package br.com.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.dao.ProdutoDAO;
import br.com.model.pedido.PedidoDetalhe;
import br.com.model.produto.Produto;

/**
 * Servlet implementation class AdicionaCarrinhoComprasServlet
 */
@WebServlet("/ac.do")
public class AdicionaCarrinhoComprasServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdicionaCarrinhoComprasServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idProduto = request.getParameter("idProduto");

		if (idProduto!=null && !idProduto.isEmpty()) {
			Produto produto = new ProdutoDAO().buscar(new Long(idProduto));
			HttpSession session = request.getSession(true);
			
			if (session.getAttribute("carrinho")!= null) {
				@SuppressWarnings("unchecked")
				List<PedidoDetalhe> carrinho = (List<PedidoDetalhe>) session.getAttribute("carrinho");
				carrinho.add(criarItemCarrinho(produto));
				session.setAttribute("carrinho", carrinho);
			}else{
				List<PedidoDetalhe> carrinho = new ArrayList<>();
				carrinho.add(criarItemCarrinho(produto));
				session.setAttribute("carrinho", carrinho);
			}
			response.sendRedirect("./store.jsp");
		}else{
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	/**
	 * Adicona item ao carrinho ativo.
	 * */
	private PedidoDetalhe criarItemCarrinho(Produto produto){
		PedidoDetalhe pedidoDetalhe = new PedidoDetalhe();
		pedidoDetalhe.setCodItem(produto.getId());
		pedidoDetalhe.setDescricaoItem(produto.getDescricao());
		pedidoDetalhe.setQuantItem(1);
		if (produto.getPrecoPadrao().doubleValue()<=produto.getPrecoPromocional().doubleValue()) {
			pedidoDetalhe.setValorItem(produto.getPrecoPadrao());
			pedidoDetalhe.setValorDescontoItem(new BigDecimal(0));
		}else{
			pedidoDetalhe.setValorItem(produto.getPrecoPromocional());
			pedidoDetalhe.setValorDescontoItem(produto.getPrecoPadrao().subtract(produto.getPrecoPromocional()));
		}
		pedidoDetalhe.setValorTotalItem(pedidoDetalhe.getValorItem().multiply(new BigDecimal(pedidoDetalhe.getQuantItem())));
		
		return pedidoDetalhe;
	}

}

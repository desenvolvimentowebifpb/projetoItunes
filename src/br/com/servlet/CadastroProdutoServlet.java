package br.com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.dao.ArtistaDAO;
import br.com.dao.GeneroDAO;
import br.com.dao.TipoProdutoDAO;
import br.com.model.produto.Produto;

/**
 * Servlet implementation class CadastroProdutoServlet
 */
@WebServlet("/cp.do")
public class CadastroProdutoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CadastroProdutoServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Produto produto = new Produto();
		produto.setDescricao(request.getParameter("descricao"));
		produto.setGenero(new GeneroDAO().buscar(request.getParameter("cbGenero")));
		produto.setArtista(new ArtistaDAO().buscar(request.getParameter("cbArtista")));
		produto.setTipoProduto(new TipoProdutoDAO().buscar(request.getParameter("cbTipoProduto")));
		System.out.println("cadastrado");

	}	

}

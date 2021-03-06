package br.com.servlet;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.dao.GeneroDAO;
import br.com.model.produto.Genero;


/**
 * Servlet implementation class CadastroProdutoCategoriasServlet
 */
@WebServlet("/cpc.do")
public class ViewProdutoCategoriasServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewProdutoCategoriasServlet() {
        super();
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String query = request.getParameter("categoria");
		String queryString = URLDecoder.decode(query, "ISO-8859-1");
		
		Genero artista = new GeneroDAO().buscar(queryString);
		System.out.println(queryString);
		
		request.setAttribute("idGenero", artista.getId());
		request.setAttribute("genero", artista.getNomeGenero());
		request.getRequestDispatcher("./cadastro_genero_filter.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

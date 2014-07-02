package br.com.servlet;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.dao.ArtistaDAO;
import br.com.model.pessoa.Artista;

/**
 * Servlet implementation class CadastroProdutoArtistaServlet
 */
@WebServlet("/cpa.do")
public class ViewProdutoArtistaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewProdutoArtistaServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String query = request.getParameter("categoria");
		String queryString = URLDecoder.decode(query, "ISO-8859-1");
		
		Artista artista = new ArtistaDAO().buscar(queryString);
		System.out.println(queryString);
		
		request.setAttribute("idArtista", artista.getId());
		request.setAttribute("artista", artista.getNomeArtista());
		request.getRequestDispatcher("./cadastro_artista_filter.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

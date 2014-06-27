package br.com.servlet;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.model.pessoa.Artista;

/**
 * Servlet implementation class CadastroProdutoServlet
 */
@WebServlet("/ca.do")

public class CadastroArtistaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CadastroArtistaServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Artista artista = new Artista();
		artista.setNomeArtista((String)request.getParameter("artista"));
		artista.setDataCadastro(Calendar.getInstance());
		artista.setDataUltAlteracao(Calendar.getInstance());
		artista.setLoginCadastro(new Long(1));
	}	
	
}

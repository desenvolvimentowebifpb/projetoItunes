package br.com.servlet;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.actions.VerificaLogin;
import br.com.dao.ArtistaDAO;
import br.com.model.pessoa.Artista;
import br.com.validated.ArtistaValidated;

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
		
		if (VerificaLogin.isAdminLogged(request, response)) {
			Artista artista = new Artista();
			artista.setNomeArtista((String)request.getParameter("artista"));
			artista.setDataCadastro(Calendar.getInstance());
			artista.setDataUltAlteracao(Calendar.getInstance());
			artista.setLoginCadastro(new Long(1));
			
			HashMap<String, String> map = new ArtistaValidated().isValid(artista);
			if (map.get("boolean").equals("true")) {
				new ArtistaDAO().inserir(artista);
				request.setAttribute("artista", artista);
				request.getRequestDispatcher("./cadastro_artista_view.jsp").forward(request, response);
			}else{
				request.setAttribute("message", map.get("message"));
				request.getRequestDispatcher("./cadastro_artista_error.jsp").forward(request, response);
			}
		}else{
			request.setAttribute("message", "Area Restrita - Voce Não possui privilegios para esta Operação.");
			request.getRequestDispatcher("./cadastro_artista_error.jsp").forward(request, response);
		}
	}	
	
	

	
}

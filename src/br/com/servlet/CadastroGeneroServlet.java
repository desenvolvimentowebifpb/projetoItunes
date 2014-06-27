package br.com.servlet;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.dao.GeneroDAO;
import br.com.model.produto.Genero;
import br.com.validated.GeneroValidated;


/**
 * Servlet implementation class CadastroProdutoServlet
 */
@WebServlet("/cg.do")

public class CadastroGeneroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CadastroGeneroServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Genero genero = new Genero();
		genero.setNomeGenero((String)request.getParameter("genero"));
		genero.setDataCadastro(Calendar.getInstance());
		genero.setDataUltAlteracao(Calendar.getInstance());
		genero.setLoginCadastro(new Long(1));
		
		HashMap<String, String> map = new GeneroValidated().isValid(genero);
		if (map.get("boolean").equals("true")) {
			new GeneroDAO().inserir(genero);
			request.setAttribute("genero", genero);
			request.getRequestDispatcher("./cadastro_genero_view.jsp").forward(request, response);
		}else{
			request.setAttribute("message", map.get("message"));
			request.getRequestDispatcher("./cadastro_genero_error.jsp").forward(request, response);
		}
	}	
	
}

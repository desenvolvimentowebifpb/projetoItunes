package br.com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.actions.VerificaLogin;

/**
 * Servlet implementation class CadastroClienteServlet
 */
@WebServlet("/cc.do")
public class CadastroClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CadastroClienteServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (VerificaLogin.isClienteLogged(request, response)) {
			
		}else{
			request.setAttribute("errorLogin", "<a href=\"/restrito.jsp\">Area Restrita a Clientes.</a>");
			request.getRequestDispatcher("./restrito.jsp").forward(request, response);
		}
	}

}

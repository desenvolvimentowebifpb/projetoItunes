package br.com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.dao.ClienteDAO;

/**
 * Servlet implementation class VerificaClienteServlet
 */
@WebServlet("/vcc.do")
public class VerificaClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerificaClienteServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String cpf = request.getParameter("cpf");
			cpf=cpf.replaceAll("\\D", "");
			if (new ClienteDAO().validatedExist(cpf)) {
				request.setAttribute("errorCadastro", "Ja Existe Cadastro para este CPF.");
				request.getRequestDispatcher("./restrito.jsp").forward(request, response);
			}else{
				request.getRequestDispatcher("./cadastro_cliente.jsp").forward(request, response);
			}
	}

}

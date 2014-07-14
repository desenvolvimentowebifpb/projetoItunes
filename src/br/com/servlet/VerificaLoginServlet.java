package br.com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class VerificaLoginServlet
 */
public class VerificaLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerificaLoginServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String usuarioNome ="";
		String usuarioValue="";
		String ultAcesso="";
		Cookie[] cookies = request.getCookies();
		Cookie c = null;
		if (cookies!=null) {
			int length = cookies.length;
			for (int i = 0; i < length; i++) {
				c = cookies[i];
				if (c.getName().equals("usuarioNome")) {
					usuarioNome = c.getName();
					usuarioValue = c.getValue();
				}
				if (c.getName().equals("sessionTime")) {
					ultAcesso = c.getValue();
				}
			}
		}
		
		if (usuarioNome.trim().isEmpty() || usuarioValue.equals("false")) {
			PrintWriter out = response.getWriter();
			out.println("<a href=\"./restrito.jsp\">Click aqui para efetuar login...</a>");
		}else{
			HttpSession session = request.getSession();
			
			if (session==null) {
				PrintWriter out = response.getWriter();
				out.println("<a href=\"./restrito.jsp\">Click aqui para efetuar login...</a>");
			}else{
				String session_atribut = (String) session.getAttribute(usuarioValue);
				if (session_atribut!=null) {
					if (session_atribut.equals("true")) {
						PrintWriter out = response.getWriter();
						out.println("Usuario Logado: "+usuarioNome+"&nbsp&nbsp&nbspUltimo Acesso: "+ultAcesso+"&nbsp&nbsp&nbsp<a href=\"./logoff.do\">Logoff</a>");
					}else{
						PrintWriter out = response.getWriter();
						out.println("<a href=\"./restrito.jsp\">Click aqui para efetuar login...</a>");
					}
				}else{
					PrintWriter out = response.getWriter();
					out.println("<a href=\"./restrito.jsp\">Click aqui para efetuar login...</a>");
				}
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

package br.com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogoffServlet
 */
@WebServlet("/logoff.do")
public class LogoffServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoffServlet() {
        super();
    }
    
    

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String usuarioNome ="";

		Cookie[] cookies = request.getCookies();
		Cookie c = null;
		for (int i = 0; i < cookies.length; i++) {
			c = cookies[i];
			if (c.getName().equals("usuarioNome")) {
				usuarioNome = c.getName();
			}
		}
		
		HttpSession session = request.getSession();
		
		if (session==null) {
			if (usuarioNome.trim().isEmpty()) {
				response.sendRedirect("./index.jsp");
			}else{
				c.setValue("false");
				c.setPath("/");
				c.setMaxAge(0);
				response.addCookie(c);
				response.sendRedirect("./index.jsp");
			}
		}else{
			session.setMaxInactiveInterval(0);
			session.setAttribute(usuarioNome, "false");
			session.removeAttribute(usuarioNome);
			session.invalidate();
			
			c.setValue("false");
			c.setPath("/");
			c.setMaxAge(0);
			response.addCookie(c);
			response.sendRedirect("./index.jsp");
		}
	}



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doGet(request, response);
	}

}

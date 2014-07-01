package br.com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class VerificaCookiesServlet
 */
@WebServlet("/vc.do")
public class VerificaCookiesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerificaCookiesServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Entrou no servlet...");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		if (request.getParameter("flag")==null) {
			request.setAttribute("flag", 1);
			System.out.println("Retornando o cookie ao cliente...");
			Cookie cookie = new Cookie("browserSetting","on");
			response.addCookie(cookie);
			String nextUrl = request.getRequestURI()+"?flag=1";
			out.println("<META HTTP-EQUIV=Refresh CONTENT=0; URL="+
					nextUrl+">");
		}else{
			System.out.println("Verificando o cookie do cliente...");
			Cookie[] cookies = request.getCookies();
			int length = cookies.length;
			boolean cookieFound = false;
			for (int i = 0; i < length; i++) {
				Cookie cookie = cookies[i];
				if (cookie.getName().equals("browserSetting") && cookie.getValue().equals("on")) {
					System.out.println("Cookie encontrado do cliente...");
					cookieFound= true;
					break;
				}//segundo if
			}//for
			
			if (cookieFound) {
				request.setAttribute("message", "Seu Navegador aceita cookies.");
			}else{
				request.setAttribute("message", "Seu Navegador não aceita cookies.");
			}
			request.getRequestDispatcher("./restrito.jsp").forward(request, response);
		}//primeirop if
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

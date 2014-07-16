package br.com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.actions.EncryptPassword;
import br.com.dao.UsuarioDAO;
import br.com.model.pessoa.Usuario;

/**
 * Servlet implementation class ControllerServlet
 */
@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Usuario user;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String usuario = request.getParameter("usuario");
		String senha = request.getParameter("senha");
		
		user = new Usuario();
		if (usuario!=null && senha!=null) {
			if (!usuario.trim().isEmpty()) {
				user = new UsuarioDAO().buscar(usuario.trim());
				if (user!=null) {
					if (new EncryptPassword().encrypt(senha.trim()).equals(user.getSenha())) {
						HttpSession session = request.getSession(true);
						session.setAttribute("usuarioLogado", "true");
						session.setAttribute("usuarioNome", user.getLogin());
						System.out.println("Nome do usuario da sessao: "+user.getLogin());
						session.setMaxInactiveInterval(900);
						
						if (user.getTipoUsuario().getId()==1) {
							response.sendRedirect("./menu_admin.jsp");
						}else{
							response.sendRedirect("./index.jsp");
						}
					}else{
						request.setAttribute("errorLogin", "<a href=\"/restrito.jsp\">Usuario ou Senha Invalidos!</a>");
						request.getRequestDispatcher("./restrito.jsp").forward(request, response);
					}
				}else{
					request.setAttribute("errorLogin", "<a href=\"/restrito.jsp\">Usuario não cadastrado!</a>");
					request.getRequestDispatcher("./restrito.jsp").forward(request, response);
				}//if usuario != null
			}else{
				request.setAttribute("errorLogin", "<a href=\"/restrito.jsp\">Usuario e Senha em brancos.</a>");
				request.getRequestDispatcher("./restrito.jsp").forward(request, response);
			}
		}else{
			request.setAttribute("errorLogin", "<a href=\"/restrito.jsp\">Usuario e Senha em brancos.</a>");
			request.getRequestDispatcher("./restrito.jsp").forward(request, response);
		}
		
		
	}
    
    

}

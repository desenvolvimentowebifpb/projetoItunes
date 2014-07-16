package br.com.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.dao.UsuarioDAO;
import br.com.model.pessoa.Usuario;

public class VerificaLogin {
	
	public static boolean isAdminLogged(HttpServletRequest request, HttpServletResponse response){
		String usuarioLogado ="";
		String usuarioNome="";

		HttpSession session = request.getSession();
		usuarioLogado = (String) session.getAttribute("usuarioLogado");
		System.out.println("Esta logado? " + usuarioLogado);
		if (usuarioLogado.equals("true")) {
			usuarioNome = (String) session.getAttribute("usuarioNome");
			Usuario usuario = new UsuarioDAO().buscar(usuarioNome);
			System.out.println("Tipo de usuario: "+usuario.getTipoUsuario().getId());
			
			if (usuario.getTipoUsuario().getId().equals(new Long(1))) {
				System.out.println("Usuario esta logado...");
				return true;
			}else{
				System.out.println("Usuario invalido logado...");
				System.out.println("Usuario "+usuario.getLogin() +" do tipo "+ usuario.getTipoUsuario().getId());
				return false;
			}
		}else{
			System.out.println("Usuario não esta logado...");
			return false;
		}
	}

	public static boolean isClienteLogged(HttpServletRequest request, HttpServletResponse response){
		return false;
	}
}

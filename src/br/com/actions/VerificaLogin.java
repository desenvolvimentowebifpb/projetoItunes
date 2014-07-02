package br.com.actions;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.dao.UsuarioDAO;
import br.com.model.pessoa.Usuario;

public class VerificaLogin {
	
	public static boolean isAdminLogged(HttpServletRequest request, HttpServletResponse response){
		String usuarioNome ="";
		String usuarioValue="";
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
			}
		}
		
		if (usuarioNome.trim().isEmpty() || usuarioValue.equals("false")) {
			return false;
		}else{
			HttpSession session = request.getSession();
			if (session==null) {
				return false;
			}else{
				String session_atribut = (String) session.getAttribute(usuarioValue);
				if (session_atribut!=null) {
					if (session_atribut.equals("true")) {
						Usuario usuario = new UsuarioDAO().buscar(usuarioValue);
						if (usuario.getTipoUsuario().getId()==new Long(1)) {
							return true;
						}else{
							return false;
						}
					}else{
						return false;
					}
				}else{
					return false;
				}
			}
		}
	}

	public static boolean isClienteLogged(HttpServletRequest request, HttpServletResponse response){
		String usuarioNome ="";
		String usuarioValue="";
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
			}
		}
		
		if (usuarioNome.trim().isEmpty() || usuarioValue.equals("false")) {
			return false;
		}else{
			HttpSession session = request.getSession();
			if (session==null) {
				return false;
			}else{
				String session_atribut = (String) session.getAttribute(usuarioValue);
				if (session_atribut!=null) {
					if (session_atribut.equals("true")) {
						Usuario usuario = new UsuarioDAO().buscar(usuarioValue);
						if (usuario.getTipoUsuario().getId()!=new Long(1)) {
							return true;
						}else{
							return false;
						}
					}else{
						return false;
					}
				}else{
					return false;
				}
			}
		}
	}
}

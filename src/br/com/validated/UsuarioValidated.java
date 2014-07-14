package br.com.validated;

import br.com.model.pessoa.Usuario;


public class UsuarioValidated {
	
	/**
	 * Verifica se o Usuario Cadastrado é Validio
	 * */
	public boolean isValid(Usuario usuario){
		
		if (usuario.getLogin().trim().isEmpty() || usuario.getLogin()==null) {
			return false;
		}

		if (usuario.getSenha()==null||usuario.getSenha().trim().isEmpty()) {
			return false;
		}

		if (usuario.getLembreteSenha()==null ||usuario.getLembreteSenha().trim().isEmpty()) {
			return false;
		}

		return true;
		
	}
}

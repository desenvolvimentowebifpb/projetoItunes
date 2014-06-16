/**
 * 
 */
package br.com.model.pessoa;

import br.com.model.CadastroModel;

/**
 * @author Leonardo Costa Vieira
 * @email leocg@hotmail.com.br
 *
 * Classe Modelo dos Usuarios da Loja Online 
 */
public class Usuario extends CadastroModel {
	private String login;
	private String senha;
	private String email;
	private String lembreteSenha;
	private TipoUsuario tipoUsuario;
	
	/**
	 * 
	 */
	public Usuario() {
		super();
	}

	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @return the senha
	 */
	public String getSenha() {
		return senha;
	}

	/**
	 * @param senha the senha to set
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the lembreteSenha
	 */
	public String getLembreteSenha() {
		return lembreteSenha;
	}

	/**
	 * @param lembreteSenha the lembreteSenha to set
	 */
	public void setLembreteSenha(String lembreteSenha) {
		this.lembreteSenha = lembreteSenha;
	}

	/**
	 * @return the tipoUsuario
	 */
	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}

	/**
	 * @param tipoUsuario the tipoUsuario to set
	 */
	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	
	

}

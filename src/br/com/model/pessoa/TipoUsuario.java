/**
 * 
 */
package br.com.model.pessoa;

import br.com.model.CadastroModel;

/**
 * @author Leonardo Costa Vieira
 * @email leocg@hotmail.com.br
 *
 * Classe Modelo dos Tipos de Usuarios da Loja Online 
 */
public class TipoUsuario extends CadastroModel {
	
	private String tipoUsuario;

	/**
	 * Construtor Default da Classe
	 */
	public TipoUsuario() {
		super();
	}

	/**
	 * @return the tipoUsuario
	 */
	public String getTipoUsuario() {
		return tipoUsuario;
	}

	/**
	 * @param tipoUsuario the tipoUsuario to set
	 */
	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	
	

}

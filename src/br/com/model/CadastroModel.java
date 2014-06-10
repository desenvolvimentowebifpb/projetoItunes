package br.com.model;

import java.util.Calendar;

/**
 * @author Leonardo Costa Vieira
 * @email leocg@hotmail.com.br
 *
 * Classe Modelo dos Cadastros do Sistema
 */
public class CadastroModel {

	private Long id;
	private Long loginCadastro;
	private Calendar dataCadastro;
	private Calendar dataUltAlteracao;
	
	/**
	 * Construtor DEFAULT da classe
	 */
	public CadastroModel() {
		super();
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the loginCadastro
	 */
	public Long getLoginCadastro() {
		return loginCadastro;
	}

	/**
	 * @param loginCadastro the loginCadastro to set
	 */
	public void setLoginCadastro(Long loginCadastro) {
		this.loginCadastro = loginCadastro;
	}

	/**
	 * @return the dataCadastro
	 */
	public Calendar getDataCadastro() {
		return dataCadastro;
	}

	/**
	 * @param dataCadastro the dataCadastro to set
	 */
	public void setDataCadastro(Calendar dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	/**
	 * @return the dataUltAlteracao
	 */
	public Calendar getDataUltAlteracao() {
		return dataUltAlteracao;
	}

	/**
	 * @param dataUltAlteracao the dataUltAlteracao to set
	 */
	public void setDataUltAlteracao(Calendar dataUltAlteracao) {
		this.dataUltAlteracao = dataUltAlteracao;
	}
	
	
	
	
}

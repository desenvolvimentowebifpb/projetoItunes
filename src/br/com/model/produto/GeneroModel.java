package br.com.model.produto;

import br.com.model.CadastroModel;


/**
 * @author Leonardo Costa Vieira
 * @email leocg@hotmail.com.br
 *
 * Classe Modelo dos Generos Musicais 
 */
public class GeneroModel extends CadastroModel{
	private String nomeGenero;
	
	/**
	 * Construtor DEFAULT da Classe.
	 */
	public GeneroModel() {
		super();
	}

	/**
	 * @return the nomeGenero
	 */
	public String getNomeGenero() {
		return nomeGenero;
	}

	/**
	 * @param nomeGenero the nomeGenero to set
	 */
	public void setNomeGenero(String nomeGenero) {
		this.nomeGenero = nomeGenero;
	}
	

}
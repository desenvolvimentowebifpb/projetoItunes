package br.com.model.pessoa;

import br.com.model.CadastroModel;


/**
 * @author Leonardo Costa Vieira
 * @email leocg@hotmail.com.br
 *
 * Classe Modelo dos Artistas da Loja Online 
 */
public class ArtistaModel extends CadastroModel{
	private String nomeArtista;

	/**
	 * Construtor DEFAULT da Classe.
	 */
	public ArtistaModel() {
		super();
	}

	/**
	 * @return the nomeArtista
	 */
	public String getNomeArtista() {
		return nomeArtista;
	}

	/**
	 * @param nomeArtista the nomeArtista to set
	 */
	public void setNomeArtista(String nomeArtista) {
		this.nomeArtista = nomeArtista;
	}
	
	
	
}

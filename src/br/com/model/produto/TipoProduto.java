package br.com.model.produto;

import br.com.model.CadastroModel;


/**
 * @author Leonardo Costa Vieira
 * @email leocg@hotmail.com.br
 *
 * Classe Modelo do Tipo de `Produto da Loja Online 
 */
public class TipoProduto extends CadastroModel{
	private String tipoProduto;
	
	/**
	 * Construtor DEFAULT da Classe.
	 */
	public TipoProduto() {
		super();
	}

	/**
	 * @return the tipoProduto
	 */
	public String getTipoProduto() {
		return tipoProduto;
	}

	/**
	 * @param tipoProduto the tipoProduto to set
	 */
	public void setTipoProduto(String tipoProduto) {
		this.tipoProduto = tipoProduto;
	}


}
package br.com.model.produto;

import br.com.model.CadastroModel;

/**
 * @author Leonardo Costa Vieira
 * @email leocg@hotmail.com.br
 *
 * Classe Modelo dos Produtos da Loja Online 
 */
public class ProdutoFile extends CadastroModel {
	
	private Long codProduto;
	private byte[] file;
	
	/**
	 * construtor default da classe
	 * */
	public ProdutoFile() {
		super();
	}

	/**
	 * @return the codProduto
	 */
	public Long getCodProduto() {
		return codProduto;
	}

	/**
	 * @param codProduto the codProduto to set
	 */
	public void setCodProduto(Long codProduto) {
		this.codProduto = codProduto;
	}

	/**
	 * @return the file
	 */
	public byte[] getFile() {
		return file;
	}

	/**
	 * @param file the file to set
	 */
	public void setFile(byte[] file) {
		this.file = file;
	}

	
	
	
}

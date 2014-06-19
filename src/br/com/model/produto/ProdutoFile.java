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
	private byte[] arquivo;
	
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
	public byte[] getArquivo() {
		return arquivo;
	}

	/**
	 * @param arquivo the file to set
	 */
	public void setArquivo(byte[] arquivo) {
		this.arquivo = arquivo;
	}

	
	
	
}

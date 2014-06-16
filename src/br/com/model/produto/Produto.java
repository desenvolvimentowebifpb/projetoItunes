/**
 * 
 */
package br.com.model.produto;

import java.math.BigDecimal;

import br.com.model.CadastroModel;
import br.com.model.pessoa.Artista;

/**
 * @author Leonardo Costa Vieira
 * @email leocg@hotmail.com.br
 *
 * Classe Modelo dos Produtos da Loja Online 
 */
public class Produto extends CadastroModel{
	private String descricao;
	private byte[] image;
	private Artista artista = new Artista();
	private Genero genero = new Genero();
	private ProdutoTipo tipoProduto = new ProdutoTipo();
	private BigDecimal precoPadrao;
	private BigDecimal precoPromocional;
	
	/**
	 * Construtor Default da CLASSE;
	 */
	public Produto() {
		super();
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return the artista
	 */
	public Artista getArtista() {
		return artista;
	}

	/**
	 * @param artista the artista to set
	 */
	public void setArtista(Artista artista) {
		this.artista = artista;
	}

	/**
	 * @return the genero
	 */
	public Genero getGenero() {
		return genero;
	}

	/**
	 * @param genero the genero to set
	 */
	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	/**
	 * @return the tipoProduto
	 */
	public ProdutoTipo getTipoProduto() {
		return tipoProduto;
	}

	/**
	 * @param tipoProduto the tipoProduto to set
	 */
	public void setTipoProduto(ProdutoTipo tipoProduto) {
		this.tipoProduto = tipoProduto;
	}

	/**
	 * @return the precoPadrao
	 */
	public BigDecimal getPrecoPadrao() {
		return precoPadrao;
	}

	/**
	 * @param precoPadrao the precoPadrao to set
	 */
	public void setPrecoPadrao(BigDecimal precoPadrao) {
		this.precoPadrao = precoPadrao;
	}

	/**
	 * @return the precoPromocional
	 */
	public BigDecimal getPrecoPromocional() {
		return precoPromocional;
	}

	/**
	 * @param precoPromocional the precoPromocional to set
	 */
	public void setPrecoPromocional(BigDecimal precoPromocional) {
		this.precoPromocional = precoPromocional;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
	
	
	
	
	
}

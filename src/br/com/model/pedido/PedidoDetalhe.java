package br.com.model.pedido;

import java.math.BigDecimal;

import br.com.model.CadastroModel;


/**
 * @author Leonardo Costa Vieira
 * @param <ProdutoModel>
 * @email leocg@hotmail.com.br
 *
 * Classe Modelo do Detalhe dos Pedidos do Sistema
 */

public class PedidoDetalhe extends CadastroModel{
	
	private Long codPedido;
	private Long codItem;
	private String descricaoItem;
	private Integer quantItem;
	private BigDecimal valorItem;
	private BigDecimal valorDescontoItem;
	private BigDecimal valorTotalItem;

	/**
	 * Construtor Default da Classe
	 * */
	public PedidoDetalhe() {
		super();
	}

	/**
	 * @return the codPedido
	 */
	public Long getCodPedido() {
		return codPedido;
	}

	/**
	 * @param codPedido the codPedido to set
	 */
	public void setCodPedido(Long codPedido) {
		this.codPedido = codPedido;
	}

	/**
	 * @return the codItem
	 */
	public Long getCodItem() {
		return codItem;
	}

	/**
	 * @param codItem the codItem to set
	 */
	public void setCodItem(Long codItem) {
		this.codItem = codItem;
	}

	/**
	 * @return the descricaoItem
	 */
	public String getDescricaoItem() {
		return descricaoItem;
	}

	/**
	 * @param descricaoItem the descricaoItem to set
	 */
	public void setDescricaoItem(String descricaoItem) {
		this.descricaoItem = descricaoItem;
	}

	/**
	 * @return the quantItem
	 */
	public Integer getQuantItem() {
		return quantItem;
	}

	/**
	 * @param quantItem the quantItem to set
	 */
	public void setQuantItem(Integer quantItem) {
		this.quantItem = quantItem;
	}

	/**
	 * @return the valorItem
	 */
	public BigDecimal getValorItem() {
		return valorItem;
	}

	/**
	 * @param valorItem the valorItem to set
	 */
	public void setValorItem(BigDecimal valorItem) {
		this.valorItem = valorItem;
	}

	/**
	 * @return the valorDescontoItem
	 */
	public BigDecimal getValorDescontoItem() {
		return valorDescontoItem;
	}

	/**
	 * @param valorDescontoItem the valorDescontoItem to set
	 */
	public void setValorDescontoItem(BigDecimal valorDescontoItem) {
		this.valorDescontoItem = valorDescontoItem;
	}

	/**
	 * @return the valorTotalItem
	 */
	public BigDecimal getValorTotalItem() {
		return valorTotalItem;
	}

	/**
	 * @param valorTotalItem the valorTotalItem to set
	 */
	public void setValorTotalItem(BigDecimal valorTotalItem) {
		this.valorTotalItem = valorTotalItem;
	}
	
	

}

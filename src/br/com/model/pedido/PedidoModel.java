package br.com.model.pedido;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.model.CadastroModel;

/**
 * @author Leonardo Costa Vieira
 * @email leocg@hotmail.com.br
 *
 * Classe Modelo dos Pedidos do Sistema
 */
public class PedidoModel extends CadastroModel {
	
	private Long codCliente;
	private BigDecimal valorTotal;
	private BigDecimal valorTotalDesconto;
	private Integer quantItens;
	private Calendar dataPedido;
	private Calendar dataDownload;
	private FormaPagamento formaPagamento;
	private List<PedidoDetalheModel> listItens = new ArrayList<PedidoDetalheModel>();
	
	public PedidoModel() {
		super();
	}

	/**
	 * @return the codCliente
	 */
	public Long getCodCliente() {
		return codCliente;
	}

	/**
	 * @param codCliente the codCliente to set
	 */
	public void setCodCliente(Long codCliente) {
		this.codCliente = codCliente;
	}

	/**
	 * @return the valorTotal
	 */
	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	/**
	 * @param valorTotal the valorTotal to set
	 */
	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	/**
	 * @return the valorTotalDesconto
	 */
	public BigDecimal getValorTotalDesconto() {
		return valorTotalDesconto;
	}

	/**
	 * @param valorTotalDesconto the valorTotalDesconto to set
	 */
	public void setValorTotalDesconto(BigDecimal valorTotalDesconto) {
		this.valorTotalDesconto = valorTotalDesconto;
	}

	/**
	 * @return the quantItens
	 */
	public Integer getQuantItens() {
		return quantItens;
	}

	/**
	 * @param quantItens the quantItens to set
	 */
	public void setQuantItens(Integer quantItens) {
		this.quantItens = quantItens;
	}

	/**
	 * @return the dataPedido
	 */
	public Calendar getDataPedido() {
		return dataPedido;
	}

	/**
	 * @param dataPedido the dataPedido to set
	 */
	public void setDataPedido(Calendar dataPedido) {
		this.dataPedido = dataPedido;
	}

	/**
	 * @return the dataDownload
	 */
	public Calendar getDataDownload() {
		return dataDownload;
	}

	/**
	 * @param dataDownload the dataDownload to set
	 */
	public void setDataDownload(Calendar dataDownload) {
		this.dataDownload = dataDownload;
	}

	/**
	 * @return the formaPagamento
	 */
	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	/**
	 * @param formaPagamento the formaPagamento to set
	 */
	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	/**
	 * @return the listItens
	 */
	public List<PedidoDetalheModel> getListItens() {
		return listItens;
	}

	/**
	 * @param listItens the listItens to set
	 */
	public void setListItens(List<PedidoDetalheModel> listItens) {
		this.listItens = listItens;
	}
	
	

}

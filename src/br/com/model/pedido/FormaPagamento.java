/**
 * 
 */
package br.com.model.pedido;

import br.com.model.CadastroModel;

/**
 * @author Leonardo Costa Vieira
 * @email leocg@hotmail.com.br
 *
 * Classe Modelo das Formas de Pagamento do Sistema
 */
public class FormaPagamento extends CadastroModel {
	private String formaPagamento;
	private Integer quantParcelas;
	
	
	/**
	 * construtor default da classe
	 */
	public FormaPagamento() {
		super();
	}


	/**
	 * @return the formaPagamento
	 */
	public String getFormaPagamento() {
		return formaPagamento;
	}


	/**
	 * @param formaPagamento the formaPagamento to set
	 */
	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}


	/**
	 * @return the quantParcelas
	 */
	public Integer getQuantParcelas() {
		return quantParcelas;
	}


	/**
	 * @param quantParcelas the quantParcelas to set
	 */
	public void setQuantParcelas(Integer quantParcelas) {
		this.quantParcelas = quantParcelas;
	}
	
	

}

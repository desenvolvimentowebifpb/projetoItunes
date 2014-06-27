/**
 * 
 */
package br.com.validated;

import java.util.HashMap;

import br.com.model.produto.Produto;

/**
 * @author DW-PC
 *
 */
public class ProdutoValidated {
	int value = 0;
	String message="<p>";
	HashMap<String, String> values = new HashMap<>();
	public HashMap<String, String> isValid(Produto produto){
		
		if (produto.getDescricao().trim().isEmpty()) {
			message+=(" <p> &#8227 Descrição Invalida");
			value++;
		}
		
		if (produto.getArtista() == null) {
			message+="<p> &#8227 Artista Invalido";
			value++;
		}

		if (produto.getGenero() == null) {
			message+="<p> &#8227 Genero Invalido";
			value++;
		}

		if (produto.getTipoProduto() == null) {
			message+="<p> &#8227 Tipo de Produto Invalido";
			value++;
		}

		if (produto.getImage() == null) {
			message+="<p> &#8227 Imagem Invalida";
			value++;
		}

		if ((produto.getPrecoPadrao() == null) || (produto.getPrecoPadrao().doubleValue()<= 0.0)) {
			message+="<p> &#8227 Preço Padrao Invalido";
			value++;
		}

		if ((produto.getPrecoPromocional() == null) || (produto.getPrecoPromocional().doubleValue()<= 0.0)) {
			message+="<p> &#8227 Preço Promocional Invalido";
			value++;
		}
		message.concat("<p>");
		
		values.put("message", message);
		if (value==0) {
			values.put("boolean", "true");
		}else{
			values.put("boolean", "false");
		}
		
		return values;
	}
}

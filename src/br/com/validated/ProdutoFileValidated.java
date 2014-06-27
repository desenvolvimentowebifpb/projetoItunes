/**
 * 
 */
package br.com.validated;

import java.util.HashMap;

import br.com.model.produto.ProdutoFile;



/**
 * @author DW-PC
 *
 */
public class ProdutoFileValidated {
	int value = 0;
	String message="<p>";
	HashMap<String, String> values = new HashMap<>();
	public HashMap<String, String> isValid(ProdutoFile produtoFile){
		
		if (produtoFile.getArquivo() == null) {
			message.concat(" <p> &#8227 Arquivo Invalido");
			value++;
		}
		
		if (produtoFile.getCodProduto() <= 0) {
			message+="<p> &#8227 Produto Invalido";
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

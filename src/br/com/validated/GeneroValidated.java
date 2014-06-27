/**
 * 
 */
package br.com.validated;

import java.util.HashMap;

import br.com.model.produto.Genero;


/**
 * @author DW-PC
 *
 */
public class GeneroValidated {
	int value = 0;
	String message="<p>";
	HashMap<String, String> values = new HashMap<>();
	public HashMap<String, String> isValid(Genero genero){
		
		if (genero.getNomeGenero().trim().isEmpty() || genero.getNomeGenero()==null) {
			message+=(" <p> &#8227 Genero Invalido");
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

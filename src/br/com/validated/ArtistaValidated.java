/**
 * 
 */
package br.com.validated;

import java.util.HashMap;

import br.com.model.pessoa.Artista;


/**
 * @author DW-PC
 *
 */
public class ArtistaValidated {
	int value = 0;
	String message="<p>";
	HashMap<String, String> values = new HashMap<>();
	public HashMap<String, String> isValid(Artista artista){
		
		if (artista.getNomeArtista().trim().isEmpty() || artista.getNomeArtista()==null) {
			message+=(" <p> &#8227 Artista Invalido");
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

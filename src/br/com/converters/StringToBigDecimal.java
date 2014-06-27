/**
 * 
 */
package br.com.converters;

import java.math.BigDecimal;

/**
 * @author DW-PC
 *
 */
public class StringToBigDecimal {
	
	public BigDecimal StringToBigDecimalValue(String valor){
		String valor_s = valor;
		String valor_s_t = valor_s.replace(",", ".");
		String valor_s_t_r = valor_s_t.replace("R", "");
		String valor_s_t_r_s = valor_s_t_r.replace("$", "");
		String valor_s_t_r_s_r = valor_s_t_r_s.replace("r", "");
		if (valor_s_t_r_s_r.trim().isEmpty()) {
			valor_s_t_r_s_r="0.0";
		}
		
		BigDecimal valor_bd = new BigDecimal(valor_s_t_r_s_r);
		return valor_bd;
	}
}

package br.com.validated;

import java.util.Calendar;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.model.pessoa.Cliente;


public class ClienteValidated {
	int value = 0;
	String message="<p>";
	HashMap<String, String> values = new HashMap<>();
	public HashMap<String, String> isValid(Cliente cliente){
		
		if (cliente.getNome().trim().isEmpty() || cliente.getNome()==null) {
			message+=(" <p> &#8227 Nome Invalido");
			value++;
		}

		if (cliente.getSobrenome().trim().isEmpty() || cliente.getSobrenome()==null) {
			message+=(" <p> &#8227 Sobrenome Invalido");
			value++;
		}
		
		Calendar dtaNascimento = Calendar.getInstance();
		dtaNascimento.add(Calendar.YEAR, -18);
		System.out.println("Data Limite: "+dtaNascimento.getTimeInMillis());
		System.out.println("Data Nascimento: "+cliente.getDataNascimento().getTimeInMillis());
		if (cliente.getDataNascimento()==null|| cliente.getDataNascimento().getTimeInMillis()>dtaNascimento.getTimeInMillis()) {
			message+=(" <p> &#8227 Data Nascimento Invalido");
			value++;
		}

		if (cliente.getSexo()==null || cliente.getSexo().trim().isEmpty() ) {
			message+=(" <p> &#8227 Sexo Invalido");
			value++;
		}

		if (cliente.getEmail()==null || cliente.getEmail().trim().isEmpty()||!isEmail(cliente.getEmail())) {
			message+=(" <p> &#8227 Email Invalido");
			value++;
		}

		if (cliente.getEndereco()==null || cliente.getEndereco().trim().isEmpty()) {
			message+=(" <p> &#8227 Endereço Invalido");
			value++;
		}

		if (cliente.getEnderecoComplemento()==null || cliente.getEnderecoComplemento().trim().isEmpty() ) {
			message+=(" <p> &#8227 Endereço Complemento Invalido");
			value++;
		}
		
		if (cliente.getBarro()==null || cliente.getBarro().trim().isEmpty()) {
			message+=(" <p> &#8227 Bairro Invalido");
			value++;
		}

		if (cliente.getCidade()==null || cliente.getCidade().trim().isEmpty()) {
			message+=(" <p> &#8227 Cidade Invalido");
			value++;
		}

		if (cliente.getUf()==null || cliente.getUf().trim().isEmpty() ) {
			message+=(" <p> &#8227 Uf Invalido");
			value++;
		}

		if (cliente.getCep()==null || cliente.getCep().trim().isEmpty()) {
			message+=(" <p> &#8227 CEP Invalido");
			value++;
		}

		if (cliente.getRg()==null || cliente.getRg().trim().isEmpty()) {
			message+=(" <p> &#8227 RG Invalido");
			value++;
		}

		if (cliente.getCpf()==null || cliente.getCpf().trim().isEmpty()||!isCPF(cliente.getCpf())) {
			message+=(" <p> &#8227 CPF Invalido");
			value++;
		}
		
		if (cliente.getUsuario()==null || new UsuarioValidated().isValid(cliente.getUsuario())==false) {
			message+=(" <p> &#8227 Usuario ou Senha Invalidos");
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
	
	private static boolean isEmail(String email) {
	    Pattern p = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$"); 
	    Matcher m = p.matcher(email); 
	    if (m.find()){
	      System.out.println("O email "+email+" e valido");
	      return true;
	    }
	    else{
	      System.out.println("O E-mail "+email+" é inválido");
	      return false;
	    }  
	}
	
	private static boolean isCPF(String CPF) {
		String cpf = CPF.trim().replaceAll("\\D", "");
		System.out.println(cpf);
	    if (cpf.equals("00000000000") || cpf.equals("11111111111") ||cpf.equals("22222222222") || cpf.equals("33333333333") ||
	    		cpf.equals("44444444444") || cpf.equals("55555555555") ||cpf.equals("66666666666") || cpf.equals("77777777777") ||
	    		cpf.equals("88888888888") || cpf.equals("99999999999") ||(cpf.length() != 11)){
	       return(false);
	    }
	    
	    char dig10, dig11;
	    int sm, i, r, num, peso;

	    try {
		      sm = 0;
		      peso = 10;
		      
		      for (i=0; i<9; i++) {             
		 	        num = (int)(cpf.charAt(i) - 48);
			        sm = sm + (num * peso);
			        peso = peso - 1;
		      }
		 
		      r = 11 - (sm % 11);
		      
		      if ((r == 10) || (r == 11)){
			    	  dig10 = '0';
		      } else {
			    	  dig10 = (char)(r + 48);
		      }
		 
	
		      sm = 0;
		      peso = 11;
		      for(i=0; i<10; i++) {
			        num = (int)(cpf.charAt(i) - 48);
			        sm = sm + (num * peso);
			        peso = peso - 1;
		      }
		 
		      r = 11 - (sm % 11);
		      if ((r == 10) || (r == 11)){
			         dig11 = '0';
		      }else {
			    	 dig11 = (char)(r + 48);
		      }
		 
	
		      if ((dig10 == cpf.charAt(9)) && (dig11 == cpf.charAt(10))){
			         return(true);
		      }else {
			    	  return(false);
		      }
	    } catch (InputMismatchException erro) {
	        return(false);
	    }
	}
}

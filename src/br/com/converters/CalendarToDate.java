/**
 * 
 */
package br.com.converters;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author YNDAC
 *
 */
public class CalendarToDate {
	
	/**
	 * Retorna a data no formato "dd/MM/yyyy"
	 * */
	public static String CalendarToDateFormated(Calendar data_calendar){
	    Date data = data_calendar.getTime();  
	    
	    SimpleDateFormat spf = new SimpleDateFormat("dd/MM/yyyy");  
	    return spf.format(data);
	}
	
	/**
	 * Retorna a data no formato "dd/MM/yyyy"
	 * */
	public static String DateToDateFormated(Date date){
	    Date data = date; 
	    
	    SimpleDateFormat spf = new SimpleDateFormat("dd/MM/yyyy");  
	    return spf.format(data);
	}
	
	/**
	 * Retorna a data no formato "dd/MM/yyyy"
	 * */
	public static String DateToDateFormated(Calendar date){
		Calendar data = date; 
		Date data1 = data.getTime();
		
		SimpleDateFormat spf = new SimpleDateFormat("dd/MM/yyyy");  
		return spf.format(data1);
	}
	
	/**
	 * Retorna a data no formato "dd/MM/yyyy HH:mm:ss"
	 * */
	public static String DateToDateHourFormated(Calendar date){
		Calendar data = date; 
		Date data1 = data.getTime();
		
		SimpleDateFormat spf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
		return spf.format(data1);
	}
	
	/**
	 * Retorna o tempo formatado: "HH:mm:ss.SSS"
	 * */
	public static String TimeFormated(Calendar date){
		Calendar data = date; 
		Date data1 = data.getTime();
		
		SimpleDateFormat spf = new SimpleDateFormat("HH:mm:ss.SSS");  
		return spf.format(data1);
	}

	/**
	 * Retorna o tempo formatado: "HH:mm:ss.SSS"
	 * */
	public static String TimeFormated2(Calendar date){
		Calendar data = date; 
		Date data1 = data.getTime();
		
		SimpleDateFormat spf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS");  
		return spf.format(data1);
	}
	
	/**
	 * Retorna o tempo formatado: "HH:mm:ss.SSS"
	 * */
	public static String TimeFormated(Date date){
		Date data1 = date;
		
		SimpleDateFormat spf = new SimpleDateFormat("HH:mm:ss.SSS");  
		return spf.format(data1);
	}
	
	/**
	 * Transforma uma data em uma String sem a barra.
	 * */
	public static String DateToString(Calendar data){

		String string = DateToDateFormated(data);
		string = string.replaceAll("/", "");
		
		return string;
	}
	
	public String DiferencaHoras(Calendar hora_ini, Calendar hora_fim){
		DateFormat hf = new SimpleDateFormat("HH:mm");
		Calendar data1 = Calendar.getInstance();
		Calendar data2 = Calendar.getInstance();
		
		data1=hora_ini;
		data2=hora_fim;
		
		hf.setTimeZone(TimeZone.getTimeZone("UTC"));
		Calendar data3 = Calendar.getInstance();
		data3.setTimeInMillis(data2.getTimeInMillis() - data1.getTimeInMillis());

			return hf.format(data3);
	}
	
	public Calendar DiferencaHorasCalendar(Calendar hora_ini, Calendar hora_fim){
		DateFormat hf = new SimpleDateFormat("HH:mm");
		Calendar data1 = Calendar.getInstance();
		Calendar data2 = Calendar.getInstance();
		
		data1=hora_ini;
		data2=hora_fim;
		
		hf.setTimeZone(TimeZone.getTimeZone("UTC"));
		Calendar data3 = Calendar.getInstance();
		data3.setTimeInMillis(data2.getTimeInMillis() - data1.getTimeInMillis());
		
		return data3;
	}
	
	/**
	 * Transformar uma String "dd/MM/yyyy" em uma data valida
	 * */
	public static Calendar StringToDate(String data){
		DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");  
		try {
			Date date = (Date)formatter.parse(data);
			Calendar ca = Calendar.getInstance();
			ca.setTime(date);
			return ca;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		} 
				
	}
	
}

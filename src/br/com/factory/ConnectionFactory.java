package br.com.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * @author Leonardo Costa Vieira
 * @email leocg@hotmail.com.br
 *
 * Fabrica de Conexoes do Sistema
 */
public class ConnectionFactory {
	
	/**
	 * Responsavel por retornar uma conexao valida
	 * */
	public Connection getConnection(){
		try {
			return DriverManager.getConnection("jdbc:mysql://localhost/itunes","root","91049065");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
}

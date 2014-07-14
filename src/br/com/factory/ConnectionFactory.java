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
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection("jdbc:mysql://localhost/itunes","root","91049065");
		} catch (SQLException | ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void closeConnection(Connection conn) {
		try {
			conn.close();
			System.out.println("Conexao Fechada...");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

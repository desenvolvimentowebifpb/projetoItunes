/**
 * 
 */
package br.com.factory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 * @author Programador
 *
 */
public class PreparedStatementFactory {

	private PreparedStatement pstm;
	
	public PreparedStatement getPreparedStatement(Connection conn, String sql){
		try { // tentativa de criação do statement
			this.pstm = conn.prepareStatement(sql);// Criando o preparedStatement
			//System.out.println("pstm criado!");// Informando ao terminal que o preparedStatement foi criado.
			return this.pstm; // retornando o stm criado.
		} catch (SQLException e) {// tratamento de um erro do sql
			e.toString();
			return null;// retornando null
		}catch (NullPointerException e){ //tratamento do erro de conexao nula
			e.toString();
			return null;// retornando null
		}
	}
	
	public void close(){
		try {
			pstm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

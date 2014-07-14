package br.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.factory.ConnectionFactory;
import br.com.factory.PreparedStatementFactory;
import br.com.model.pessoa.Artista;



public class ArtistaDAO {
	private Connection conn;
	private PreparedStatement pstm;
	private String sql;
	private String tabela = "artista";
	private ResultSet rs;
	
	/**
	 * Procedure para apagar um registro da TABELA
	 * */
	public boolean apagar(Long id){
		conn = new ConnectionFactory().getConnection();
		sql = "DELETE FROM "+tabela+" WHERE id = ?";
		pstm = new PreparedStatementFactory().getPreparedStatement(conn, sql);
		
		try {
			pstm.setLong(1, id);
			pstm.executeUpdate();
			
			pstm.close();
			new ConnectionFactory().closeConnection(conn);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			new ConnectionFactory().closeConnection(conn);
			return false;
		}
	}
	
	/**
	 * Procedure para inserir um registro novo na TABELA
	 * */
	public boolean inserir(Artista artista){
		conn = new ConnectionFactory().getConnection();
		sql = "INSERT INTO "+ tabela +
				" (nomeArtista , loginCadastro, dataCadastro, dataUltAlteracao)"+ 
				" VALUES(" +
				"?,?,?,?)";
		pstm = new PreparedStatementFactory().getPreparedStatement(conn, sql);
		
		try {
			pstm.setString(1, artista.getNomeArtista());
			pstm.setLong(2, artista.getLoginCadastro());
			pstm.setDate(3, new java.sql.Date(artista.getDataCadastro().getTimeInMillis()));
			pstm.setDate(4, new java.sql.Date(artista.getDataUltAlteracao().getTimeInMillis()));
			pstm.execute();
			pstm.close();
			new ConnectionFactory().closeConnection(conn);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			new ConnectionFactory().closeConnection(conn);
			return false;
		}
	}
	
	/**
	 *Procedure para atualizar um registro numa TABELA 
	 * */
	public boolean atualizar(Artista artista){
		conn = new ConnectionFactory().getConnection();
		sql= "UPDATE "+tabela+" SET" +
				" nomeArtista=?, loginCadastro=?, dataUltAlteracao=? "+
				" WHERE id=?";
		pstm = new PreparedStatementFactory().getPreparedStatement(conn, sql);
		
		try {
			pstm.setString(1, artista.getNomeArtista());
			pstm.setLong(2, artista.getLoginCadastro());
			pstm.setDate(3, new java.sql.Date(artista.getDataUltAlteracao().getTimeInMillis()));
			pstm.setLong(4, artista.getId());
			pstm.executeUpdate();
			pstm.close();
			new ConnectionFactory().closeConnection(conn);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			new ConnectionFactory().closeConnection(conn);
			return false;
		}
	}
	
	/**
	 * Procedure para buscar 1 registro numa TABELA
	 * */
	public Artista buscar(Long id){
		conn = new ConnectionFactory().getConnection();
		sql = "SELECT * FROM " + tabela + " WHERE id=?";
		pstm = new PreparedStatementFactory().getPreparedStatement(conn, sql);
		
		try {
			pstm.setLong(1, id);
			rs = pstm.executeQuery();
			
			Artista artista = new Artista();
			while (rs.next()) {
				artista = criar(conn, rs);				
			}
			if (rs!=null) {rs.close();}
			pstm.close();
			new ConnectionFactory().closeConnection(conn);
			return artista;
		} catch (SQLException e) {
			e.printStackTrace();
			new ConnectionFactory().closeConnection(conn);
			return null;
		}
	}

	/**
	 * Procedure para buscar 1 registro numa TABELA
	 * */
	public Artista buscar(String nomeArtista){
		conn = new ConnectionFactory().getConnection();
		sql = "SELECT * FROM " + tabela + " WHERE nomeArtista=?";
		pstm = new PreparedStatementFactory().getPreparedStatement(conn, sql);
		
		try {
			pstm.setString(1, nomeArtista);
			rs = pstm.executeQuery();
			
			Artista artista = new Artista();
			while (rs.next()) {
				artista = criar(conn, rs);				
			}
			if (rs!=null) {rs.close();}
			pstm.close();
			new ConnectionFactory().closeConnection(conn);
			return artista;
		} catch (SQLException e) {
			e.printStackTrace();
			new ConnectionFactory().closeConnection(conn);
			return null;
		}
	}
	
	/**
	 * Buscar todos os registros de uma TABELA
	 * */
	public List<Artista> buscarTodos(){
		conn = new ConnectionFactory().getConnection();
		sql = "SELECT * FROM " + tabela +" ORDER BY nomeArtista";
		pstm = new PreparedStatementFactory().getPreparedStatement(conn, sql);
		
		try {
			rs = pstm.executeQuery();
			List<Artista> list = new ArrayList<>();
			
			while (rs.next()) {
				list.add(criar(conn, rs));
			}
			
			if (rs!=null) {rs.close();}
			pstm.close();
			conn.close();
			new ConnectionFactory().closeConnection(conn);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			new ConnectionFactory().closeConnection(conn);
			return null;
		}
		
	}
	
	/**
	 * Buscar registros baseado em parte de uma campo numa TABELA
	 * 
	 * A buscar usa %+string+%
	 * */
	public List<Artista> buscarParte(String string){
		conn = new ConnectionFactory().getConnection();
		sql = "SELECT * FROM " + tabela + " WHERE nomeArtista like ? ORDER BY nomeArtista";
		pstm = new PreparedStatementFactory().getPreparedStatement(conn, sql);
		
		try {
			pstm.setString(1, "%"+string+"%");
			rs = pstm.executeQuery();
			
			List<Artista> list = new ArrayList<>();
			
			while (rs.next()) {
				list.add(criar(conn, rs));
			}
			
			if (rs!=null) {rs.close();}
			pstm.close();
			conn.close();
			new ConnectionFactory().closeConnection(conn);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			new ConnectionFactory().closeConnection(conn);
			return null;
		}
		
	}
	
	/**
	 * Converte o resultSet num objeto em memoria
	 * */
	private Artista criar(Connection conn, ResultSet rs){
		Artista temp = new Artista();
		try {
			temp.setId(rs.getLong("id"));
			
			Calendar dataCadastro = Calendar.getInstance();
			dataCadastro.setTime(rs.getDate("dataCadastro"));
			temp.setDataCadastro(dataCadastro);
			
			Calendar dataUltAlteracao = Calendar.getInstance();
			dataUltAlteracao.setTime(rs.getDate("dataUltAlteracao"));
			temp.setDataUltAlteracao(dataUltAlteracao);
			
			temp.setLoginCadastro(rs.getLong("loginCadastro"));
			
			temp.setNomeArtista(rs.getString("nomeArtista"));
			
			return temp;
		} catch (Exception e) {
			return null;
		}
	}
	
}

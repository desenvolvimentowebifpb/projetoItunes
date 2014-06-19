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
import br.com.model.produto.Genero;

public class GeneroDAO {
	private Connection conn;
	private PreparedStatement pstm;
	private String sql;
	private String tabela = "genero";
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
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Procedure para inserir um registro novo na TABELA
	 * */
	public boolean inserir(Genero genero){
		conn = new ConnectionFactory().getConnection();
		sql = "INSERT INTO "+ tabela +
				" (nomeGenero , loginCadastro, dataCadastro, dataUltAlteracao)"+ 
				" VALUES(" +
				"?,?,?,?)";
		pstm = new PreparedStatementFactory().getPreparedStatement(conn, sql);
		
		try {
			pstm.setString(1, genero.getNomeGenero());
			pstm.setLong(2, genero.getLoginCadastro());
			pstm.setDate(3, new java.sql.Date(genero.getDataCadastro().getTimeInMillis()));
			pstm.setDate(4, new java.sql.Date(genero.getDataUltAlteracao().getTimeInMillis()));
			pstm.execute();
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 *Procedure para atualizar um registro numa TABELA 
	 * */
	public boolean atualizar(Genero genero){
		conn = new ConnectionFactory().getConnection();
		sql= "UPDATE "+tabela+" SET" +
				" nomeGenero=?, loginCadastro=?, dataUltAlteracao=? "+
				" WHERE id=?";
		pstm = new PreparedStatementFactory().getPreparedStatement(conn, sql);
		
		try {
			pstm.setString(1, genero.getNomeGenero());
			pstm.setLong(2, genero.getLoginCadastro());
			pstm.setDate(3, new java.sql.Date(genero.getDataUltAlteracao().getTimeInMillis()));
			pstm.setLong(4, genero.getId());
			pstm.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Procedure para buscar 1 registro numa TABELA
	 * */
	public Genero buscar(Long id){
		conn = new ConnectionFactory().getConnection();
		sql = "SELECT * FROM " + tabela + " WHERE id=?";
		pstm = new PreparedStatementFactory().getPreparedStatement(conn, sql);
		
		try {
			pstm.setLong(1, id);
			rs = pstm.executeQuery();
			
			Genero genero = new Genero();
			while (rs.next()) {
				genero = criar(conn, rs);				
			}
			
			return genero;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Buscar todos os registros de uma TABELA
	 * */
	public List<Genero> buscarTodos(){
		conn = new ConnectionFactory().getConnection();
		sql = "SELECT * FROM " + tabela;
		pstm = new PreparedStatementFactory().getPreparedStatement(conn, sql);
		
		try {
			rs = pstm.executeQuery();
			List<Genero> list = new ArrayList<>();
			
			while (rs.next()) {
				list.add(criar(conn, rs));
			}
			
			if (rs!=null) {rs.close();}
			pstm.close();
			conn.close();
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * Buscar registros baseado em parte de uma campo numa TABELA
	 * 
	 * A buscar usa %+string+%
	 * */
	public List<Genero> buscarParte(String string){
		conn = new ConnectionFactory().getConnection();
		sql = "SELECT * FROM " + tabela + " WHERE nomeGenero like ?";
		pstm = new PreparedStatementFactory().getPreparedStatement(conn, sql);
		
		try {
			pstm.setString(1, "%"+string+"%");
			rs = pstm.executeQuery();
			
			List<Genero> list = new ArrayList<>();
			
			while (rs.next()) {
				list.add(criar(conn, rs));
			}
			
			if (rs!=null) {rs.close();}
			pstm.close();
			conn.close();
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Converte o resultSet num objeto em memoria
	 * */
	private Genero criar(Connection conn, ResultSet rs){
		Genero temp = new Genero();
		try {
			temp.setId(rs.getLong("id"));
			
			Calendar dataCadastro = Calendar.getInstance();
			dataCadastro.setTime(rs.getDate("dataCadastro"));
			temp.setDataCadastro(dataCadastro);
			
			Calendar dataUltAlteracao = Calendar.getInstance();
			dataUltAlteracao.setTime(rs.getDate("dataUltAlteracao"));
			temp.setDataUltAlteracao(dataUltAlteracao);
			
			temp.setLoginCadastro(rs.getLong("loginCadastro"));
			
			temp.setNomeGenero(rs.getString("nomeGenero"));
			
			return temp;
		} catch (Exception e) {
			return null;
		}
	}
}

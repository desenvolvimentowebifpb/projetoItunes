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
import br.com.model.produto.TipoProduto;

public class TipoProdutoDAO {
	private Connection conn;
	private PreparedStatement pstm;
	private String sql;
	private String tabela = "tipoproduto";
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
	public boolean inserir(TipoProduto tipoProduto){
		conn = new ConnectionFactory().getConnection();
		sql = "INSERT INTO "+ tabela +
				" (tipoProduto , loginCadastro, dataCadastro, dataUltAlteracao)"+ 
				" VALUES(" +
				"?,?,?,?)";
		pstm = new PreparedStatementFactory().getPreparedStatement(conn, sql);
		
		try {
			pstm.setString(1, tipoProduto.getTipoProduto());
			pstm.setLong(2, tipoProduto.getLoginCadastro());
			pstm.setDate(3, new java.sql.Date(tipoProduto.getDataCadastro().getTimeInMillis()));
			pstm.setDate(4, new java.sql.Date(tipoProduto.getDataUltAlteracao().getTimeInMillis()));
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
	public boolean atualizar(TipoProduto tipoProduto){
		conn = new ConnectionFactory().getConnection();
		sql= "UPDATE "+tabela+" SET" +
				" tipoProduto=?, loginCadastro=?, dataUltAlteracao=? "+
				" WHERE id=?";
		pstm = new PreparedStatementFactory().getPreparedStatement(conn, sql);
		
		try {
			pstm.setString(1, tipoProduto.getTipoProduto());
			pstm.setLong(2, tipoProduto.getLoginCadastro());
			pstm.setDate(3, new java.sql.Date(tipoProduto.getDataUltAlteracao().getTimeInMillis()));
			pstm.setLong(4, tipoProduto.getId());
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
	public TipoProduto buscar(Long id){
		conn = new ConnectionFactory().getConnection();
		sql = "SELECT * FROM " + tabela + " WHERE id=?";
		pstm = new PreparedStatementFactory().getPreparedStatement(conn, sql);
		
		try {
			pstm.setLong(1, id);
			rs = pstm.executeQuery();
			
			TipoProduto tipoProduto = new TipoProduto();
			while (rs.next()) {
				tipoProduto = criar(conn, rs);				
			}
			
			return tipoProduto;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Buscar todos os registros de uma TABELA
	 * */
	public List<TipoProduto> buscarTodos(){
		conn = new ConnectionFactory().getConnection();
		sql = "SELECT * FROM " + tabela;
		pstm = new PreparedStatementFactory().getPreparedStatement(conn, sql);
		
		try {
			rs = pstm.executeQuery();
			List<TipoProduto> list = new ArrayList<>();
			
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
	public List<TipoProduto> buscarParte(String string){
		conn = new ConnectionFactory().getConnection();
		sql = "SELECT * FROM " + tabela + " WHERE tipoProduto like ?";
		pstm = new PreparedStatementFactory().getPreparedStatement(conn, sql);
		
		try {
			pstm.setString(1, "%"+string+"%");
			rs = pstm.executeQuery();
			
			List<TipoProduto> list = new ArrayList<>();
			
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
	private TipoProduto criar(Connection conn, ResultSet rs){
		TipoProduto temp = new TipoProduto();
		try {
			temp.setId(rs.getLong("id"));
			
			Calendar dataCadastro = Calendar.getInstance();
			dataCadastro.setTime(rs.getDate("dataCadastro"));
			temp.setDataCadastro(dataCadastro);
			
			Calendar dataUltAlteracao = Calendar.getInstance();
			dataUltAlteracao.setTime(rs.getDate("dataUltAlteracao"));
			temp.setDataUltAlteracao(dataUltAlteracao);
			
			temp.setLoginCadastro(rs.getLong("loginCadastro"));
			
			temp.setTipoProduto(rs.getString("tipoProduto"));
			
			return temp;
		} catch (Exception e) {
			return null;
		}
	}
}

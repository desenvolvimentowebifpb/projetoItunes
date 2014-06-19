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
import br.com.model.pedido.FormaPagamento;


public class FormaPagamentoDAO {
	private Connection conn;
	private PreparedStatement pstm;
	private String sql;
	private String tabela = "formapagamento";
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
	public boolean inserir(FormaPagamento formaPagamento){
		conn = new ConnectionFactory().getConnection();
		sql = "INSERT INTO "+ tabela +
				" (formaPagamento, quantParcelas, loginCadastro, dataCadastro, dataUltAlteracao)"+ 
				"VALUES(" +
				"?,?,?,?,?)";
		pstm = new PreparedStatementFactory().getPreparedStatement(conn, sql);
		
		try {
			pstm.setString(1, formaPagamento.getFormaPagamento());
			pstm.setInt(2, formaPagamento.getQuantParcelas());
			pstm.setLong(3, formaPagamento.getLoginCadastro());
			pstm.setDate(4, new java.sql.Date(formaPagamento.getDataCadastro().getTimeInMillis()));
			pstm.setDate(5, new java.sql.Date(formaPagamento.getDataUltAlteracao().getTimeInMillis()));
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
	public boolean atualizar(FormaPagamento formaPagamento){
		conn = new ConnectionFactory().getConnection();
		sql= "UPDATE "+tabela+" SET" +
				" formaPagamento=?,quantParcelas=?,"
				+ " loginCadastro=?, dataUltAlteracao=?"+
				" WHERE id=?";
		pstm = new PreparedStatementFactory().getPreparedStatement(conn, sql);
		
		try {
			pstm.setString(1, formaPagamento.getFormaPagamento());
			pstm.setInt(2, formaPagamento.getQuantParcelas());
			pstm.setLong(3, formaPagamento.getLoginCadastro());
			pstm.setDate(4, new java.sql.Date(formaPagamento.getDataUltAlteracao().getTimeInMillis()));
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
	public FormaPagamento buscar(Long id){
		conn = new ConnectionFactory().getConnection();
		sql = "SELECT * FROM " + tabela + " WHERE id=?";
		pstm = new PreparedStatementFactory().getPreparedStatement(conn, sql);
		
		try {
			FormaPagamento formaPagamento = new FormaPagamento();
			pstm.setLong(1, id);
			rs = pstm.executeQuery();
			
			while (rs.next()) {
				formaPagamento=criar(conn, rs);
			}
			return formaPagamento;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Buscar todos os registros de uma TABELA
	 * */
	public List<FormaPagamento> buscarTodos(){
		conn = new ConnectionFactory().getConnection();
		sql = "SELECT * FROM " + tabela;
		pstm = new PreparedStatementFactory().getPreparedStatement(conn, sql);
		
		try {
			rs = pstm.executeQuery();
			
			List<FormaPagamento> list = new ArrayList<FormaPagamento>();
			while (rs.next()) {
				list.add(criar(conn, rs));
			}
			
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
	public List<FormaPagamento> buscarParte(String string){
		conn = new ConnectionFactory().getConnection();
		sql = "SELECT * FROM " + tabela + " WHERE formaPagamento like ?";
		pstm = new PreparedStatementFactory().getPreparedStatement(conn, sql);
		
		try {
			pstm.setString(1, "%"+string+"%");
			rs = pstm.executeQuery();
			
			List<FormaPagamento> list = new ArrayList<FormaPagamento>();
			
			while (rs.next()) {
				list.add(criar(conn, rs));
			}
			
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * Converte o resultSet num objeto em memoria
	 * */
	private FormaPagamento criar(Connection conn, ResultSet rs){
		FormaPagamento temp = new FormaPagamento();
		try {
			temp.setId(rs.getLong("id"));
			
			Calendar dataCadastro = Calendar.getInstance();
			dataCadastro.setTime(rs.getDate("dataCadastro"));
			temp.setDataCadastro(dataCadastro);
			
			Calendar dataUltAlteracao = Calendar.getInstance();
			dataUltAlteracao.setTime(rs.getDate("dataUltAlteracao"));
			temp.setDataUltAlteracao(dataUltAlteracao);
			
			temp.setLoginCadastro(rs.getLong("loginCadastro"));
			
			temp.setFormaPagamento(rs.getString("formaPagamento"));
			temp.setQuantParcelas(rs.getInt("quantParcelas"));
			return temp;
		} catch (Exception e) {
			return null;
		}
	}
}

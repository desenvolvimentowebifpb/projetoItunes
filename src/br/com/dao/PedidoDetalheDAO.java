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
import br.com.model.pedido.PedidoDetalhe;


public class PedidoDetalheDAO {
	private Connection conn;
	private PreparedStatement pstm;
	private String sql;
	private String tabela = "pedidoDetalhe";
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
	public boolean inserir(PedidoDetalhe pedidoDetalhe){
		conn = new ConnectionFactory().getConnection();
		sql = "INSERT INTO "+ tabela +
				" (codPedido,codItem,descricaoItem,quantItem,valorItem,"
				+ "valorDescontoItem,valorTotalItem, loginCadastro, dataCadastro, dataUltAlteracao)"+ 
				" VALUES(" +
				"?,?,?,?,?,"
				+ "?,?,?,?,?)";
		pstm = new PreparedStatementFactory().getPreparedStatement(conn, sql);
		
		try {
			pstm.setLong(1, pedidoDetalhe.getCodPedido());
			pstm.setLong(2, pedidoDetalhe.getCodItem());
			pstm.setString(3, pedidoDetalhe.getDescricaoItem());
			pstm.setInt(4, pedidoDetalhe.getQuantItem());
			pstm.setBigDecimal(5, pedidoDetalhe.getValorItem());
			pstm.setBigDecimal(6, pedidoDetalhe.getValorDescontoItem());
			pstm.setBigDecimal(7, pedidoDetalhe.getValorTotalItem());
			pstm.setLong(8, pedidoDetalhe.getLoginCadastro());
			pstm.setDate(9, new java.sql.Date(pedidoDetalhe.getDataCadastro().getTimeInMillis()));
			pstm.setDate(10, new java.sql.Date(pedidoDetalhe.getDataUltAlteracao().getTimeInMillis()));
			pstm.execute();
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	

	/**
	 * Procedure para buscar 1 registro numa TABELA
	 * */
	public PedidoDetalhe buscar(Long id){
		conn = new ConnectionFactory().getConnection();
		sql = "SELECT * FROM " + tabela + " WHERE id=?";
		pstm = new PreparedStatementFactory().getPreparedStatement(conn, sql);
		
		try {
			pstm.setLong(1, id);
			rs = pstm.executeQuery();
			
			PedidoDetalhe pedidoDetalhe = new PedidoDetalhe();
			while (rs.next()) {
				pedidoDetalhe = criar(conn, rs);				
			}
			
			return pedidoDetalhe;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Buscar todos os registros de uma TABELA
	 * */
	public List<PedidoDetalhe> buscarTodos(){
		conn = new ConnectionFactory().getConnection();
		sql = "SELECT * FROM " + tabela;
		pstm = new PreparedStatementFactory().getPreparedStatement(conn, sql);
		
		try {
			rs = pstm.executeQuery();
			List<PedidoDetalhe> list = new ArrayList<>();
			
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
	public List<PedidoDetalhe> buscarPedido(Long codPedido){
		conn = new ConnectionFactory().getConnection();
		sql = "SELECT * FROM " + tabela + " WHERE codPedido=?";
		pstm = new PreparedStatementFactory().getPreparedStatement(conn, sql);
		
		try {
			pstm.setLong(1, codPedido);
			rs = pstm.executeQuery();
			
			List<PedidoDetalhe> list = new ArrayList<>();
			
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
	private PedidoDetalhe criar(Connection conn, ResultSet rs){
		PedidoDetalhe temp = new PedidoDetalhe();
		try {
			temp.setId(rs.getLong("id"));
			
			Calendar dataCadastro = Calendar.getInstance();
			dataCadastro.setTime(rs.getDate("dataCadastro"));
			temp.setDataCadastro(dataCadastro);
			
			Calendar dataUltAlteracao = Calendar.getInstance();
			dataUltAlteracao.setTime(rs.getDate("dataUltAlteracao"));
			temp.setDataUltAlteracao(dataUltAlteracao);
			
			temp.setLoginCadastro(rs.getLong("loginCadastro"));
			
			temp.setCodPedido(rs.getLong("codPedido"));
			temp.setCodItem(rs.getLong("codItem"));
			temp.setDescricaoItem(rs.getString("descricaoItem"));
			temp.setQuantItem(rs.getInt("quantItem"));
			temp.setValorItem(rs.getBigDecimal("valorItem"));
			temp.setValorDescontoItem(rs.getBigDecimal("valorDescontoItem"));
			temp.setValorTotalItem(rs.getBigDecimal("valorTotalItem"));
			
			return temp;
		} catch (Exception e) {
			return null;
		}
	}
}

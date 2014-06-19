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
import br.com.model.pedido.Pedido;


public class PedidoDAO {
	private Connection conn;
	private PreparedStatement pstm;
	private String sql;
	private String tabela = "pedido";
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
	public boolean inserir(Pedido pedido){
		conn = new ConnectionFactory().getConnection();
		sql = "INSERT INTO "+ tabela +
				" (codCliente,valorTotal,valorTotalDesconto,quantItens,dataPedido,"
				+ "dataDownload,formaPagamento,loginCadastro, dataCadastro, dataUltAlteracao)"+ 
				" VALUES(" +
				"?,?,?,?,?,"
				+ "?,?,?,?,?)";
		pstm = new PreparedStatementFactory().getPreparedStatement(conn, sql);
		
		try {
			pstm.setLong(1, pedido.getCodCliente());
			pstm.setBigDecimal(2, pedido.getValorTotal());
			pstm.setBigDecimal(3, pedido.getValorTotalDesconto());
			pstm.setInt(4, pedido.getQuantItens());
			pstm.setDate(5, new java.sql.Date(pedido.getDataPedido().getTimeInMillis()));
			pstm.setDate(6, new java.sql.Date(pedido.getDataDownload().getTimeInMillis()));
			pstm.setLong(7, pedido.getFormaPagamento().getId());
			pstm.setLong(8, pedido.getLoginCadastro());
			pstm.setDate(9, new java.sql.Date(pedido.getDataCadastro().getTimeInMillis()));
			pstm.setDate(10, new java.sql.Date(pedido.getDataUltAlteracao().getTimeInMillis()));
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
	public Pedido buscar(Long id){
		conn = new ConnectionFactory().getConnection();
		sql = "SELECT * FROM " + tabela + " WHERE id=?";
		pstm = new PreparedStatementFactory().getPreparedStatement(conn, sql);
		
		try {
			pstm.setLong(1, id);
			rs = pstm.executeQuery();
			
			Pedido pedido = new Pedido();
			while (rs.next()) {
				pedido = criar(conn, rs);				
			}
			
			return pedido;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Buscar todos os registros de uma TABELA
	 * */
	public List<Pedido> buscarTodos(){
		conn = new ConnectionFactory().getConnection();
		sql = "SELECT * FROM " + tabela;
		pstm = new PreparedStatementFactory().getPreparedStatement(conn, sql);
		
		try {
			rs = pstm.executeQuery();
			List<Pedido> list = new ArrayList<>();
			
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
	private Pedido criar(Connection conn, ResultSet rs){
		Pedido temp = new Pedido();
		try {
			temp.setId(rs.getLong("id"));
			
			Calendar dataCadastro = Calendar.getInstance();
			dataCadastro.setTime(rs.getDate("dataCadastro"));
			temp.setDataCadastro(dataCadastro);
			
			Calendar dataUltAlteracao = Calendar.getInstance();
			dataUltAlteracao.setTime(rs.getDate("dataUltAlteracao"));
			temp.setDataUltAlteracao(dataUltAlteracao);
			
			temp.setLoginCadastro(rs.getLong("loginCadastro"));
			
			temp.setCodCliente(rs.getLong("codCliente"));
			temp.setValorTotal(rs.getBigDecimal("valorTotal"));
			temp.setValorTotalDesconto(rs.getBigDecimal("valorTotalDesconto"));
			temp.setQuantItens(rs.getInt("quantItens"));
			
			Calendar dataPedido = Calendar.getInstance();
			dataPedido.setTime(rs.getDate("dataPedido"));
			temp.setDataPedido(dataPedido);
			
			Calendar dataDownload = Calendar.getInstance();
			dataDownload.setTime(rs.getDate("dataDownload"));
			temp.setDataDownload(dataDownload);
			
			temp.setFormaPagamento(new FormaPagamentoDAO().buscar(rs.getLong("formaPagamento")));
			temp.setListItens(new PedidoDetalheDAO().buscarPedido(temp.getId()));
			
			return temp;
		} catch (Exception e) {
			return null;
		}
	}
}

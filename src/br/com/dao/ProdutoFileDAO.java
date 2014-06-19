package br.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import br.com.factory.ConnectionFactory;
import br.com.factory.PreparedStatementFactory;
import br.com.model.produto.ProdutoFile;

public class ProdutoFileDAO {
	private Connection conn;
	private PreparedStatement pstm;
	private String sql;
	private String tabela = "produtofile";
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
	public boolean inserir(ProdutoFile produtoFile){
		conn = new ConnectionFactory().getConnection();
		sql = "INSERT INTO "+ tabela +
				" (codProduto,arquivo , loginCadastro, dataCadastro, dataUltAlteracao)"+ 
				" VALUES(" +
				"?,?,?,?)";
		pstm = new PreparedStatementFactory().getPreparedStatement(conn, sql);
		
		try {
			pstm.setLong(1, produtoFile.getCodProduto());
			java.sql.Blob blob = new SerialBlob(produtoFile.getArquivo());
			pstm.setBlob(2, blob);
			pstm.setLong(3, produtoFile.getLoginCadastro());
			pstm.setDate(4, new java.sql.Date(produtoFile.getDataCadastro().getTimeInMillis()));
			pstm.setDate(5, new java.sql.Date(produtoFile.getDataUltAlteracao().getTimeInMillis()));
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
	public boolean atualizar(ProdutoFile produtoFile){
		conn = new ConnectionFactory().getConnection();
		sql= "UPDATE "+tabela+" SET" +
				" arquivo=?, loginCadastro=?, dataUltAlteracao=? "+
				" WHERE id=?";
		pstm = new PreparedStatementFactory().getPreparedStatement(conn, sql);
		
		try {
			java.sql.Blob blob = new SerialBlob(produtoFile.getArquivo());
			pstm.setBlob(1, blob);
			pstm.setLong(2, produtoFile.getLoginCadastro());
			pstm.setDate(3, new java.sql.Date(produtoFile.getDataUltAlteracao().getTimeInMillis()));
			pstm.setLong(4, produtoFile.getId());
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
	public ProdutoFile buscar(Long id){
		conn = new ConnectionFactory().getConnection();
		sql = "SELECT * FROM " + tabela + " WHERE id=?";
		pstm = new PreparedStatementFactory().getPreparedStatement(conn, sql);
		
		try {
			pstm.setLong(1, id);
			rs = pstm.executeQuery();
			
			ProdutoFile produtoFile = new ProdutoFile();
			while (rs.next()) {
				produtoFile = criar(conn, rs);				
			}
			
			return produtoFile;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Procedure para buscar 1 registro numa TABELA
	 * */
	public ProdutoFile buscarCodProduto(Long codProduto){
		conn = new ConnectionFactory().getConnection();
		sql = "SELECT * FROM " + tabela + " WHERE codProduto=?";
		pstm = new PreparedStatementFactory().getPreparedStatement(conn, sql);
		
		try {
			pstm.setLong(1, codProduto);
			rs = pstm.executeQuery();
			
			ProdutoFile produtoFile = new ProdutoFile();
			while (rs.next()) {
				produtoFile = criar(conn, rs);				
			}
			
			return produtoFile;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Buscar todos os registros de uma TABELA
	 * */
	public List<ProdutoFile> buscarTodos(){
		conn = new ConnectionFactory().getConnection();
		sql = "SELECT * FROM " + tabela;
		pstm = new PreparedStatementFactory().getPreparedStatement(conn, sql);
		
		try {
			rs = pstm.executeQuery();
			List<ProdutoFile> list = new ArrayList<>();
			
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
	private ProdutoFile criar(Connection conn, ResultSet rs){
		ProdutoFile temp = new ProdutoFile();
		try {
			temp.setId(rs.getLong("id"));
			
			Calendar dataCadastro = Calendar.getInstance();
			dataCadastro.setTime(rs.getDate("dataCadastro"));
			temp.setDataCadastro(dataCadastro);
			
			Calendar dataUltAlteracao = Calendar.getInstance();
			dataUltAlteracao.setTime(rs.getDate("dataUltAlteracao"));
			temp.setDataUltAlteracao(dataUltAlteracao);
			
			temp.setLoginCadastro(rs.getLong("loginCadastro"));
			
			temp.setCodProduto(rs.getLong("codProduto"));
			
			java.sql.Blob blob = rs.getBlob("arquivo");
			byte[] arquivo;
			ByteOutputStream bout = new ByteOutputStream();
			bout.write(blob.getBinaryStream());
			arquivo = bout.getBytes();
			bout.close();
			temp.setArquivo(arquivo);
			
			return temp;
		} catch (Exception e) {
			return null;
		}
	}
}

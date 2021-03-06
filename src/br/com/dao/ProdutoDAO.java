package br.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;

import br.com.factory.ConnectionFactory;
import br.com.factory.PreparedStatementFactory;
import br.com.model.produto.Produto;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;


public class ProdutoDAO {
	private Connection conn;
	private PreparedStatement pstm;
	private String sql;
	private String tabela = "produto";
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
			return false;
		}
	}
	
	/**
	 * Procedure para inserir um registro novo na TABELA
	 * */
	public boolean inserir(Produto produto){
		conn = new ConnectionFactory().getConnection();
		sql = "INSERT INTO "+ tabela +
				" (descricao, image, artista, genero, tipoProduto , "
				+ "precoPadrao, precoPromocional,loginCadastro, dataCadastro, dataUltAlteracao)"+ 
				" VALUES(" +
				"?,?,?,?,?,"
				+ "?,?,?,?,?)";
		pstm = new PreparedStatementFactory().getPreparedStatement(conn, sql);
		
		try {
			pstm.setString(1, produto.getDescricao());
			java.sql.Blob blob = new SerialBlob(produto.getImage());
			pstm.setBlob(2, blob);
			pstm.setLong(3, produto.getArtista().getId());
			pstm.setLong(4, produto.getGenero().getId());
			pstm.setLong(5, produto.getTipoProduto().getId());
			pstm.setBigDecimal(6, produto.getPrecoPadrao());
			pstm.setBigDecimal(7, produto.getPrecoPromocional());
			pstm.setLong(8, produto.getLoginCadastro());
			pstm.setDate(9, new java.sql.Date(produto.getDataCadastro().getTimeInMillis()));
			pstm.setDate(10, new java.sql.Date(produto.getDataUltAlteracao().getTimeInMillis()));
			pstm.execute();
			
			pstm.close();
			new ConnectionFactory().closeConnection(conn);
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 *Procedure para atualizar um registro numa TABELA 
	 * */
	public boolean atualizar(Produto produto){
		conn = new ConnectionFactory().getConnection();
		sql= "UPDATE "+tabela+" SET" +
				"  image, artista, genero, tipoProduto , "
				+ "precoPadrao, precoPromocional,loginCadastro, dataUltAlteracao"
				+
				" WHERE id=?";
		pstm = new PreparedStatementFactory().getPreparedStatement(conn, sql);
		
		try {
			java.sql.Blob blob = new SerialBlob(produto.getImage());
			pstm.setBlob(1, blob);
			pstm.setLong(2, produto.getArtista().getId());
			pstm.setLong(3, produto.getGenero().getId());
			pstm.setLong(4, produto.getTipoProduto().getId());
			pstm.setBigDecimal(5, produto.getPrecoPadrao());
			pstm.setBigDecimal(6, produto.getPrecoPromocional());
			pstm.setLong(7, produto.getLoginCadastro());
			pstm.setDate(8, new java.sql.Date(produto.getDataUltAlteracao().getTimeInMillis()));
			pstm.setLong(9, produto.getId());
			pstm.executeUpdate();
			
			pstm.close();
			new ConnectionFactory().closeConnection(conn);
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Procedure para buscar 1 registro numa TABELA
	 * */
	public Produto buscar(Long id){
		conn = new ConnectionFactory().getConnection();
		sql = "SELECT * FROM " + tabela + " WHERE id= ?";
		pstm = new PreparedStatementFactory().getPreparedStatement(conn, sql);
		
		try {
			pstm.setLong(1, id);
			rs = pstm.executeQuery();
			
			Produto produto = null;
			while (rs.next()) {
				produto = criar(conn, rs);	
			}
			
			if (rs!=null) {rs.close();}
			pstm.close();
			new ConnectionFactory().closeConnection(conn);
			
			return produto;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Procedure para buscar 1 registro numa TABELA
	 * */
	public Produto buscar(String descricao){
		conn = new ConnectionFactory().getConnection();
		sql = "SELECT * FROM " + tabela + " WHERE descricao= ?";
		pstm = new PreparedStatementFactory().getPreparedStatement(conn, sql);
		
		try {
			pstm.setString(1, descricao);
			rs = pstm.executeQuery();
			
			Produto produto=null;
			while (rs.next()) {
				produto = criar(conn, rs);	
			}
			
			if (rs!=null) {rs.close();}
			pstm.close();
			new ConnectionFactory().closeConnection(conn);
			
			return produto;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Buscar todos os registros de uma TABELA
	 * */
	public List<Produto> buscarTodos(){
		conn = new ConnectionFactory().getConnection();
		sql = "SELECT * FROM " + tabela;
		pstm = new PreparedStatementFactory().getPreparedStatement(conn, sql);
		
		try {
			rs = pstm.executeQuery();
			List<Produto> list = new ArrayList<>();
			
			while (rs.next()) {
				list.add(criar(conn, rs));
			}
			
			if (rs!=null) {rs.close();}
			pstm.close();
			new ConnectionFactory().closeConnection(conn);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Buscar todos os registros de uma TABELA
	 * */
	public List<Produto> buscarTodosPromocao(){
		conn = new ConnectionFactory().getConnection();
		sql = "SELECT * FROM " + tabela+" WHERE precoPromocional < precoPadrao";
		pstm = new PreparedStatementFactory().getPreparedStatement(conn, sql);
		
		try {
			rs = pstm.executeQuery();
			List<Produto> list = new ArrayList<>();
			
			while (rs.next()) {
				list.add(criar(conn, rs));
			}
			
			if (rs!=null) {rs.close();}
			pstm.close();
			new ConnectionFactory().closeConnection(conn);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Buscar todos os registros de uma TABELA
	 * */
	public List<Produto> buscarTodosArtista(Long idArtista){
		conn = new ConnectionFactory().getConnection();
		sql = "SELECT * FROM " + tabela+" WHERE artista=?";
		pstm = new PreparedStatementFactory().getPreparedStatement(conn, sql);
		
		try {
			pstm.setLong(1, idArtista);
			rs = pstm.executeQuery();
			List<Produto> list = new ArrayList<>();
			
			while (rs.next()) {
				list.add(criarSemImagem(conn, rs));
			}
			
			if (rs!=null) {rs.close();}
			pstm.close();
			new ConnectionFactory().closeConnection(conn);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Buscar todos os registros de uma TABELA
	 * */
	public List<Produto> buscarTodosGenero(Long idGenero){
		conn = new ConnectionFactory().getConnection();
		sql = "SELECT * FROM " + tabela+" WHERE genero=?";
		pstm = new PreparedStatementFactory().getPreparedStatement(conn, sql);
		
		try {
			pstm.setLong(1, idGenero);
			rs = pstm.executeQuery();
			List<Produto> list = new ArrayList<>();
			
			while (rs.next()) {
				list.add(criarSemImagem(conn, rs));
			}
			
			if (rs!=null) {rs.close();}
			pstm.close();
			new ConnectionFactory().closeConnection(conn);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Buscar todos os registros de uma TABELA
	 * */
	public List<Produto> buscarTodosComMp3(){
		conn = new ConnectionFactory().getConnection();
		sql = "SELECT * FROM " + tabela;
		pstm = new PreparedStatementFactory().getPreparedStatement(conn, sql);
		
		try {
			rs = pstm.executeQuery();
			List<Produto> list = new ArrayList<>();
			
			while (rs.next()) {
				Produto tempProduto = new Produto();
				tempProduto = criar(conn, rs);
				if (new ProdutoFileDAO().validatedExist(tempProduto.getId())==false) {
					list.add(tempProduto);
				}
			}
			
			if (rs!=null) {rs.close();}
			pstm.close();
			new ConnectionFactory().closeConnection(conn);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Buscar todos os registros de uma TABELA
	 * */
	public List<Produto> buscar9ItensComMP3(){
		conn = new ConnectionFactory().getConnection();
		sql = "SELECT * FROM " + tabela + " ORDER BY dataCadastro LIMIT 14";
		pstm = new PreparedStatementFactory().getPreparedStatement(conn, sql);
		
		try {
			rs = pstm.executeQuery();
			List<Produto> list = new ArrayList<>();
			
			while (rs.next()) {
				Produto tempProduto = new Produto();
				tempProduto = criarSemImagem(conn, rs);
				if (new ProdutoFileDAO().validatedExist(tempProduto.getId())==true) {
					list.add(tempProduto);
				}
			}
			
			Collections.shuffle(list);
			
			while (list.size()%3!=0 && list.size()>12) {
				list.add(list.get(0));
				Collections.shuffle(list);
			}
			
			if (rs!=null) {rs.close();}
			pstm.close();
			new ConnectionFactory().closeConnection(conn);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Buscar todos os registros de uma TABELA
	 * */
	public List<Produto> buscar9ItensComMP3Promocao(){
		conn = new ConnectionFactory().getConnection();
		sql = "SELECT * FROM " + tabela + " WHERE precoPromocional < precoPadrao ORDER BY dataCadastro LIMIT 14";
		pstm = new PreparedStatementFactory().getPreparedStatement(conn, sql);
		
		try {
			rs = pstm.executeQuery();
			List<Produto> list = new ArrayList<>();
			
			while (rs.next()) {
				Produto tempProduto = new Produto();
				tempProduto = criarSemImagem(conn, rs);
				if (new ProdutoFileDAO().validatedExist(tempProduto.getId())==true) {
					list.add(tempProduto);
				}
			}
			
			Collections.shuffle(list);
			
			while (list.size()%3!=0 && list.size()>12) {
				list.add(list.get(0));
				Collections.shuffle(list);
			}
			
			if (rs!=null) {rs.close();}
			pstm.close();
			new ConnectionFactory().closeConnection(conn);
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
	public List<Produto> buscarParte(String string){
		conn = new ConnectionFactory().getConnection();
		sql = "SELECT * FROM " + tabela + " WHERE descricao like ?";
		pstm = new PreparedStatementFactory().getPreparedStatement(conn, sql);
		
		try {
			pstm.setString(1, "%"+string+"%");
			rs = pstm.executeQuery();
			
			List<Produto> list = new ArrayList<>();
			
			while (rs.next()) {
				list.add(criar(conn, rs));
			}
			
			if (rs!=null) {rs.close();}
			pstm.close();
			new ConnectionFactory().closeConnection(conn);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Converte o resultSet num objeto em memoria
	 * */
	private Produto criar(Connection conn, ResultSet rs){
		Produto temp = new Produto();
		try {
			temp.setId(rs.getLong("id"));
			
			Calendar dataCadastro = Calendar.getInstance();
			dataCadastro.setTime(rs.getDate("dataCadastro"));
			temp.setDataCadastro(dataCadastro);
			
			Calendar dataUltAlteracao = Calendar.getInstance();
			dataUltAlteracao.setTime(rs.getDate("dataUltAlteracao"));
			temp.setDataUltAlteracao(dataUltAlteracao);
			
			temp.setLoginCadastro(rs.getLong("loginCadastro"));
			
			java.sql.Blob blob = rs.getBlob("image");
			byte[] img;
			ByteOutputStream bout = new ByteOutputStream();
			bout.write(blob.getBinaryStream());
			img = bout.getBytes();
			bout.close();
			temp.setImage(img);
			
			temp.setDescricao(rs.getString("descricao"));
			temp.setArtista(new ArtistaDAO().buscar(rs.getLong("artista")));
			temp.setGenero(new GeneroDAO().buscar(rs.getLong("genero")));
			temp.setTipoProduto(new TipoProdutoDAO().buscar(rs.getLong("tipoProduto")));
			temp.setPrecoPadrao(rs.getBigDecimal("precoPadrao"));
			temp.setPrecoPromocional(rs.getBigDecimal("precoPromocional"));
			
			return temp;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Converte o resultSet num objeto em memoria
	 * */
	private Produto criarSemImagem(Connection conn, ResultSet rs){
		Produto temp = new Produto();
		try {
			temp.setId(rs.getLong("id"));
			
			Calendar dataCadastro = Calendar.getInstance();
			dataCadastro.setTime(rs.getDate("dataCadastro"));
			temp.setDataCadastro(dataCadastro);
			
			Calendar dataUltAlteracao = Calendar.getInstance();
			dataUltAlteracao.setTime(rs.getDate("dataUltAlteracao"));
			temp.setDataUltAlteracao(dataUltAlteracao);
			
			temp.setLoginCadastro(rs.getLong("loginCadastro"));
			
			temp.setDescricao(rs.getString("descricao"));
			temp.setArtista(new ArtistaDAO().buscar(rs.getLong("artista")));
			temp.setGenero(new GeneroDAO().buscar(rs.getLong("genero")));
			temp.setTipoProduto(new TipoProdutoDAO().buscar(rs.getLong("tipoProduto")));
			temp.setPrecoPadrao(rs.getBigDecimal("precoPadrao"));
			temp.setPrecoPromocional(rs.getBigDecimal("precoPromocional"));
			
			return temp;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}

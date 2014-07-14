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
import br.com.model.pessoa.Usuario;

public class UsuarioDAO {
	private Connection conn;
	private PreparedStatement pstm;
	private String sql;
	private String tabela = "usuario";
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
	public boolean inserir(Usuario usuario){
		conn = new ConnectionFactory().getConnection();
		sql = "INSERT INTO "+ tabela +
				" (login, senha, email, lembreteSenha, tipoUsuario,"
				+ "loginCadastro, dataCadastro, dataUltAlteracao)"+ 
				"VALUES(" +
				"?,?,?,?,?,"
				+ "?,?,?)";
		pstm = new PreparedStatementFactory().getPreparedStatement(conn, sql);
		
		try {
			pstm.setString(1, usuario.getLogin());
			pstm.setString(2, usuario.getSenha());
			pstm.setString(3, usuario.getEmail());
			pstm.setString(4, usuario.getLembreteSenha());
			pstm.setLong(5, usuario.getTipoUsuario().getId());
			pstm.setLong(6, usuario.getLoginCadastro());
			pstm.setDate(7, new java.sql.Date(usuario.getDataCadastro().getTimeInMillis()));
			pstm.setDate(8, new java.sql.Date(usuario.getDataUltAlteracao().getTimeInMillis()));
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
	public boolean atualizar(Usuario usuario){
		conn = new ConnectionFactory().getConnection();
		sql= "UPDATE "+tabela+" SET" +
				" senha=?, lembreteSenha=?, loginCadastro=?, dataUltAlteracao=? "+
				" WHERE id=?";
		pstm = new PreparedStatementFactory().getPreparedStatement(conn, sql);
		
		try {
			pstm.setString(1, usuario.getSenha());
			pstm.setString(2, usuario.getLembreteSenha());
			pstm.setLong(3, usuario.getLoginCadastro());
			pstm.setDate(4, new java.sql.Date(usuario.getDataUltAlteracao().getTimeInMillis()));
			pstm.setLong(5, new Long(1));
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
	public Usuario buscar(Long id){
		conn = new ConnectionFactory().getConnection();
		sql = "SELECT * FROM " + tabela + " WHERE id=?";
		pstm = new PreparedStatementFactory().getPreparedStatement(conn, sql);
		
		try {
			Usuario usuario = new Usuario();
			pstm.setLong(1, id);
			rs = pstm.executeQuery();
			
			while (rs.next()) {
				usuario=criar(conn, rs);
			}
			
			if (rs!=null) {rs.close();}
			pstm.close();
			new ConnectionFactory().closeConnection(conn);
			return usuario;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Procedure para buscar 1 registro numa TABELA
	 * */
	public Usuario buscar(String login){
		conn = new ConnectionFactory().getConnection();
		sql = "SELECT * FROM " + tabela + " WHERE login=?";
		pstm = new PreparedStatementFactory().getPreparedStatement(conn, sql);
		
		try {
			Usuario usuario = null;
			pstm.setString(1, login);
			rs = pstm.executeQuery();
			
			while (rs.next()) {
				usuario=criar(conn, rs);
			}
			
			if (rs!=null) {rs.close();}
			pstm.close();
			new ConnectionFactory().closeConnection(conn);
			return usuario;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Buscar todos os registros de uma TABELA
	 * */
	public List<Usuario> buscarTodos(){
		conn = new ConnectionFactory().getConnection();
		sql = "SELECT * FROM " + tabela;
		pstm = new PreparedStatementFactory().getPreparedStatement(conn, sql);
		
		try {
			rs = pstm.executeQuery();
			List<Usuario> list = new ArrayList<Usuario>();
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
	 * Buscar registros baseado em parte de uma campo numa TABELA
	 * 
	 * A buscar usa %+string+%
	 * */
	public List<Usuario> buscarParte(String string){
		conn = new ConnectionFactory().getConnection();
		sql = "SELECT * FROM " + tabela + " WHERE login like ?";
		pstm = new PreparedStatementFactory().getPreparedStatement(conn, sql);
		
		try {
			pstm.setString(1, "%"+string+"%");
			rs = pstm.executeQuery();
			List<Usuario> list = new ArrayList<Usuario>();
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
	private Usuario criar(Connection conn, ResultSet rs){
		Usuario temp = new Usuario();
		try {
			temp.setId(rs.getLong("id"));
			
			Calendar dataCadastro = Calendar.getInstance();
			dataCadastro.setTime(rs.getDate("dataCadastro"));
			temp.setDataCadastro(dataCadastro);
			
			Calendar dataUltAlteracao = Calendar.getInstance();
			dataUltAlteracao.setTime(rs.getDate("dataUltAlteracao"));
			temp.setDataUltAlteracao(dataUltAlteracao);
			
			temp.setLoginCadastro(rs.getLong("loginCadastro"));
			
			temp.setLogin(rs.getString("login"));
			temp.setSenha(rs.getString("senha"));
			temp.setEmail(rs.getString("email"));
			temp.setLembreteSenha(rs.getString("lembreteSenha"));
			temp.setTipoUsuario(new TipoUsuarioDAO().buscar(rs.getLong("tipoUsuario")));
			
			return temp;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}

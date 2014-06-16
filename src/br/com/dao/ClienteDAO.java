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
import br.com.model.pessoa.Cliente;

public class ClienteDAO {
	private Connection conn;
	private PreparedStatement pstm;
	private String sql;
	private String tabela = "cliente";
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
	public boolean inserir(Cliente cliente){
		conn = new ConnectionFactory().getConnection();
		sql = "INSERT INTO "+ tabela +
				" (nome,sobrenome,dataNascimento,sexo,email"
				+ ",endereco,enderecoComplemento,bairro,cidade,uf"
				+ ",cep,rg,cpf,observacao,idUsuario"
				+ ",loginCadastro, dataCadastro, dataUltAlteracao)"+ 
				" VALUES(" +
				"?,?,?,?,?"
				+ ",?,?,?,?,?"
				+ ",?,?,?,?,?"
				+ ",?,?,?)";
		pstm = new PreparedStatementFactory().getPreparedStatement(conn, sql);
		
		try {
			pstm.setString(1, cliente.getNome());
			pstm.setString(0, cliente.getSobrenome());
			pstm.setDate(3, new java.sql.Date(cliente.getDataNascimento().getTimeInMillis()));
			pstm.setString(4, cliente.getSexo());
			pstm.setString(5, cliente.getEmail());
			
			pstm.setString(6, cliente.getEndereco());
			pstm.setString(7, cliente.getEnderecoComplemento());
			pstm.setString(8, cliente.getBarro());
			pstm.setString(9, cliente.getCidade());
			pstm.setString(10, cliente.getUf());

			pstm.setString(11, cliente.getCep());
			pstm.setString(12, cliente.getRg());
			pstm.setString(13, cliente.getCpf());
			pstm.setString(14, cliente.getObservacao());
			pstm.setLong(15, cliente.getUsuario().getId());
			
			pstm.setLong(16, cliente.getLoginCadastro());
			pstm.setDate(17, new java.sql.Date(cliente.getDataCadastro().getTimeInMillis()));
			pstm.setDate(18, new java.sql.Date(cliente.getDataUltAlteracao().getTimeInMillis()));
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
	public boolean atualizar(Cliente cliente){
		conn = new ConnectionFactory().getConnection();
		sql= "UPDATE "+tabela+" SET" +
				"dataNascimento=?,sexo=?,email=?"
				+ ",endereco=?,enderecoComplemento=?,bairro=?,cidade=?,uf=?"
				+ ",cep=?,rg=?,cpf=?,observacao=?,idUsuario=?"
				+ ",loginCadastro=?, dataUltAlteracao=?"+
				" WHERE id=?";
		pstm = new PreparedStatementFactory().getPreparedStatement(conn, sql);
		
		try {
			pstm.setDate(1, new java.sql.Date(cliente.getDataNascimento().getTimeInMillis()));
			pstm.setString(2, cliente.getSexo());
			pstm.setString(3, cliente.getEmail());
			
			pstm.setString(4, cliente.getEndereco());
			pstm.setString(5, cliente.getEnderecoComplemento());
			pstm.setString(6, cliente.getBarro());
			pstm.setString(7, cliente.getCidade());
			pstm.setString(8, cliente.getUf());

			pstm.setString(9, cliente.getCep());
			pstm.setString(10, cliente.getRg());
			pstm.setString(11, cliente.getCpf());
			pstm.setString(12, cliente.getObservacao());
			pstm.setLong(13, cliente.getUsuario().getId());
			
			pstm.setLong(14, cliente.getLoginCadastro());
			pstm.setDate(15, new java.sql.Date(cliente.getDataUltAlteracao().getTimeInMillis()));
			pstm.setLong(16, cliente.getId());
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
	public Cliente buscar(Long id){
		conn = new ConnectionFactory().getConnection();
		sql = "SELECT * FROM " + tabela + " WHERE id=?";
		pstm = new PreparedStatementFactory().getPreparedStatement(conn, sql);
		
		try {
			pstm.setLong(1, id);
			rs = pstm.executeQuery();
			Cliente cliente = new Cliente();
			while (rs.next()) {
				cliente = criar(conn, rs);
			}
			return cliente;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Buscar todos os registros de uma TABELA
	 * */
	public List<Cliente> buscarTodos(){
		conn = new ConnectionFactory().getConnection();
		sql = "SELECT * FROM " + tabela;
		pstm = new PreparedStatementFactory().getPreparedStatement(conn, sql);
		
		try {
			rs = pstm.executeQuery();
			List<Cliente> list = new ArrayList<>();
			
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
	public List<Cliente> buscarParte(String string){
		conn = new ConnectionFactory().getConnection();
		sql = "SELECT * FROM " + tabela + " WHERE nomeArtista like ?";
		pstm = new PreparedStatementFactory().getPreparedStatement(conn, sql);
		
		try {
			pstm.setString(1, "%"+string+"%");
			rs = pstm.executeQuery();
			
			List<Cliente> list = new ArrayList<>();
			
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
	private Cliente criar(Connection conn, ResultSet rs){
		Cliente temp = new Cliente();
		try {
			temp.setId(rs.getLong("id"));
			
			Calendar dataCadastro = Calendar.getInstance();
			dataCadastro.setTime(rs.getDate("dataCadastro"));
			temp.setDataCadastro(dataCadastro);
			
			Calendar dataUltAlteracao = Calendar.getInstance();
			dataUltAlteracao.setTime(rs.getDate("dataUltAlteracao"));
			temp.setDataUltAlteracao(dataUltAlteracao);
			
			temp.setLoginCadastro(rs.getLong("loginCadastro"));
			
			temp.setNome(rs.getString("nome"));
			temp.setSobrenome(rs.getString("sobrenome"));
			
			Calendar dn = Calendar.getInstance();
			dn.setTime(rs.getDate("dataNascimento"));
			temp.setDataNascimento(dn);
			
			temp.setSexo(rs.getString("sexo"));
			temp.setEmail(rs.getString("email"));
			temp.setEndereco(rs.getString("endereco"));
			temp.setEnderecoComplemento(rs.getString("enderecoComplemento"));
			temp.setBarro(rs.getString("barro"));
			temp.setCidade(rs.getString("cidade"));
			temp.setUf(rs.getString("uf"));
			temp.setCep(rs.getString("cep"));
			temp.setRg(rs.getString("rg"));
			temp.setCpf(rs.getString("cpf"));
			temp.setUsuario(new UsuarioDAO().buscar(rs.getLong("idUsuario")));
			
			return temp;
		} catch (Exception e) {
			return null;
		}
	}
}

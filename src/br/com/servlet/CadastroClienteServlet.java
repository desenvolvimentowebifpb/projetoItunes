package br.com.servlet;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.actions.EncryptPassword;
import br.com.converters.CalendarToDate;
import br.com.dao.ClienteDAO;
import br.com.dao.TipoUsuarioDAO;
import br.com.dao.UsuarioDAO;
import br.com.model.pessoa.Cliente;
import br.com.model.pessoa.Usuario;
import br.com.validated.ClienteValidated;

/**
 * Servlet implementation class CadastroClienteServlet
 */
@WebServlet("/cc.do")
public class CadastroClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CadastroClienteServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			Cliente cliente = new Cliente();
			Usuario usuario = new Usuario();
			
			cliente.setNome((String) request.getParameter("nome"));
			System.out.println(request.getParameter("nome"));
			cliente.setSobrenome((String) request.getParameter("sobrenome"));
			System.out.println(request.getParameter("sobrenome"));
			
			String dtaNascimento = request.getParameter("dataNascimento");
			if (dtaNascimento==null || dtaNascimento.trim().isEmpty()) {
				cliente.setDataCadastro(Calendar.getInstance());
			}else{
				Calendar dta = Calendar.getInstance();
				dta.setTimeInMillis(CalendarToDate.StringToDate(dtaNascimento).getTimeInMillis());
				cliente.setDataNascimento(dta);
			}
			
			cliente.setSexo(request.getParameter("cbSexo"));
			cliente.setEmail(request.getParameter("email"));
			cliente.setEndereco(request.getParameter("endereco"));
			cliente.setEnderecoComplemento("enderecoComplemento");
			cliente.setBarro(request.getParameter("bairro"));
			cliente.setCidade(request.getParameter("cidade"));
			cliente.setUf(request.getParameter("cbUf"));
			cliente.setCep(request.getParameter("cep"));
			cliente.setRg(request.getParameter("rg"));
			cliente.setCpf(request.getParameter("cpf"));
			cliente.setObservacao(" ");
			cliente.setDataCadastro(Calendar.getInstance());
			cliente.setDataUltAlteracao(Calendar.getInstance());
			cliente.setLoginCadastro(new Long(1));
			
			usuario.setLogin(request.getParameter("usuario"));
			usuario.setEmail(cliente.getEmail());
			usuario.setDataCadastro(Calendar.getInstance());
			usuario.setDataUltAlteracao(Calendar.getInstance());
			usuario.setLembreteSenha(request.getParameter("lembreteSenha"));
			
			String senha = request.getParameter("senha");
			String confirmaSenha = request.getParameter("confirmaSenha");
			
			if (senha!= null || confirmaSenha!= null) {
				if (!senha.trim().isEmpty() || !confirmaSenha.trim().isEmpty()) {
					if (senha.equals(confirmaSenha)) {
						usuario.setSenha(new EncryptPassword().encrypt(senha));
					}else{
						usuario.setSenha(null);
					}
				}else{
					usuario.setSenha(null);
				}
			}else{
				usuario.setSenha(null);
			}
			
			usuario.setLoginCadastro(new Long(1));
			usuario.setTipoUsuario(new TipoUsuarioDAO().buscar(new Long(1)));
			cliente.setUsuario(usuario);
			
			HashMap<String, String> map = new ClienteValidated().isValid(cliente);
			if (map.get("boolean").equals("true")) {
				new UsuarioDAO().inserir(usuario);
				usuario = new UsuarioDAO().buscar(usuario.getLogin());
				new ClienteDAO().inserir(cliente);
				cliente.setUsuario(usuario);
				
				request.setAttribute("cliente", cliente);
				request.getRequestDispatcher("./cadastro_cliente_view.jsp").forward(request, response);
			}else{
				request.setAttribute("message", map.get("message"));
				request.getRequestDispatcher("./cadastro_artista_error.jsp").forward(request, response);
			}
			
			
	}

}

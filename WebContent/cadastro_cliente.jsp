<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.*" %>
<%@ page import="br.com.dao.GeneroDAO" %>
<%@ page import="br.com.model.produto.Genero" %>
<%@ page import="br.com.dao.ArtistaDAO" %>
<%@ page import="br.com.model.pessoa.Artista" %>
<%@ page import="br.com.dao.TipoProdutoDAO" %>
<%@ page import="br.com.model.produto.TipoProduto" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta http-equiv="imagetoolbar" content="no" />
		<link rel="stylesheet" type="text/css" href="./styles/layout.css">
		<link rel="stylesheet" type="text/css" href="./styles/inputs.css">
		<title>Itunes - Loja Online</title>
		
		<script type="text/javascript">
			function formatar(mascara, documento){
			  var i = documento.value.length;
			  var saida = mascara.substring(0,1);
			  var texto = mascara.substring(i);
			
			  if (texto.substring(0,1) != saida){
			            documento.value += texto.substring(0,1);
			  }
			}
		</script>
			
		<script type="text/javascript">
			function verificarSenha(){ 
				var campo1 = document.getElementById("senha").value;
				var campo2 = document.getElementById("confirmaSenha").value;
			    
				if(campo1==campo2){
					document.getElementById("resultadoSenha").innerHTML= "&raquo; As senhas são iguais.";
				}else{
					document.getElementById("resultadoSenha").innerHTML= "&raquo; As senhas não correpondem.";
				}
			}
		</script>
		
	</head>
	<body id="top">
		<div class="wrapper">
			<jsp:include page="./header.jsp" flush="true"/> 
		</div>
		<div class="wrapper">
						<jsp:include page="./login.jsp" flush="true"/> 
		</div>
		<div class="wrapper">
			<div id="intro">
				<h2>Cadastro de Cliente</h2>
				
				<form action="cc.do" name="cliente" ENCTYPE="form" METHOD=POST>
					
					<p><label for="nome">Nome :</label>
					<input type="text" name="nome" id="nome" size=35>

					<p><label for="sobrenome">Sobrenome:</label>
					<input type="text" name="sobrenome" id="sobrenome" size=30>
				
					<p><label for="dataNascimento">Data Nascimento:</label>
					<input type="text" name="dataNascimento" id="dataNascimento" size=24 maxlength="10"onkeypress="formatar('##/##/####', this)">

					<p><label for="sexo">Sexo:</label>
					<select name="cbSexo">
						<option value="M">Masculino</option>
						<option value="F">Feminino</option>
					</select>

					<p><label for="email">Email:</label>
					<input type="text" name="email" id="email" size=70>
					
					<p><label for="cep">Cep:</label>
					<input type="text" name="cep" id="cep" size=25 maxlength="9" OnKeyPress="formatar('#####-###', this)">
					
					<p><label for="endereco">Endereço:</label>
					<input type="text" name="endereco" id="endereco" size=66>

					<p><label for="enderecoComplemento">Endereço Complemento:</label>
					<input type="text" name="enderecoComplemento" id="enderecoComplemento" size=51>

					<p><label for="bairro">Bairro:</label>
					<input type="text" name="bairro" id="bairro" size=51>

					<p><label for="cidade">Cidade:</label>
					<input type="text" name="cidade" id="cidade" size=50>
	
					<p><label for="uf">Sexo:</label>
					<select name="cbUf" id="id_estados" >
						<option>Escolha o Estado</option>
						<option value="AC">Acre</option>
						<option value="AL">Alagoas</option>
						<option value="AP">Amapá</option>
						<option value="AM">Amazonas</option>
						<option value="BA">Bahia</option>
						<option value="CE">Ceará</option>
						<option value="DF">Distrito Federal</option>
						<option value="ES">Espirito Santo</option>
						<option value="GO">Goiás</option>
						<option value="MA">Maranhão</option>
						<option value="MT">Mato Grosso</option>
						<option value="MS">Mato Grosso do Sul</option>
						<option value="MG">Minas Gerais</option>
						<option value="PA">Pará</option>
						<option value="PB">Paraiba</option>
						<option value="PR">Paraná</option>
						<option value="PE">Pernambuco</option>
						<option value="PI">Piauí</option>
						<option value="RJ">Rio de Janeiro</option>
						<option value="RN">Rio Grande do Norte</option>
						<option value="RS">Rio Grande do Sul</option>
						<option value="RO">Rondônia</option>
						<option value="RR">Roraima</option>
						<option value="SC">Santa Catarina</option>
						<option value="SP">São Paulo</option>
						<option value="SE">Sergipe</option>
						<option value="TO">Tocantis</option>
					</select>
					
					<p><label for="cpf">CPF:</label>
					<input type="text" name="cpf" id="cpf" size= 30  maxlength="14" OnKeyPress="formatar('###.###.###-##', this)">

					<p><label for="rg">RG:</label>
					<input type="text" name="rg" id="rg" size= 31 maxlength="14" OnKeyPress="formatar('#.###.###.###-#', this)">

					<p><label for="usuario">Usuario:</label>
					<input type="text" name="usuario" id="usuario" size=25>

					<p><label for="senha">Senha:</label>
					<input type="password" name="senha" id="senha" size=25>

					<p><label for="confirmaSenha">Confirma Senha:</label>
					<input type="password" name="confirmaSenha" id="confirmaSenha" size=25 onkeyup="verificarSenha()">
					<p id="resultadoSenha"></p>
					
					<p><label for="lembreteSenha">Lembrete Senha:</label>
					<input type="text" name="lembreteSenha" id="lembreteSenha" size= 80  >
					
					<p><input type="submit" value="Cadastrar">
				</form>
			   
			   <br class="clear" />
			</div>
		</div>
				<div class="wrapper">
			<jsp:include page="./deals.jsp" flush="true"/>
		</div>
		<div class="wrapper">
			<jsp:include page="./footer.jsp" flush="true"/>
		</div>
	</body>
</html>
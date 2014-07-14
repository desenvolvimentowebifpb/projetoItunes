<%@ page import="br.com.converters.CalendarToDate"%>
<%@ page import="java.text.DateFormat"%>
<%@ page import="java.text.NumberFormat"%>
<%@ page import="java.util.*" %>
<%@ page import="br.com.model.pessoa.Cliente" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta http-equiv="imagetoolbar" content="no" />
		<link rel="stylesheet" type="text/css" href="./styles/layout.css">
		<link rel="stylesheet" type="text/css" href="./styles/inputs.css">
		<title>Itunes - Loja Online</title>
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
				<h2>Cadastro de Cliente </h2>
				<h1></h1>
				<ul>
					<li>
						<% Cliente cliente = (Cliente) request.getAttribute("cliente"); 
							String dataNascimento = CalendarToDate.DateToDateFormated(cliente.getDataNascimento());
							out.println("<p>");
							out.println("<p>Nome: "+cliente.getNome());
							out.println("<p>Sobrenome: "+cliente.getSobrenome());
							out.println("<p>Data Nascimento: "+dataNascimento);
							out.println("<p>Sexo: "+cliente.getSexo());
							out.println("<p>Email: "+cliente.getEmail());
							out.println("<p>Cep: "+cliente.getCep());
							out.println("<p>Endereço: "+cliente.getEndereco());
							out.println("<p>End. Complemento: "+cliente.getEnderecoComplemento());
							out.println("<p>Bairro: "+cliente.getBarro());
							out.println("<p>Cidade: "+cliente.getCidade());
							out.println("<p>Uf: "+cliente.getUf());
							out.println("<p>RG: "+cliente.getRg());
							out.println("<p>CPF: "+cliente.getCpf());
							out.println("<p>Usuario: "+cliente.getUsuario().getLogin());
							out.println("<p>");
						%>
					</li>
				</ul>
			</div>
		    <br class="clear" />
		</div>
		<div class="wrapper">
			<jsp:include page="./footer.jsp" flush="true"/>
		</div>
	</body>
</html>
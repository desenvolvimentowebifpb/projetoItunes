<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta http-equiv="imagetoolbar" content="no" />
		<link rel="stylesheet" type="text/css" href="./styles/layout.css">
		<link rel="stylesheet" type="text/css" href="./styles/inputs.css">
				<script>
			function formatar(mascara, documento){
			  var i = documento.value.length;
			  var saida = mascara.substring(0,1);
			  var texto = mascara.substring(i)
			
			  if (texto.substring(0,1) != saida){
			            documento.value += texto.substring(0,1);
			  }
			
			}
		</script>
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
				<h2>Usuario Cadastrado</h2>
				<p>Digite seus dados abaixo:</p>
				<form action="login.do" METHOD="post">
					<p><label for="usuario">Login:</label>
					<input type="text" name="usuario" id="usuario"></p>
					
					<p><label for="senha">Senha:</label>
					<input type=PASSWORD name="senha">
					<input type="SUBMIT" value="OK"> </p>
				</form>
				<%
					if(request.getAttribute("errorLogin")!=null){
						out.println(request.getAttribute("errorLogin"));
					}				
				%>				

				<h2>Usuario Não Cadastrado</h2>
				<p>Caso não possua cadastro, digite seu CPF abaixo:</p>
				<form action="vcc.do" METHOD="get">
					<p><label for ="cpf">CPF:</label>
					<input type="text" name="cpf" id="cpf" size= 30  maxlength="14" OnKeyPress="formatar('###.###.###-##', this)">
					<input type="submit" value="Enviar">
				</form>
					<%
						if(request.getAttribute("errorCadastro")!=null){
							out.println(request.getAttribute("errorCadastro"));
						}				
					%>	
			   <br class="clear" />
			</div>
		</div>
		<div class="wrapper">
			<div id="intro">
				<h2> </h2>
				<% 
				if(request.getAttribute("message")!=null){
					out.println(request.getAttribute("message"));
				}		
				%>
				<p> O navegador precisa autorizar a gravação de cookies.
				 Saiba como. Clique aqui e verifique.
				<form action="vc.do?flag=1" METHOD=POST>
					<input TYPE="submit" VALUE="Verificar">
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
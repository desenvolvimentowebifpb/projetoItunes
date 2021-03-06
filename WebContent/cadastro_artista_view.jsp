<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.*" %>

<%@ page import="br.com.dao.ArtistaDAO" %>
<%@ page import="br.com.model.pessoa.Artista" %>

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
				<h2>Cadastro de Artista (Modulo Administrador)</h2>
				<ul>
					<li>
						<% Artista artista = (Artista) request.getAttribute("artista"); 
							out.println("<p>Nome do Artista: "+artista.getNomeArtista());
							out.println("<p>");
							out.println("<p>");
							out.println("<p>");
						%>
					</li>
				</ul>
			</div>
		</div>
		<div class="wrapper">
			<jsp:include page="./footer_admin.jsp" flush="true"></jsp:include>
		</div>
		<div class="wrapper">
			<jsp:include page="./footer.jsp" flush="true"/>
		</div>
	</body>
</html>
<%@page import="java.text.NumberFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.*" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="br.com.dao.GeneroDAO" %>
<%@ page import="br.com.model.produto.Genero" %>
<%@ page import="br.com.model.produto.Produto" %>
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
				<h2>Detalhe do Produto</h2>
				<ul>
					<li>
						<% Produto produto = (Produto) request.getAttribute("produto"); 
						   String img = produto.getDescricao();
						    NumberFormat nf = NumberFormat.getCurrencyInstance();
						    out.println("<div class=\"imgholder\">");
						    out.println("<a><img src=\"imagem.do?texto="+img+"\" alt=\"\"></a>");
						    out.println("</div>");
							out.println("<p>Descricao do Produto: "+produto.getDescricao());
							out.println("<p>Artista: "+produto.getArtista().getNomeArtista());
							out.println("<p>Genero: "+produto.getGenero().getNomeGenero());
							out.println("<p>Preço Padrao: "+nf.format(produto.getPrecoPadrao().doubleValue()));
							out.println("<p>Preço Promocional: "+nf.format(produto.getPrecoPromocional().doubleValue()));
							out.println("<p>Tipo de Produto: "+produto.getTipoProduto().getTipoProduto());
							out.println("<p class=\"readmore\"> <a href=\"ac.do?idProduto="+produto.getId()+"\">Comprar &raquo</a> </p>");
							out.println("<p>");
						%>
					</li>
				</ul>
			</div>
		    <br class="clear" />
		</div>
		<div class="wrapper">
			<jsp:include page="./footer_admin.jsp" flush="true"></jsp:include>
		</div>
		<div class="wrapper">
			<jsp:include page="./footer.jsp" flush="true"/>
		</div>
	</body>
</html>
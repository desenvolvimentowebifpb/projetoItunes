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
				<h2>Cadastro de Produtos (Modulo Administrador)</h2>
				
				<form action="cp.do" ENCTYPE="multipart/form-data" METHOD=POST>
					
					<p><label for="descricao">Descricao:</label>
					<input type="text" name="descricao" id="descricao">
				
					<p><label for="genero">Genero:</label>
					<select name="cbGenero">
						<%	
							List<Genero> listGenero = new GeneroDAO().buscarTodos();
							Genero genero;
							for(Object obj : listGenero){
								genero = (Genero)obj;
								out.println("<option value "+ genero.getId() +">" + genero.getNomeGenero()+"</option>" );
							}
						%>
					</select>
					
					<p><label for="artista">Artista:</label>
					<select name="cbArtista">
						<%
							List<Artista> listArtista = new ArtistaDAO().buscarTodos();
							Artista artista;
							for(Object obj : listArtista){
								artista = (Artista)obj;
								out.println("<option value "+ artista.getId() +">" + artista.getNomeArtista()+"</option>" );
							}
						%>
					</select>

					<p><label for="tipoProduto">Tipo Produto:</label>
					<select name="cbTipoProduto">
						<%
							List<TipoProduto> listTipoProduto = new TipoProdutoDAO().buscarTodos();
							TipoProduto tipoProduto;
							for(Object obj : listTipoProduto){
								tipoProduto = (TipoProduto)obj;
								out.println("<option value "+ tipoProduto.getId() +">" + tipoProduto.getTipoProduto()+"</option>" );
							}
						%>
					</select>
					
					<p><label for="precoPadrao">Preço Padrao:</label>
					<input type="text" name="precoPadrao" id="precoPadrao">

					<p><label for="precoPromocional">Preço Promocional:</label>
					<input type="text" name="precoPromocional" id="precoPromocional">
					
					<p><label for="imagens">Arquivo de Imagem:</label>
					<input type="file" name="file" id="file" value="">
					
					<p><input type="submit" value="Cadastrar">
				</form>
			   
			   <br class="clear" />
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
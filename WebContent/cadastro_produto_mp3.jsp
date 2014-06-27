<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.*" %>
<%@ page import="br.com.model.produto.Produto" %>
<%@ page import="br.com.dao.ProdutoDAO" %>

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
			<div id="intro">
				<h2>Cadastro de MP3 no Produto (Modulo Administrador)</h2>
				
				<form action="cpm.do" ENCTYPE="multipart/form-data" METHOD=POST>
					
					<p><label for="mp3">Produto :</label>
					<select name="cbMp3">
						<%	
							List<Produto> listProduto = new ProdutoDAO().buscarTodosComMp3();
							Produto produto;
							for(Object obj : listProduto){
								produto = (Produto)obj;
								out.println("<option value "+ produto.getId() +">" + produto.getDescricao()+"</option>" );
							}
						%>
					</select>
					
					<p><label for="mp3">Arquivo de Musica:</label>
					<input type="file" name="mp3" id="mp3" value="">
					
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
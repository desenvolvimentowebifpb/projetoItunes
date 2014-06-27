<div id="latest">
	<div class="fl_left">
		<%@ page import="java.util.*" %>
		<%@ page import="br.com.dao.GeneroDAO" %>
		<%@ page import="br.com.model.produto.Genero" %>
		<img alt="Beatles" src="./images/not_fone.png">
	</div>
	<div class="fl_right">
		<h2>Categorias</h2>
			<ul>
			<%
				List<Genero> listGenero = new GeneroDAO().buscarTodos();
				Genero genero;
				for(Object obj : listGenero){
					genero = (Genero)obj;
					String genero_busca = genero.getNomeGenero();
					String img = "./images/"+genero_busca.toLowerCase()+".png";
					img.replace(" ", "");
					out.write("<li><a href=\"./cpc.do?categoria="+genero_busca+"\"> <img src=\""+img+"\" alt=\""+genero_busca+"\"></a></li>");
				}
			%>
			</ul>
			
	</div>
	<br class="clear" />
</div>


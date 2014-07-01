<div id="footer">
	<div id="newsletter">
		<h2>Receba nossas promoções!</h2>
		<p>Digite abaixo o seu email, e receba nosssas promoções!</p>
		<form action="promocoes.do" method="post">
        	<fieldset>
          		<legend>Promocoes</legend>
          		<input type="text" value="Digite seu Email"  onfocus="this.value=(this.value=='Digite seu Email...')? '' : this.value ;" />
         		 <input type="submit" name="deals_subscrib" id="deals_subscrib" value="Cadastrar..." />
        	</fieldset>
      </form>
      <p> Para remover seu email das promocoes <a href="./removeEmail.jsp"> clique aqui!</a></p>
	</div>
		<%@ page import="java.util.*" %>
		<%@ page import="java.util.Collections" %>
		<%@ page import="br.com.dao.GeneroDAO" %>
		<%@ page import="br.com.model.produto.Genero" %>
		<%@ page import="br.com.dao.ArtistaDAO" %>
		<%@ page import="br.com.model.pessoa.Artista" %>
		<%@ page import="br.com.dao.ProdutoDAO" %>
		<%@ page import="br.com.model.produto.Produto" %>
	<div class="footbox">
		<h2> Artistas </h2>
				<ul>
					<%
						List<Artista> listArtista = new ArtistaDAO().buscarTodos();
						Collections.shuffle(listArtista);
						Artista artista;
						for(int i=0;i<6;i++){
							if(listArtista.size()>5){
								artista = (Artista) listArtista.get(i);
								String artista_busca = artista.getNomeArtista();
								out.write("<li><a href=\"./cpa.do?categoria="+artista_busca+"\"> "+artista_busca+"</a></li>");
							}else{
								if(i<listArtista.size()){
									artista = (Artista) listArtista.get(i);
									String artista_busca = artista.getNomeArtista();
									out.write("<li><a href=\"./cpa.do?categoria="+artista_busca+"\"> "+artista_busca+"</a></li>");
								}
							}
						}
					%>
				</ul>
	</div>
	<div class="footbox">
		<h2> Generos </h2>
				<ul>
					<%
						List<Genero> listGenero = new GeneroDAO().buscarTodos();
						Collections.shuffle(listGenero);
						Genero genero;
						for(int i=0;i<6;i++){
							if(listGenero.size()>5){
								genero = (Genero) listGenero.get(i);
								String genero_busca = genero.getNomeGenero();
								out.write("<li><a href=\"./cpc.do?categoria="+genero_busca+"\"> "+genero_busca+"</a></li>");
							}else{
								if(i<listGenero.size()){
									genero = (Genero) listGenero.get(i);
									String genero_busca = genero.getNomeGenero();
									out.write("<li><a href=\"./cpc.do?categoria="+genero_busca+"\"> "+genero_busca+"</a></li>");
								}
							}
						}
					%>
				</ul>
	</div>
	<div class="footbox">
		<h2> Promoção </h2>
				<ul>
					<%
						List<Produto> listProduto = new ProdutoDAO().buscarTodosPromocao();
						Collections.shuffle(listProduto);
						Produto produto;
						for(int i=0;i<6;i++){
							if(listProduto.size()>5){
								produto = (Produto) listProduto.get(i);
								String produto_busca = produto.getDescricao();
								out.write("<li><a href=\"./cpp.do?categoria="+produto_busca+"\"> "+produto_busca+"</a></li>");
							}else{
								if(i<listProduto.size()){
									produto = (Produto) listProduto.get(i);
									String produto_busca = produto.getDescricao();
									out.write("<li><a href=\"./cpp.do?categoria="+produto_busca+"\"> "+produto_busca+"</a></li>");
								}
							}
						}
					%>
				</ul>
	</div>
	<br class="clear"/>
</div>
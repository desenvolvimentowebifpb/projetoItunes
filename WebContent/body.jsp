<div id="intro">
		<%@ page import="br.com.model.produto.Produto" %>
		<%@ page import="br.com.dao.ProdutoDAO" %>
		<%@ page import="java.util.*" %>
		<%@ page import="java.text.NumberFormat" %>
	<ul>
		<%
			List<Produto> listProduto = new ProdutoDAO().buscar9ItensComMP3();
			NumberFormat nf = NumberFormat.getCurrencyInstance();
			Produto produto;
			Produto produto_l;
			if(listProduto.size()>0){
				for(int i= 0; i < listProduto.size();i++){
					produto = (Produto) listProduto.get(i);
					String produto_busca = produto.getDescricao();
					   String img = produto.getDescricao();
					
					if(i%3==0){
						out.println("<li class=\"last\">");
						out.println("<h2>"+produto.getDescricao());
						out.println("</h2>");
					    out.println("<div class=\"imgholder\">");
					    out.println("<a><img src=\"imagem.do?texto="+img+"\" alt=\"\"></a>");
					    out.println("</div>");
						out.println("<p> Artista: "+produto.getArtista().getNomeArtista());
						out.println("</p>");
						out.println("<p> Genero: "+produto.getGenero().getNomeGenero());
						out.println("</p>");
						out.println("<p> Tipo de Produto: "+produto.getTipoProduto().getTipoProduto());
						out.println("</p>");
						out.println("<p class=\"readmore\">"+nf.format(produto.getPrecoPadrao().doubleValue())+" </p>");
						out.println("<p></p>");
						out.println("<p class=\"readmore\"> <a href=\"#\">Comprar &raquo</a> </p>");
						out.println("<p></p>");
					    out.println("</li>");
					}else{
					    out.println("<li>");
						out.println("<h2>"+produto.getDescricao());
						out.println("</h2>");
					    out.println("<div class=\"imgholder\">");
					    out.println("<a><img src=\"imagem.do?texto="+img+"\" alt=\"\"></a>");
					    out.println("</div>");
						out.println("<p> Artista: "+produto.getArtista().getNomeArtista());
						out.println("</p>");
						out.println("<p> Genero: "+produto.getGenero().getNomeGenero());
						out.println("</p>");
						out.println("<p> Tipo de Produto: "+produto.getTipoProduto().getTipoProduto());
						out.println("</p>");
						out.println("<p class=\"readmore\">"+nf.format(produto.getPrecoPadrao().doubleValue())+" </p>");
						out.println("<p></p>");
						out.println("<p class=\"readmore\"> <a href=\"#\">Comprar &raquo</a> </p>");
						out.println("<p></p>");
					    out.println("</li>");
					}
				}
			}else{
			    out.println("<li>");
				out.println("<p> Não Existem Produtos Cadastrados.");
			    out.println("</li>");
			}
		%>
	</ul>
   <br class="clear" />
</div>
<div id="intro">
		<%@ page import="br.com.model.pedido.PedidoDetalhe" %>
		<%@ page import="java.util.*" %>
		<%@ page import="java.text.NumberFormat" %>
	<h2>Carrinho</h2>
	<ul>
		<%
			List<PedidoDetalhe>	carrinho = (List<PedidoDetalhe>) request.getSession().getAttribute("carrinho"); 	
			if(carrinho!=null && carrinho.size()>0){
				out.println("<h1>Itens no Carrinho</h1>");
				out.println("<li>");
					for(int i=0;i<carrinho.size();i++){
						PedidoDetalhe pd = carrinho.get(i);
						out.println("<p> "+pd.getDescricaoItem()+"</p>");
					}
				out.println("</li>");
			}else{
			    out.println("<li>");
				out.println("<p> Carrinho Vazio.");
			    out.println("</li>");
			}
		%>
	</ul>
   <br class="clear" />
</div>
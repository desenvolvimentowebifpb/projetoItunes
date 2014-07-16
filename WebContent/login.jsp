<div id="logon">
			<%@ page import="java.util.*" %>
			<%@ page import="br.com.converters.CalendarToDate" %>
			<%
			
			if (session==null) {
				out.println("<a href=\"./restrito.jsp\">Click aqui para efetuar login...</a>");
			}else{
				String usuarioLogado = (String) session.getAttribute("usuarioLogado");	
				
				if (usuarioLogado==null || usuarioLogado.equals(false)) {
					out.println("<a href=\"./restrito.jsp\">Click aqui para efetuar login...</a>");
				}else{
					String usuarioNome = (String) session.getAttribute("usuarioNome");	
					Calendar dta_acesso= Calendar.getInstance();
					dta_acesso.setTimeInMillis(session.getLastAccessedTime());
					String ultAcesso = CalendarToDate.DateToDateHourFormated(dta_acesso);
					out.println("Usuario Logado: "+usuarioNome+"&nbsp&nbsp&nbspUltimo Acesso: "+ultAcesso+"&nbsp&nbsp&nbsp<a href=\"./logoff.do\">Logoff</a>");
				}
			}
			%>
   <br class="clear" />
</div>
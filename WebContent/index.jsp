<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta http-equiv="imagetoolbar" content="no" />
		<link rel="stylesheet" type="text/css" href="./styles/layout.css">
		<title>Itunes - Loja Online</title>
	</head>
	<body id="top">
		<div class="wrapper">
			<jsp:include page="./jsp/header.jsp" flush="true"/> 
		</div>
		<div class="wrapper">
			<jsp:include page="./jsp/menu.jsp" flush="true"/>
		</div>
		<div class="wrapper">
			<jsp:include page="./jsp/body.jsp" flush="true"/>
		</div>
		<div class="wrapper">
			<jsp:include page="./jsp/top.jsp" flush="true"/>
		</div>
		<div class="wrapper">
			<jsp:include page="./jsp/deals.jsp" flush="true"/>
		</div>
		<div class="wrapper">
			<jsp:include page="./jsp/footer.jsp" flush="true"/>
		</div>
	</body>
</html>
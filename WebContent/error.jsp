<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isErrorPage="true" import="java.io.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta name="description" content="Página de produto do portal de vendas da Faber Canetas" />
<title>Error Page | FaberCanetas</title>
<link href="css/carousel.css" rel="stylesheet">
<link rel="shortcut icon" href="img/iconeTitulo2.ico">
<link href="css/bootstrap.css" rel="stylesheet" />
<link href="css/sticky-footer.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
</head>
<body>

	<!-- NAVBAR -->
	<%@ include file="barra_navegacao.html" %>
	
	<div class="container marketing">
	<div class="row">
			<div class="panel panel-success col-sm-6 col-sm-offset-3 painel-confirmacao">
  				<div class="panel-body">
  					<img src="img/error.png" class="imgError">
					<h2 class="pull-center"><%=exception.getMessage()%></h2>
  				</div>
			</div>
		</div>	
	</div>

	<!-- FOOTER -->
		
	<%@ include file="footer.html" %>	
		
	<!-- FIM DO FOOTER -->	

</body>
</html>
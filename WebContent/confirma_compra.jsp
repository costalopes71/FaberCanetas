<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="br.com.fabercanetas.utils.Utils" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta name="description"
	content="Página de confirmação de compra do portal de vendas da Faber Canetas" />
<link href="css/carousel.css" rel="stylesheet">
<title>Confirmação Compra | Faber Canetas</title>
<link rel="shortcut icon" href="img/iconeTitulo2.ico">
<link href="css/bootstrap.css" rel="stylesheet" />
<link href="css/sticky-footer.css" rel="stylesheet">
</head>
<body>

	<%-- MENU --%>
	<%@ include file="barra_navegacao.html"%>

	<%-- CONTEUDO --%>
	<div class="container marketing">
		<div class="row">
			<div class="panel panel-success col-sm-6 col-sm-offset-3 painel-confirmacao">
  				<div class="panel-heading">
    				<h3 class="panel-title">COMPRA CONFIRMADA COM SUCESSO!</h3>
  				</div>
  				<div class="panel-body">
    				<p>Número do pedido de venda: ${pedido.codigo}</p>
    				<p>Prazo de entrega: ${pedido.prazoEntrega} dias</p>
    				<p>Valor do frete: R$${pedido.valorFrete}</p>
    				<p>Data do pedido: 
    				<fmt:parseDate value="${pedido.dataPedido}" pattern="yyyy-MM-dd"  var="dataPedido" type="date" />
					<fmt:formatDate value="${dataPedido}" pattern="dd/MM/yyyy" type="date"/>
    				</p>
  				</div>
			</div>
		</div>	
	</div>

	<%-- RODAPE --%>
	<%@ include file="footer.html" %>
	

	<script type="text/javascript" src="js/jquery-3.2.1.js"></script>
	<script type="text/javascript" src="js/bootstrap.js"></script>
</body>
</html>
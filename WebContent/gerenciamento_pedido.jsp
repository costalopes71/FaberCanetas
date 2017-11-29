<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="br.com.fabercanetas.utils.Utils" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta name="description" content="Página de gerenciamento dos pedidos do cliente do portal de vendas da Faber Canetas"/>
<link href="css/carousel.css" rel="stylesheet">
<title>Meus Pedidos | Faber Canetas</title>
<link rel="shortcut icon" href="img/iconeTitulo2.ico">
<link href="css/bootstrap.css" rel="stylesheet" />
<link href="css/sticky-footer.css" rel="stylesheet">
</head>
<body>

	<%-- MENU --%>
	<%@ include file="barra_navegacao.html"%>
	
	<%-- CONTEUDO --%>
	<div class="container marketing">
		<div class="row col-sm-8 col-sm-offset-2">
			<div class="tabPedido"><h3 class="pull-center">MEUS PEDIDOS</h3></div>
			<table class="table table-hover pull-center">
				<tr>
							<th>Nr. Pedido</th>
							<th>Data Pedido</th>
							<th>Status</th>
							<th>Prazo Entrega</th>
							<th>Valor Frete</th>
							<th>Observações</th>
						</tr>
						<c:forEach var="pedido" items="${listaPedido}">
						<tr>
							<td>${pedido.codigo}</td>
							<td>
							<fmt:parseDate value="${pedido.dataPedido}" pattern="yyyy-MM-dd"  var="dataPedido" type="date" />
							<fmt:formatDate value="${dataPedido}" pattern="dd/MM/yyyy" type="date"/>
							</td>
							<td>${pedido.statusPedido}</td>
							<td>${pedido.prazoEntrega} dias</td>
							<td>R$${pedido.valorFrete}</td>
							<td>${pedido.obsPedido}</td>
						</tr>
						</c:forEach>
					</table>
			</div>
	</div>

	<%-- RODAPE --%>
	<%@ include file="footer.html" %>
	
	<script type="text/javascript" src="js/jquery-3.2.1.js"></script>
	<script type="text/javascript" src="js/bootstrap.js"></script>
</body>
</html>
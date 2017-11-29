<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta name="description" content="Página de produto do portal de vendas da Faber Canetas" />
<link href="css/carousel.css" rel="stylesheet">
<title>Produto | Faber Canetas</title>
<link rel="shortcut icon" href="img/iconeTitulo2.ico">
<link href="css/bootstrap.css" rel="stylesheet" />
<link href="css/sticky-footer.css" rel="stylesheet">
</head>
<body>

	<!-- NAVBAR -->
	
	<%@ include file="barra_navegacao.html" %>
	
	<!--  FIM DA NAVBAR -->

	<div class="container marketing">

		<!-- 3 colunas com os produtos abaixo do carousel -->
		
		<div class="row">
		
			<div class="col-sm-4 col-lg-offset-2 container-produto">
				<img class="img-circle img-circle-produto" src="${attrProduto.urlImgProduto}" alt="${attrProduto.descricaoProduto}" 
				width="250" height="250">
			</div>
			
			<div class="col-sm-4 container-produto">
			<h2 class="pull-center">${attrProduto.nomeProduto}</h2>
				<div class="panel panel-default painel-produto">
  					<div class="panel-body">
  						
							<p>POR: <span class="valor-moeda">R$</span><span class="preco-produto">${attrProduto.valorUnitario}</span></p>
							<p style="font-weight: bold">Especificações</p>
							<ul>
								<li>corpo plástico cristal transparente</li>
								<li>nome do fabricante impresso no corpo da caneta</li>
								<li>tinta à base de corantes orgânicos e solventes</li>
							</ul>
							<a class="btn btn-primary btn-lg col-sm-offset-2" href="carrinho?idProduto=${attrProduto.codigo}&opt=1" role="button">
								<span class="glyphicon glyphicon-shopping-cart"></span>Adicionar ao Carrinho</a>
  					</div>
				</div>
			</div>
			
		</div>
		
		<div class="row">
			
			<div class="col-sm-12">
				<h3>DESCRIÇÃO</h3>
				<hr>
				<p>${attrProduto.descricaoProdutoCompleta}</p>
				<hr>
			</div>
			
		</div>

		<!-- FIM DAS 3 COLUNAS DE PRODUTOS -->

	</div>
	
	<!-- FIM DO CONTAINER MARKETING -->
	
	<!-- FOOTER -->
		
	<%@ include file="footer.html" %>	
		
	<!-- FIM DO FOOTER -->	
	
	
	<script type="text/javascript" src="js/jquery-3.2.1.js"></script>
	<script type="text/javascript" src="js/bootstrap.js"></script>
</body>
</html>


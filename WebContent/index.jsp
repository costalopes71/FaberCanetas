<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta name="description" content="Página inicial do portal de vendas da Faber Canetas" />
<link href="css/carousel.css" rel="stylesheet">
<title>Home - Faber Canetas</title>
<link rel="shortcut icon" href="img/iconeTitulo2.ico">
<link href="css/bootstrap.css" rel="stylesheet" />
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script type="text/javascript" src="js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
  $( function() {
    var availableTags = [
		"Hype Magenta",
		"Standard Blue",
		"Super White"
    ];
    $( "#tags" ).autocomplete({
      source: availableTags
    });
  } );
  
  function exibeProduto(e) {
	  
	  if(e.keyCode === 13) {
		  var nome = $("#tags").val();
		  window.location = "http://localhost:8080/FaberCanetas/produto?nome="+ nome + "&opt=2";		  
	  }
  }
  
  </script>
</head>
<body>

	<!-- NAVBAR -->

	<div class="navbar-wrapper">
		<div class="container">
			<nav class="navbar navbar-inverse navbar-static-top">
				<div class="container">
					<div class="navbar-header">
						<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
							aria-expanded="false" aria-controls="navbar">
							<span class="sr-only">Toggle navigation</span>
							<span class="icon-bar"></span> 
							<span class="icon-bar"></span> 
							<span class="icon-bar"></span>
						</button>
						<a class="navbar-brand" href="#">Faber Canetas</a>
					</div>
					<div id="navbar" class="navbar-collapse collapse">
						<ul class="nav navbar-nav navbar-right">
							<li>
								<div class="ui-widget form-group">
  									<input id="tags" class="" onkeypress="exibeProduto(event)">
								</div>
							</li>
							<li class="active"><a href="#">Home</a></li>
							<li><a href="#about">Sobre</a></li>
							<li><a href="#contact">Contato</a></li>
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown" role="button" aria-haspopup="true"
								aria-expanded="false">Minha Conta<span class="caret"></span></a>
								<ul class="dropdown-menu">
									<li><a href="meuspedidos?opt=exibirPedidos"><span class="glyphicon glyphicon-list-alt iconesMenu"></span>Meus Pedidos</a></li>
									<li><a href="home?opt=3"><span class="glyphicon glyphicon-shopping-cart iconesMenu"></span>Carrinho de Compra</a></li>
									<li><a href="#"><span class="glyphicon glyphicon-cog iconesMenu"></span>Configurações</a></li>
									<li role="separator" class="divider"></li>
									<li class="dropdown-header">Terminou por hoje?</li>
									<li><a href="home?opt=99">
										<span class="glyphicon glyphicon-user iconesMenu"></span>Sair</a></li>
								</ul></li>
						</ul>
					</div>
				</div>
			</nav>
		</div>
	</div>
	
	<!--  FIM DA NAVBAR -->

	<!-- INICIO DO Carousel -->
	
	<div id="myCarousel" class="carousel slide" data-ride="carousel">
		<!-- Indicators -->
		<ol class="carousel-indicators">
			<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
			<li data-target="#myCarousel" data-slide-to="1"></li>
			<li data-target="#myCarousel" data-slide-to="2"></li>
		</ol>
		<div class="carousel-inner" role="listbox">
			<div class="item active">
				<div class="container">
					<div class="carousel-caption">
						<h1>Precisa de ajuda? Entre em contato!</h1>
						<p>
							Bem-vindo a Faber Canetas! Esta com qualquer tipo de problema para efetuar sua compra? Precisa tirar qualquer tipo de dúvidas?
							Conte com nossos canais de atendimento ao cliente! 24h por dia a sua disposição!
						</p>
						<p>
							<a class="btn btn-lg btn-primary" href="#" role="button">Contato</a>
						</p>
					</div>
				</div>
			</div>
			<div class="item">
				<div class="container">
					<div class="carousel-caption">
						<h1>Faber Canetas, a melhor do mercado!</h1>
						<p>Rápido, fácil e sem burocracia. A Faber Canetas tem o melhor e-comerce
						de canetas do Brasil. Vendemos desde 1997 para o mercado brasileiro e contamos com as entregas mais
						rápidas!
						<p>
							<a class="btn btn-lg btn-primary" href="#" role="button">Nossa História</a>
						</p>
					</div>
				</div>
			</div>
			<div class="item">
				<div class="container">
					<div class="carousel-caption">
						<h1>Nossos Produtos</h1>
						<p>
						Confira na seção de produtos todos os nossos tipos de canetas! Temos a caneta ideal para você ou
						para seus clientes. Nossos produtos tem a melhor qualidade do mercado e duram 3x mais do que as
						marcas concorrentes! Experimente!
						</p>
						<p>
							<a class="btn btn-lg btn-primary" href="#" role="button">Produtos</a>
						</p>
					</div>
				</div>
			</div>
		</div>
		<a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev"> <span class="glyphicon glyphicon-chevron-left"
			aria-hidden="true"></span> <span class="sr-only">Previous</span></a>
			<a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
			<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
			<span class="sr-only">Next</span>
		</a>
	</div>
	
	<!-- FIM DO carousel -->

	<div class="container marketing">

		<!-- 3 colunas com os produtos abaixo do carousel -->
		
		<div class="row">
		
			<c:forEach items="${attrListaProduto}" var="produto">
			<div class="col-lg-4">
				<a href="produto?id=${produto.codigo}&opt=1">
				<img class="img-circle" src="${produto.urlImgProduto}" alt="Caneta vermelha da linha Super" 
				width="140" height="140"></a>
				<h2>${produto.nomeProduto}</h2>
				<p>${produto.descricaoProdutoCompleta}</p>
				<p>
					<a class="btn btn-default" href="produto?id=${produto.codigo}&opt=1" role="button">Veja mais...&raquo;</a>
				</p>
			</div>
			</c:forEach>
			
		</div>

		<!-- FIM DAS 3 COLUNAS DE PRODUTOS -->

	

	</div>

	<!-- FOOTER -->
	
	<%@ include file="footer.html" %>
	
	<!-- FIM DO FOOTER -->
	<!-- testando o GITHUB -->
	<!-- testando o GITHUB 2-->
</body>
</html>


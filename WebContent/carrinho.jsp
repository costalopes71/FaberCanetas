<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="br.com.fabercanetas.to.Produto" %>
<%@ page import="br.com.fabercanetas.to.ItemCarrinho" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta name="description" content="Página do carrinho de compras do portal de vendas da Faber Canetas" />
<title>Carrinho | Faber Canetas</title>
<link rel="shortcut icon" href="img/iconeTitulo2.ico">
<link href="css/bootstrap.css" rel="stylesheet" />
<link href="css/carousel.css" rel="stylesheet">
<script type="text/javascript">

function atualizaQtd(obj, id) {
	var qtdAtual = { 
			qtd:$(obj).val(),
			id: id,
			opt:4
		};
	
	$.ajax({
		url: 'http://localhost:8080/FaberCanetas/carrinho',
		type: 'POST',
		data: qtdAtual,
		success:function(data){
           $("#total" + id).html("R$" + data);
			           
        }
	})
	
}

function atualizaSubTotal() {
	var qtdAtual = { 
			opt:5
		};
	$.ajax({
		url: 'http://localhost:8080/FaberCanetas/carrinho',
		type: 'POST',
		data: qtdAtual,
		success:function(data){
           $("#subTot").html("R$" + data);
        }
	})
	
}

</script>
</head>
<body>

	<%@ include file="barra_navegacao.html" %>	

	<div class="container-fluid containerCart">
		
		<div class="col-sm-10 col-sm-offset-1">
			<h2><span><img alt="" src="img/carrinho_ico.gif"></span><strong>Meu Carrinho</strong></h2>
			<hr>
		</div>
			
			<!-- COMECO DO CARRINHO -->
		
			<div class="col-sm-10 col-sm-offset-1 tituloCarrinho">
  				<div class="tituloCarrinho">
  					<div class="col-sm-2 titCart pull-center">PRODUTO</div>
  					<div class="col-sm-4 titCart pull-center">DESCRIÇÃO</div>
  					<div class="col-sm-2 titCart pull-center">QUANTIDADE</div>
  					<div class="col-sm-2 titCart pull-center">PREÇO UNIT.</div>
  					<div class="col-sm-1 titCart pull-center">TOTAL</div>
  				</div>
			</div>
			<c:forEach items="${carrinhoCliente}" var="produto">
			<div class="col-sm-10 col-sm-offset-1 areaProduto">
				<div class="col-sm-2 pull-center"><img alt="" src="${produto.value.urlImgProduto}" width="100" height="100"></div>
				<div class="col-sm-4 pull-center alinhaCentro">${produto.value.descricaoSimples}</div>
				<div class="col-sm-2 pull-center alinhaCentro">
					<input type="number" value="${produto.value.qtd}" class="qtdProduto" id="itemCarrinho_${produto.value.codigo}" 
					onchange="atualizaQtd(this, ${produto.value.codigo}), atualizaSubTotal()" style="width: 60px; text-align: center;">
					<a href="carrinho?opt=3&idProduto=${produto.value.codigo}">
						<span class="glyphicon glyphicon-remove"></span>
					</a>
				</div>
				<div class="col-sm-2 pull-center alinhaCentro"><span>R$</span>${produto.value.valorUnitario}</div>
				<div class="col-sm-1 pull-center alinhaCentro" id="total${produto.value.codigo}">
					<span>R$</span>${produto.value.valorTotalItem}
				</div>
			</div>
			</c:forEach>
			<div class="col-sm-10 col-sm-offset-1 areaTotalCompra">
				<div class="col-sm-2 pull-center"></div>
				<div class="col-sm-5 pull-center"></div>
				<div class="col-sm-1 pull-center"></div>
				<div class="col-sm-2 pull-center">SUB-TOTAL:</div>
				<div class="col-sm-1 pull-center" id="subTot">R$
				<% 
					@SuppressWarnings("unchecked")
					HashMap<Integer, ItemCarrinho> teste = (HashMap<Integer, ItemCarrinho>) request.getSession().getAttribute("carrinhoCliente");
					double soma = 0;
					for (ItemCarrinho item : teste.values()) {
						soma += item.getValorTotalItem();
					}
				%>
				<%= soma %>
				</div>
			</div>
			
			<!-- FIM DO CARRINHO -->
			
			<!-- COMECO DOS BOTOES DO CARRINHO (CONTINUAR COMPRANDO, LIMPAR, FINALIZAR COMPRA) -->
			
			<div class="row">
				<div class="col-sm-10 col-sm-offset-1 btnsCart">
				<a href="home?opt=1" class="btn btn-lg btn-default">Continuar Comprando</a>
				<a href="carrinho?opt=2" class="btn btn-lg btn-default col-sm-offset-3">Limpar Carrinho</a>
				<a href="carrinho?opt=6" class="btn btn-lg btn-primary pull-right">Finalizar Compra</a>
				</div>
			</div>
			
			<!-- FIM DOS BOTOES DO CARRINHO (CONTINUAR COMPRANDO, LIMPAR, FINALIZAR COMPRA) -->
			
	</div>
	
	<%@ include file="footer.html" %>
	
<script type="text/javascript" src="js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
</body>
</html>
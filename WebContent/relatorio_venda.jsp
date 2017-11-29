<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta name="description" content="Página de relatorio de vendas da Faber Canetas" />
<link href="css/carousel.css" rel="stylesheet">
<title>Relatório| Faber Canetas</title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="shortcut icon" href="img/iconeTitulo2.ico">
<link href="css/bootstrap.css" rel="stylesheet" />
<link href="css/sticky-footer.css" rel="stylesheet">
<script type="text/javascript" src="js/bootstrap.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
$( function() {
	  $( ".datepicker" ).datepicker({dateFormat: 'dd/mm/yy', changeYear: true, changeMonth: true});
	} );
  </script>
</head>

	<!-- NAVBAR -->
	
	<%@ include file="barra_navegacao.html" %>
	
	<!--  FIM DA NAVBAR -->

	<div class="container marketing">
		<div class="row">
			<div class="panel panel-success col-sm-8 col-sm-offset-2 painel-confirmacao">
  				<div class="panel-heading">
    				<h3 class="panel-title pull-center">RELATÓRIO MENSAL DE VENDA DE PRODUTOS</h3>
  				</div>
  				<div class="panel-body">
  					<form action="relatorio" method="get" class="col-sm-offset-1 form-inline">
  						<div class="form-group">
  							<label for="fromDate">DE</label>
							<input id="fromDate" name="from" type="text" class="datepicker form-control" placeholder="DD/MM/AAAA">
          				</div>
          				<div class="form-group">
          					<label for="toDate">ATÉ</label>
          					<input id="toDate" name="to" type="text" class="datepicker form-control" placeholder="DD/MM/AAAA">						
  						</div>
  						<button type="submit" class="btn btn-default">Pesquisar</button>
  					</form>
  				</div>
			</div>
		</div>	
		
		<div class="row col-sm-8 col-sm-offset-2">
			<div class="tabRelatorio">
				<h3 class="pull-center">RELATÓRIO MENSAL</h3>
			</div>
			<table class="table table-hover pull-center">
				<tr>
					<th class="pull-center">Nome Produto</th>
					<th class="pull-center">Quantidade Vendida</th>
				</tr>
				<c:forEach var="produto" items="${attrListaRelatorio}">
					<tr>
						<td>${produto.nomeProduto}</td>
						<td>${produto.quantidade}</td>
					</tr>
				</c:forEach>
			</table>
			</div>
		</div>
		
	
	<!-- FOOTER -->
		
	<%@ include file="footer.html" %>	
		
	<!-- FIM DO FOOTER -->	


</body>
</html>
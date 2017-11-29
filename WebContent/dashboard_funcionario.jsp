<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8" />
      <meta http-equiv="Content-Type" content="text/html">
      <meta name="viewport" content="width=device-width, initial-scale=1.0" />
      <meta name="description" content="Dashboard dos funcionários da empresa Faber Canetas" />
      <meta http-equiv="refresh" content="120">
      <title>Dashboard - Faber Canetas</title>
      <link rel="stylesheet" href="css/bootstrap.min.css"/>
      <link rel="stylesheet" href="css/font-awesome.min.css"/>
      <!-- Theme style -->
      <link rel="stylesheet" href="css/AdminLTE.min.css">
      <link rel="stylesheet" href="css/_all-skins.min.css">
      <link rel="stylesheet" href="font-awesome/css/font-awesome.css" />
      <link rel="stylesheet" href="font-awesome/css/font-family.css" />
      <script src="js/highcharts.js"></script>
      <script src="js/exporting.js"></script>
      <script src="js/jquery-3.2.1.js"></script>
      
      <script type="text/javascript">
      
      	function abreModalDistribuicao(codigo) {
      		$.ajax({
      			url: 'http://localhost:8080/FaberCanetas/relatorio?opt=8&cd=' + codigo,
      			type: 'POST',
      			data: null,
      			success:function(data){
      				var resultado = JSON.parse(data);
      				var btnCancelar = "<button type=\"button\" class=\"btn btn-secondary\" data-dismiss=\"modal\">CANCELAR</button>";
      				var btnGravar = "<button type=\"button\" class=\"btn btn-primary\" data-dismiss=\"modal\" onclick=\"abreModalConfirmacaoDistribuicao(" + resultado.codigo + ")\">GRAVAR</button>";
      				$("#iFornecedor").val(resultado.fornecedor);
      				$("#iNumeroOC").val(resultado.codigo);
      				$("#idataDistribuicao").val(resultado.data);
      				$("#divModalCompra").html(btnGravar + btnCancelar);
      				$('#pedDistribuicao').modal('show');
      			}
      		});
      	}
      	
      	function abreModalVenda(codigo) {
      	      		
      	      $.ajax({
      	      	url: 'http://localhost:8080/FaberCanetas/relatorio?opt=9&cd=' + codigo,
      	      	type: 'POST',
      	      	data: null,
      	      	success:function(data){
      	      		var resultado = JSON.parse(data);
      	      		var btnCancelar = "<button type=\"button\" class=\"btn btn-secondary\" data-dismiss=\"modal\">CANCELAR</button>";
      	      		var btnGravar = "<button type=\"button\" class=\"btn btn-primary\" data-dismiss=\"modal\" onclick=\"abreModalConfirmacaoVenda(" + resultado.codigo + ")\">GRAVAR</button>";
      	      		$("#nPedidoVenda").val(resultado.codigo);
      	      		$("#icliente").val(resultado.cliente);
	      	      	$("#dataPedido").val(resultado.data);
	      	      	$("#divModalVenda").html(btnGravar + btnCancelar);
      	      		$('#pedCompra').modal('show');
      	      	}
      	      });
		}		
      	
      	function abreModalConfirmacaoVenda(codigo) {
      		
      		var deposito = [];
      		var els = document.getElementsByName('deposito');
      		for (var i = 0; i < els.length; i++){
      		  if (els[i].checked) {
      		    deposito.push(els[i].value);
      		  }
      		}
      		
      		$.ajax({
      	      	url: 'http://localhost:8080/FaberCanetas/relatorio?opt=10&cd=' + codigo + '&dep=' + deposito,
      	      	type: 'POST',
      	      	data: null,
      	      	success:function(data){
      	      	$('#confPedVenda').modal('show');
      	      	}
      		});
      	}
      	
		function abreModalConfirmacaoDistribuicao(codigo) {
      		
      		$.ajax({
      	      	url: 'http://localhost:8080/FaberCanetas/relatorio?opt=11&cd=' + codigo,
      	      	type: 'POST',
      	      	data: null,
      	      	success:function(data){
      	      	$('#confPedCom').modal('show');
      	      	}
      		});
      	}
      	
      
      </script>
      
   </head>
   <body class="skin-blue sidebar-mini sidebar-open sidebar-collapse">
      <div class="wrapper">
      	<header class="main-header">
         <!-- Logo -->
         <a href="#" class="logo">
            <!-- mini logo for sidebar mini 50x50 pixels -->
            <span class="logo-mini"><b>Faber</b></span> 
            <!-- logo for regular state and mobile devices -->
            <span class="logo-lg"><b>Faber</b>Canetas</span>
         </a>
         <!-- Header Navbar: style can be found in header.less -->
         <nav class="navbar navbar-static-top">
            <!-- Sidebar toggle button-->
            <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span> <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            </a>
            <div class="navbar-custom-menu">
            
               <ul class="nav navbar-nav">
                  <li style="margin-right: 450px; margin-top: 1px;"><img src="${statusFarol}" style="width: 120px; height: 48px;"></li>
                  <li class=""><a href="home?opt=99"><i class="glyphicon glyphicon-off"></i><span class="text"> Logout</span></a></li>
               </ul>
            </div>
         </nav>
      </header>
      <!-- Left side column. contains the logo and sidebar -->
      <aside class="main-sidebar">
         <!-- sidebar: style can be found in sidebar.less -->
         <section class="sidebar">
            <!-- Sidebar user panel -->
            <!-- sidebar menu: : style can be found in sidebar.less -->
            <ul class="sidebar-menu">
            	<li class="active"><a href=""><i class="icon-bar-chart"></i><span>Dashboard</span></a>
                	<ul class="treeview-menu">
                   		<c:if test="${usuario.funcao.nome == 'Gerente'}">
			            	<li><a href="dashboard_gerente.jsp"><i class="fa fa-circle-o" ></i>Gerencial</a></li>
			            </c:if>
			            <li><a href="relatorio?opt=log"><i class="fa fa-circle-o" ></i>Operacional</a></li>
			        </ul>
               </li>
               <li><a href="#"><i class="glyphicon glyphicon-plus"></i><span>Cadastrar Funcionário</span></a></li>
               <li><a href="#"><i class="glyphicon glyphicon-plus"></i><span>Cadastrar Cliente</span></a></li>
               <li><a href="#"><i class="glyphicon glyphicon-plus"></i><span>Cadastrar Pedido Venda</span></a></li>
               <li><a href="#"><i class="glyphicon glyphicon-plus"></i><span>Cadastrar Pedido Compra</span></a></li>
            </ul>
         </section>
         <!-- /.sidebar -->
      </aside>
      <!-- Content Wrapper. Contains page content -->
      <div class="content-wrapper">
         <!-- Main content -->
         <section class="content">
            <div class="row">
               <div class="col-md-12">
                  <div class="box">
                     <div class="box-header with-border">
                        <h3 class="box-title"><i class="icon-bar-chart"></i> Gráficos</h3>
                        <img src="${statusFarolDep}">
                        <div class="box-tools pull-right">
                           <button type="button" class="btn btn-box-tool"
                              data-widget="collapse">
                           <i class="fa fa-minus"></i>
                           </button>
                        </div>
                     </div>
                     <!-- /.box-header -->
                     <div class="box-body">
                        <div class="row">
                           <div class="col-md-4">
                              <p class="text-center">
                                 <strong>Posição Estoque Produto Central</strong>
                              </p>
                              <div class="chart">
                                 <div class="widget-content">
                                    <div id="estoqueCentral" style="height: 260px; width: 100%;"></div>
                                 </div>
                              </div>
                              <!-- /.chart-responsive -->
                           </div>
                           <!-- /.col -->
                           <div class="col-md-4">
                              <p class="text-center">
                                 <strong>Posição Estoque Produto Sul</strong>
                              </p>
                              <div class="chart">
                                 <div class="widget-content">
                                    <div id="estoqueSul" style="height: 260px; width: 100%;"></div>
                                 </div>
                              </div>
                              <!-- /.chart-responsive -->
                           </div>
                           <!-- /.col -->
                           <div class="col-md-4">
                              <p class="text-center">
                                 <strong>Posição Estoque Produto Nordeste</strong>
                              </p>
                              <div class="chart">
                                 <div class="widget-content">
                                    <div id="estoqueNordeste"
                                       style="height: 260px; width: 100%;"></div>
                                 </div>
                              </div>
                              <!-- /.chart-responsive -->
                           </div>
                           <!-- /.col -->
                        </div>
                        <!-- /.row -->
                     </div>
                     <!-- ./box-body -->
                  </div>
                  <!-- /.box -->
               </div>
               <!-- /.col -->
            </div>
            <!-- /.row -->
            <div class="row">
               <div class="col-md-6">
                  <div class="box">
                     <div class="box-header with-border">
                        <span class="icon"><i class="glyphicon glyphicon-th-list"></i><strong> Lista Pedidos Compra</strong><img src="${statusFarolOC}"></span>
                        <div class="box-tools pull-right">
                           <button type="button" class="btn btn-box-tool"
                              data-widget="collapse">
                           <i class="fa fa-minus"></i>
                           </button>
                        </div>
                     </div>
                     <!-- /.box-header -->
                     <div class="box-body">
                        <table class="table table-condensed">
                           <tr>
                              <th>Nº Solicitação</th>
                              <th>Fornecedor</th>
                              <th>Data Solicitação</th>
                              <th>Status</th>
                              <th>
                              <th>
                           </tr>
                           <c:forEach var="ordemCompra" items="${listaOC}">
                              <c:if test="${ordemCompra.status == 'P'}">
                                 <tr class="table-warning">
                                    <td>${ordemCompra.codigo}</td>
                                    <td>${ordemCompra.fornecedor.nomeFantasia}</td>
                                    <td>
                                       <fmt:parseDate value="${ordemCompra.dataSolicitacao}" pattern="yyyy-MM-dd" var="dataSolicitacao" type="date"/>
                                       <fmt:formatDate value="${dataSolicitacao}" pattern="dd/MM/yyyy" type="date"/>
                                    </td>
                                    <td>${ordemCompra.status}</td>
                                    <td><a class="glyphicon glyphicon-edit" id="btnEditar${ordemCompra.codigo}" onclick="abreModalDistribuicao('${ordemCompra.codigo}');"></a></td>
                                 </tr>
                              </c:if>
                              <c:if test="${ordemCompra.status == 'A'}">
                                 <tr class="table-danger">
                                    <td>${ordemCompra.codigo}</td>
                                    <td>${ordemCompra.fornecedor.nomeFantasia}</td>
                                    <td>
                                       <fmt:parseDate value="${ordemCompra.dataSolicitacao}" pattern="yyyy-MM-dd" var="dataSolicitacao" type="date"/>
                                       <fmt:formatDate value="${dataSolicitacao}" pattern="dd/MM/yyyy" type="date"/>
                                    </td>
                                    <td>${ordemCompra.status}</td>
                                    <td><a class="glyphicon glyphicon-edit" id="btnEditar${ordemCompra.codigo}" onclick="abreModalDistribuicao('${ordemCompra.codigo}');"></a></td>
                                 </tr>
                              </c:if>
                           </c:forEach>
                        </table>
                     </div>
                     <!-- /.box-body -->
                  </div>
                  <!-- /.box -->
               </div>
               <div class="col-md-6">
                  <div class="box">
                     <div class="box-header with-border">
                        <span class="icon"> <i class="glyphicon glyphicon-th-list"></i><strong> Lista Pedidos Venda</strong><img src="${statusFarolPedido}"></span>
                        <div class="box-tools pull-right">
                           <button type="button" class="btn btn-box-tool"
                              data-widget="collapse">
                           <i class="fa fa-minus"></i>
                           </button>
                        </div>
                     </div>
                     <!-- /.box-header -->
                     <div class="box-body">
                        <table class="table table-condensed">
                           <tr>
                              <th>Nº Solicitação</th>
                              <th>Cliente</th>
                              <th>Data Solicitação</th>
                              <th>Status</th>
                              <th></th>
                           </tr>
                           <c:forEach var="pedido" items="${listaPV}">
                              <c:if test="${pedido.statusPedido == 'P'}">
                                 <tr class="table-warning">
                                    <td>${pedido.codigo}</td>
                                    <td>${pedido.cliente.nomeFantasia}</td>
                                    <td>
                                       <fmt:parseDate value="${pedido.dataPedido}" pattern="yyyy-MM-dd" var="dataPedido" type="date"/>
                                       <fmt:formatDate value="${dataPedido}" pattern="dd/MM/yyyy" type="date"/>
                                    </td>
                                    <td>${pedido.statusPedido}</td>
                                    <td><a class="glyphicon glyphicon-edit" id="btnEditarVenda${pedido.codigo}" onclick="abreModalVenda('${pedido.codigo}');"></a></td>
                                 </tr>
                              </c:if>
                              <c:if test="${pedido.statusPedido == 'A'}">
                                 <tr class="table-danger">
                                    <td>${pedido.codigo}</td>
                                    <td>${pedido.cliente.nomeFantasia}</td>
                                    <td>
                                       <fmt:parseDate value="${pedido.dataPedido}" pattern="yyyy-MM-dd" var="dataPedido" type="date"/>
                                       <fmt:formatDate value="${dataPedido}" pattern="dd/MM/yyyy" type="date"/>
                                    </td>
                                    <td>${pedido.statusPedido}</td>
                                    <td><a class="glyphicon glyphicon-edit" id="btnEditarVenda${pedido.codigo}" onclick="abreModalVenda('${pedido.codigo}');"></a></td>
                                 </tr>
                              </c:if>
                           </c:forEach>
                        </table>
                     </div>
                     <!-- /.box-body -->
                  </div>
                  <!-- /.box -->
               </div>
            </div>
            <!-- /.row -->
         </section>
         <!-- /.content -->
      </div>
      
      
      <!-- MODAL INICIO PEDIDO DE VENDA -->
      <div class="modal fade bd-example-modal-lg" tabindex="-1" id="pedCompra" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
         <div class="modal-dialog modal-lg">
            <div class="modal-content">
               <div class="modal-header">
                  <div class="alert alert-warning" role="alert">
                     PEDIDO DE VENDA
                     <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                     <span aria-hidden="true">&times;</span>
                     </button>
                  </div>
               </div>
               <div class="modal-body">
                  <form id='pedVenda'>
                     <div class="form-row">
                        <div class="form-group col-md-6">
                           <label for="cliente" class="col-form-label">CLIENTE</label>
                           <input type="text" class="form-control" id="icliente" disabled>
                        </div>
                        <div class="form-group col-md-4">
                           <label for="dataPedido" class="col-form-label">DATA PEDIDO DE VENDA</label>
                           <input type="text" class="form-control" id="dataPedido" disabled>
                        </div>
                        <div class="form-group col-md-2">
                           <label for="nPedidoVenda" class="col-form-label">Nº PEDIDO VENDA</label>
                           <input type="number" class="form-control" id="nPedidoVenda" disabled>
                        </div>
                        <div>
                           <p class="navbar-text navbar-left" style="font-weight:900;">DEPOSITO DISTRIBUIÇÃO</p>
                        </div>
                        <div class="form-group col-md-12">
                           <label class="radio-inline">
                           <input type="radio" name="deposito" id="deposito" value="3">CENTRAL
                           </label>
                           <label class="radio-inline">
                           <input type="radio" name="deposito"  id="deposito" value="1">NORDESTE
                           </label>
                           <label class="radio-inline">
                           <input type="radio" name="deposito" id="deposito" value="2">SUL
                           </label>
                        </div>
                     </div>
                     <div class="form-row">
                        <div>
                           <p class="navbar-text navbar-left" style="font-weight:900;">DETALHES PEDIDO</p>
                        </div>
                        <div class="box-body">
                           <table class="table table-condensed">
                              <tr>
                                 <th>CD PRODUTO</th>
                                 <th>PRODUTO</th>
                                 <th>QUANTIDADE</th>
                              </tr>
                               <tr>
                                 	<td>4</td>
                                 	<td>Caneta Branca</td>
                                 	<td>10.000</td>
                                 </tr>
                                 <tr>
                                 	<td>5</td>
                                 	<td>Caneta Azul</td>
                                 	<td>5.000</td>
                                 </tr>
                                 <tr>
                                 	<td>6</td>
                                 	<td>Caneta Magenta</td>
                                 	<td>2.5000</td>
                                 </tr>
                           </table>
                        </div>
                     </div>
                  </form>
               </div>
               <div class="modal-footer" id="divModalVenda">
               </div>
            </div>
         </div>
      </div>
      <!-- FIM MODAL PEDIDO DE VENDA -->
      
      <!-- INICIO  MODAL CONFIRMAÇÃO PEDIDO VENDA -->
      
      <div class="modal fade bd-example-modal-md" tabindex="-1" id="confPedVenda" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-md">
    		<div class="modal-content">
    			<div class="modal-header">
        			<div class="alert alert-warning" role="alert">CONFIRMAÇÃO PEDIDO DE VENDA</div>
      			</div>
      			<div class="modal-body">
        			<p>Pedido de distribuicao gravado com sucesso e ordem de distribuicao gerada.</p>
      			</div>
      			<div class="modal-footer">
					<a class="btn btn-primary" href="relatorio?opt=log">OK</a>
      			</div>
    		</div>
  		 </div>
		</div>
      
        <!-- FIM MODAL CONFIRMAÇÃO PEDIDO VENDA -->
      
      <!-- MODAL INICIO ORDEM DISTRIBUICAO -->
      <div class="modal fade bd-example-modal-lg" tabindex="-1" id="pedDistribuicao" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
         <div class="modal-dialog modal-lg">
            <div class="modal-content">
               <div class="modal-header">
                  <div class="alert alert-info" role="alert">
                     RECEBIMENTO ORDEM DE COMPRA
                     <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                     <span aria-hidden="true">&times;</span>
                     </button>
                  </div>
               </div>
               <div class="modal-body">
                  <form id='pedVenda'>
                     <div class="form-row">
                        <div class="form-group col-md-6">
                           <label for="cliente" class="col-form-label">FORNECEDOR</label>
                           <input type="text" class="form-control" id="iFornecedor" disabled>
                        </div>
                        <div class="form-group col-md-3">
                           <label for="dataPedido" class="col-form-label">DATA RECEBIMENTO</label>
                           <input type="text" class="form-control" id="idataDistribuicao" disabled>
                        </div>
                        <div class="form-group col-md-3">
                           <label for="nPedidoVenda" class="col-form-label">Nº ORDEM COMPRA</label>
                           <input type="number" class="form-control" id="iNumeroOC" disabled>
                        </div>
                        </div>
                        <div class="form-row">
                           <div>
                              <p class="navbar-text navbar-left" style="font-weight:900;">DETALHES PEDIDO</p>
                           </div>
                           <div class="box-body">
                              <table class="table table-condensed">
                                 <tr>
                                    <th>CD PRODUTO</th>
                                    <th>PRODUTO</th>
                                    <th>QUANTIDADE</th>
                                 </tr>
                                 <tr>
                                 	<td>1</td>
                                 	<td>Polipropileno</td>
                                 	<td>10.000</td>
                                 </tr>
                                 <tr>
                                 	<td>2</td>
                                 	<td>Carga</td>
                                 	<td>5.000</td>
                                 </tr>
                                 <tr>
                                 	<td>3</td>
                                 	<td>Ponta Metalica Composta</td>
                                 	<td>2.5000</td>
                                 </tr>
                                 <tr>
                                 	<td>7</td>
                                 	<td>Corante Branco</td>
                                 	<td>1000</td>
                                 </tr>
                                 <tr>
                                 	<td>8</td>
                                 	<td>Corante Azul</td>
                                 	<td>5000</td>
                                 </tr>
                              </table>
                           </div>
                        </div>
                  </form>
                  </div>
                  <div class="modal-footer" id="divModalCompra">
                  </div>
               </div>
            </div>
         </div>
         <!-- FIM MODAL ORDEM DISTRIBUICAO-->
         
		<!-- INICIO  MODAL CONFIRMAÇÃO PEDIDO DISTRIBUIÇÃO -->
      
      	<div class="modal fade bd-example-modal-md" tabindex="-1" id="confPedCom" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
	  	<div class="modal-dialog modal-md">
    		<div class="modal-content">
    			<div class="modal-header">
        			<div class="alert alert-info" role="alert">CONFIRMAÇÃO RECEBIMENTO ORDEM DE COMPRA</div>
      			</div>
      			<div class="modal-body">
        			<p>Recebimento Gravado Com Sucesso.</p>
      			</div>
      			<div class="modal-footer">
      				<a class="btn btn-primary" href="relatorio?opt=log">OK</a>
      			</div>
      		</div>
  		 </div>
		</div>
      
        <!-- FIM MODAL CONFIRMAÇÃO PEDIDO DISTRIBUIÇÃO -->
        
         
         <!-- /.content-wrapper -->
         <footer class="main-footer">
            <div id="footer" class="span12">
               2017 &copy; Faber Canetas. Trazido para você por <a href="#">TDS Tecnologia</a>
            </div>
         </footer>
      </div>
        
      <!-- ./wrapper -->
      <script src="js/bootstrap.min.js"></script>
      <script src="js/app.min.js"></script>
      <script type="text/javascript" src="js/posicaoEstoqueCentralChart.js"></script>
      <script type="text/javascript" src="js/posicaoEstoqueSulChart.js"></script>
      <script type="text/javascript" src="js/posicaoEstoqueNordesteChart.js"></script>
   </body>
</html>
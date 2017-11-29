<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<meta charset="UTF-8" />
<meta http-equiv="Content-Type" content="text/html">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta name="description" content="Página de formulário cadastro ordem de compra"/>
<title>Cadastro Ordem de Compra | Faber Canetas</title>
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/font-awesome.min.css"/>
<link rel="stylesheet" href="css/AdminLTE.min.css">
<link rel="stylesheet" href="css/_all-skins.min.css">
<link rel="stylesheet" href="font-awesome/css/font-awesome.css" />
<link rel="stylesheet" href="font-awesome/css/font-family.css" />
<script src="js/jquery-3.2.1.js"></script>
</head>

<body class="skin-blue sidebar-mini sidebar-open sidebar-collapse">
	<div class="wrapper">

		<header class="main-header">
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
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</a>

				<div class="navbar-custom-menu">
					<ul class="nav navbar-nav">
						<li class=""><a href="home?opt=99"><i class="icon icon-share-alt"></i><span class="text">Logout</span></a></li>
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
			<!-- Content Header (Page header) -->
		<section class="content-header">
      		<h1>FORMULÁRIO CADASTRO ORDEM DE COMPRA</h1>
      	</section>
      
      	<section class="content">

      		<!-- SELECT2 EXAMPLE -->
      		<div class="box box-default">
        		<div class="box-header with-border">
          			<h3 class="box-title">Dados Pedido</h3>
          			<div class="box-tools pull-right">
            			<button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
          			</div>
        		</div>
        		<!-- /.box-header -->
        		<div class="box-body">
          			<div class="row">
            			<div class="col-md-3">
              				<div class="form-group">
                				<label>CNPJ FORNECEDOR</label>
                				<input type="text" class="form-control">
              				</div>
             			</div>
            			<!-- /.col -->
            			<div class="col-md-6">
              				<div class="form-group">
                				<label>Razão Social</label>
          						<input type="text" class="form-control">
              				</div>
            			</div>
            			<!-- /.col -->
             			<div class="col-md-2">
              				<div class="form-group">
                				<label>Data Solicitação</label>
          						<input type="date" class="form-control">
              				</div>
            			</div>
            			<!-- /.col -->
            			<div class="col-md-1">
              				<div class="form-group">
                				<label>Prazo</label>
          						<input type="number" class="form-control">
              				</div>
            			</div>
            			<!-- /.col -->
          			</div>
          			<!-- /.row -->
           			<div class="box-header with-border">
          				<hr><h3 class="box-title">ITENS DE COMPRA</h3><hr>
          			</div>
          			<div class="row">
            			<div class="col-md-12">
              				<div class="form-group">
                				<table class='table table-bordered table-hover dataTable'>
                					<tr>
                						<th class='text-center'>PRODUTO</th>
                						<th  class='text-center'>QUANTIDADE</th>
                						<th  class='text-center'>VALOR UNITÁRIO</th>
                						<th  class='text-center'>TOTAL ITEM</th>
                					</tr>
                					<tr>
                						<td><input type="text" class="form-control" ></td>
                						<td><input type="text" class="form-control" ></td>
                						<td><input type="text" class="form-control" ></td>
                						<td><input type="text" class="form-control" ></td>
                					</tr>
                					<tr>
                						<td><input type="text" class="form-control" ></td>
                						<td><input type="text" class="form-control" ></td>
                						<td><input type="text" class="form-control" ></td>
                						<td><input type="text" class="form-control" ></td>
                					</tr>
                					<tr>
                						<td><input type="text" class="form-control" ></td>
                						<td><input type="text" class="form-control" ></td>
                						<td><input type="text" class="form-control" ></td>
                						<td><input type="text" class="form-control" ></td>
                					</tr>
                				</table>
              				</div>
             			</div>
            			<!-- /.col -->
          			</div>
          			<!-- /.row -->
        		</div>
        		<!-- /.box-body -->
      		</div>
      		<!-- /.box -->
    	</section>
    	<!-- /.content -->
	</div>
	<!-- /.content-wrapper -->

	<footer class="main-footer">
		<div id="footer" class="span12">
			2017 &copy; Faber Canetas. Trazido para você por <a href="#">TDS Tecnologia</a>
		</div>
	</footer>

</div>
<!-- ./wrapper -->

<script src="js/bootstrap.min.js"></script>
<!-- AdminLTE App -->
<script src="js/app.min.js"></script>

</body>
</html>
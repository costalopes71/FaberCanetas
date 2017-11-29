<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta http-equiv="Content-Type" content="text/html">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta name="description" content="Página do dashboard dos gerentes da Faber Canetas"/>
<meta http-equiv="refresh" content="30">
<title>Dashboard Gerencia - Faber Canetas</title>
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/font-awesome.min.css"/>
<!-- Theme style -->
<link rel="stylesheet" href="css/AdminLTE.min.css">
<link rel="stylesheet" href="css/_all-skins.min.css">
<link rel="stylesheet" href="css/meuCssDash.css"/>
<link rel="stylesheet" href="font-awesome/css/font-awesome.css" />
<link rel="stylesheet" href="font-awesome/css/font-family.css" />
<script src="js/highcharts.js"></script>
<script src="js/exporting.js"></script>
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
				<!-- sidebar menu: : style can be found in sidebar.less -->
				<ul class="sidebar-menu">
					<li class="active"><a href=""><i class="icon-bar-chart"></i><span>Dashboard</span></a>
						<ul class="treeview-menu">
			            	<li><a href="dashboard_gerente.jsp"><i class="fa fa-circle-o"></i>Gerencial</a></li>
			            	<li><a href="relatorio?opt=log"><i class="fa fa-circle-o"></i>Operacional</a></li>
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

			<!-- Main content -->
			<section class="content">
				<!-- Info boxes -->
				<div class="row">
					
					<c:forEach items="${listaTicket}" var="ticket">
						<div class="col-lg-4 col-xs-6">
							<!-- small box -->
							<div class="small-box bg-${ticket.corBox} boxesTicket">
								<div class="inner">
									<p>${ticket.local}</p>
									<h3>${ticket.valor}</h3>
								</div>
							</div>
						</div>
					</c:forEach>
					<!-- ./col -->
					
					<!-- fix for small devices only -->
					<div class="clearfix visible-sm-block"></div>

				</div>
				<!-- /.row -->

				<div class="row">
					<div class="col-md-12">
						<div class="box">
							<div class="box-header with-border">
								<h3 class="box-title pull-center"><i class="icon-bar-chart"></i> Gráficos</h3>
								<div class="box-tools pull-right">
									<button type="button" class="btn btn-box-tool" data-widget="collapse">
										<i class="fa fa-minus"></i>
									</button>

								</div>
							</div>
							<!-- /.box-header -->
							<div class="box-body">
								<div class="row">
									<div class="col-md-4">
										<p class="text-center">
											<strong>PRINCIPAIS CLIENTES</strong>
										</p>

										<div class="chart">
											<div class="widget-content">
												<div id="rankingCliente" style="height: 260px; width: 100%;"></div>
											</div>
										</div>
										<!-- /.chart-responsive -->
									</div>
									<!-- /.col -->
									<div class="col-md-4">
										<p class="text-center">
											<strong>FATURAMENTO P/ REGIAO</strong>
										</p>

										<div class="chart">
											<div class="widget-content">
												<div id="faturamentoRegiao"
													style="height: 260px; width: 100%;"></div>
											</div>
										</div>
										<!-- /.chart-responsive -->
									</div>
									<!-- /.col -->
									<div class="col-md-4">
										<p class="text-center">
											<strong>PRODUTOS MAIS VENDIDOS</strong>
										</p>

										<div class="chart">
											<div class="widget-content">
												<div id="rankingProduto" style="height: 260px; width: 100%;"></div>
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
					<div class="col-md-12">
						<div class="box">
							<div class="box-header with-border">
								<h3 class="box-title"><i class="icon-bar-chart"></i> Faturamento Mensal</h3>

								<div class="box-tools pull-right">
									<button type="button" class="btn btn-box-tool" data-widget="collapse">
										<i class="fa fa-minus"></i>
									</button>

								</div>
							</div>
							<!-- /.box-header -->
							<div class="box-body">
								<div class="row">
									<div class="col-md-12">
										<p class="text-center"></p>

										<div class="chart">
											<div class="widget-content">
												<div id="faturamentoMensal"
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
			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->

		<footer class="main-footer">
			<div id="footer" class="span12">
				2017 &copy; Faber Canetas. Trazido para você por <a href="#">TDS
					Tecnologia</a>
			</div>
		</footer>



	</div>
	<!-- ./wrapper -->

	<!-- jQuery 2.2.3 -->
	<script src="js/bootstrap.min.js"></script>
	<!-- AdminLTE App -->
	<script src="js/app.min.js"></script>

	<script type="text/javascript" src="js/rankingClienteChart.js"></script>
	<script type="text/javascript" src="js/faturamentoRegiaoChart.js"></script>
	<script type="text/javascript" src="js/rankingProdutoChart.js"></script>
	<script type="text/javascript" src="js/faturamentoMensalChart.js"></script>

</body>
</html>
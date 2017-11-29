<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="Pagina de login no sistema que registra pedidos de compra da empresa Faber Canetas">
<title>Login - Faber Canetas</title>
<link rel="shortcut icon" href="img/iconeTitulo2.ico">
<link href="css/bootstrap.css" rel="stylesheet">
<link href="css/cover.css" rel="stylesheet">
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script type="text/javascript" src="js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript" src="js/meujs.js"></script>
</head>

<body>

	<div class="site-wrapper">
		<div class="site-wrapper-inner">
			<div class="cover-container">
				<div class="masthead clearfix">
					<div class="inner">
						<h3 class="masthead-brand">Faber Canetas</h3>
						<nav>
							<ul class="nav masthead-nav">
								<li class="active"><a href="#">Home</a></li>
								<li><a href="#janela1" rel="modal" id="area-func">Área Funcionário</a></li>
								<li><a href="#" class="dropdown-toggle"	data-toggle="dropdown"> <img src="img/br.png"
										class="flag flag-br" alt="Brasil"> <span>Português (BR) </span>
										<i class="fa fa-angle-down"></i></a>
									<ul class="dropdown-menu pull-right">
										<li class="active">
										<a href="#"><img src="img/br.png" class="flag flag-br" alt="Brasil">Português</a></li>
										<li><a href="#"><img src="img/us.png" class="flag flag-us" alt="United States">English (US)</a></li>
									</ul></li>
							</ul>
						</nav>
					</div>
				</div>

				<div class="inner cover" id="formId">
					<h1 class="cover-heading col-sm-offset-1">LOGIN</h1>
					<form class="form-horizontal">
						<div class="form-group">
							<label class="control-label col-sm-offset-2 col-sm-2" for="iEmail">E-mail</label>
							<div class="col-sm-5">
								<input type="text" class="form-control"	placeholder="Digite seu e-mail" 
								name="email" id="iEmailC">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-offset-2 col-sm-2" for="iSenha">Senha</label>
							<div class="col-sm-5">
								<input type="password" class="form-control"	placeholder="Digite sua senha" name="senha" id="iSenha">
							</div>
						</div>
						<input type="hidden" name="opt" value="loginCliente" id="iOpt">
						<div class="form-group">
							<div class="col-sm-offset-4 col-sm-5">
								<input type="button" class="btn btn-lg btn-default"	value="LOGIN" onclick="validaLogin();">
							</div>
						</div>
						
					</form>
					<div id="messageErrorLogin"></div>
				</div>

				<div class="mastfoot">
					<div class="inner">
						<p>
							Criado por: <a href="#">TDS Tecnologia de Software</a>, <br>by <a
								href="#">@TDSTecnologia</a>.
						</p>
					</div>
				</div>

			</div>

		</div>

	</div>
	
	<!-- INICIO DO MODAL DA AREA DO FUNCIONARIO -->
	<div class="window" id="janela1">
      <a href="#" class="fechar">Fechar</a>
      <h4>ÁREA RESTRITA</h4>
      <p id="pAreaRestrita">Esta área é de uso restrito aos funcionários da empresa.</p>
      <form class="form-horizontal">
        <div class="form-group">
        	<label class="control-label col-sm-3" id="labelEmail" for="iEmail">Email</label>
        	<div class="col-sm-8">
        		<input class="form-control" type="text" name="nEmail" id="iEmail">
        	</div>
        </div>
        <div class="form-group">
        	<label class="control-label col-sm-3" id="labelPass" for="iPassword">Password</label>
        		<div class="col-sm-8">
        		<input class="form-control" type="password" name="nPassword" id="iPassword">
        	</div>
        </div>
        <div class="form-group">
        	<div class="">
        		<input type="hidden" value="loginFuncionario" name="opt" id="iOpt2">
        		<input type="button" class="btn btn-lg btn-default"	style="border: 1px solid black;" value="LOGIN" id="bEntrar" onclick="validaLoginF();">
        	</div>
        </div>
      </form>
      <div id="messageErrorLogin2"></div>
    </div>

    <!-- mascara para cobrir o site -->
    <div id="mascara"></div>
	
	<!-- FIM DO MODAL DA AREA DO FUNCIONARIO -->
	
</body>
</html>

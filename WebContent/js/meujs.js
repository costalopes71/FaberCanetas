$(document).ready(function(){
    $("a[rel=modal]").click( function(ev){
        ev.preventDefault();

        var id = $(this).attr("href");

        var alturaTela = $(document).height();
        var larguraTela = $(window).width();

        //colocando o fundo preto
        $('#mascara').css({'width':larguraTela,'height':alturaTela});
        $('#mascara').fadeIn(1000);
        $('#mascara').fadeTo("slow",0.8);

        var left = ($(window).width() /2) - ( $(id).width() / 2 );
        var top = ($(window).height() / 2) - ( $(id).height() / 2 );

        $(id).css({'top':top,'left':left});
        $(id).show();
    });

    $("#mascara").click( function(){
        $(this).hide();
        $(".window").hide();
    });

    $('.fechar').click(function(ev){
        ev.preventDefault();
        $("#mascara").hide();
        $(".window").hide();
    });
});

function validaLogin() {
	var login = { 
		email:$("#iEmailC").val(), 
		senha:$("#iSenha").val(),
		opt:$("#iOpt").val()
	};
	
	$.ajax({
		url: 'http://localhost:8080/FaberCanetas/login',
		type: 'POST',
		data: login,
		success:function(data){
           if(data == "false") {
        	    $("#formId").effect("shake");
        		$("#messageErrorLogin").html("Invalid email or password.");
           } else {
        	   window.location = "http://localhost:8080/FaberCanetas/home";
           }
        }
	})
}

function validaLoginF() {
	var login = { 
		email:$("#iEmail").val(), 
		senha:$("#iPassword").val(),
		opt:$("#iOpt2").val()
	};
	
	$.ajax({
		url: 'http://localhost:8080/FaberCanetas/login',
		type: 'POST',
		data: login,
		success:function(data){
           if(data == "trueGerente") {
        	   window.location = "http://localhost:8080/FaberCanetas/relatorio?opt=ticket";
           } else if (data == "true"){
        	   window.location = "http://localhost:8080/FaberCanetas/relatorio?opt=log";
           } else {
        	   $("#janela1").effect("shake");
        	   $("#messageErrorLogin2").html("Invalid email or password.");
           }
        }
	})
}


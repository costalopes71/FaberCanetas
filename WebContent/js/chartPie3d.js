$.ajax({
	url: 'http://localhost:8080/FaberCanetas/relatorio?opt=1',
	type: 'POST',
	data: null,
	success:function(data){
		var resultado = JSON.parse(data);
		var resultadoTuple = [];
		for (var i=0;i<resultado.length;i++) {
		   var r = resultado[i];
		   var name = r.name;
		   var qtd = parseInt(r.qtd);
		   resultadoTuple.push([name, qtd]);
		}
		
		Highcharts.chart('pie3d', {
		    chart: {
		        type: 'pie',
		        options3d: {
		            enabled: true,
		            alpha: 45
		        }
		    },
		    title: {
		        text: ''
		    },
		    plotOptions: {
		        pie: {
		            innerSize: 100,
		            depth: 45
		        }
		    },
		    series: [{
		        name: 'Quantidade Vendida',
		        data: resultadoTuple
		    }]
		});
		
	}
})
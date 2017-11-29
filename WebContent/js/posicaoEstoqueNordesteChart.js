$.ajax({
	url: 'http://localhost:8080/FaberCanetas/relatorio?opt=6',
	type: 'POST',
	data: null,
	success:function(data){
		var resultado = JSON.parse(data);

		Highcharts.chart('estoqueNordeste', {

			xAxis: {
				categories: ['Branco', 'Azul', 'Magenta']
			},
			title: {
	            text: ''
	        },
			series: [{
				type: 'column',
				name: 'QTDE Estoque',
				data: resultado.qtd
			}, {
				type: 'spline',
				name: 'Estoque Minimo',
				data: resultado.minimo,
				marker: {
					lineWidth: 2,
					lineColor: Highcharts.getOptions().colors[3],
					fillColor: 'white'
				}
			}]
		});
	}
})


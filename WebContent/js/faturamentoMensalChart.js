$.ajax({
	url: 'http://localhost:8080/FaberCanetas/relatorio?opt=4',
	type: 'POST',
	data: null,
	success:function(data){
		var resultado = JSON.parse(data);

	Highcharts.chart('faturamentoMensal', {
		chart: {
			type: 'line'
		},
		title: {
			text: ''
		},
		xAxis: {
			categories: ['Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun', 'Jul', 'Ago', 'Set', 'Out', 'Nov', 'Dez']
		},
		yAxis: {
			title: {
				text: 'Faturamento Mensal'
			}
		},
		plotOptions: {
			line: {
				dataLabels: {
					enabled: true
				},
				enableMouseTracking: false
			}
		},
		series: [{
			name: 'Total',
			data: resultado
		}]
	});
	}
})
	
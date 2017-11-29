$.ajax({
	url: 'http://localhost:8080/FaberCanetas/relatorio?opt=1',
	type: 'POST',
	data: null,
	success:function(data){
		var resultado = JSON.parse(data);
			
		//Build the chart
		Highcharts.chart('rankingCliente', {
			chart: {
				plotBackgroundColor: null,
				plotBorderWidth: null,
				plotShadow: false,
				type: 'pie'
			},
			title: {
				text: ''
			},
			plotOptions: {
				pie: {
					allowPointSelect: true,
					cursor: 'pointer',
					dataLabels: {
						enabled: false
					},
					showInLegend: true
				}
			},
			series: [{
				name: 'R$',
				colorByPoint: true,
				data: resultado
			}]
		});
	}
})
$.ajax({
	url: 'http://localhost:8080/FaberCanetas/relatorio?opt=3',
	type: 'POST',
	data: null,
	success:function(data){
		var resultado = JSON.parse(data);

// Build the chart
    Highcharts.chart('faturamentoRegiao', {
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
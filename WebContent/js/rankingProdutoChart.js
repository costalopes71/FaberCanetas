$.ajax({
	url: 'http://localhost:8080/FaberCanetas/relatorio?opt=2',
	type: 'POST',
	data: null,
	success:function(data){
		var resultado = JSON.parse(data);

// Build the chart
    Highcharts.chart('rankingProduto', {
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
            name: 'Unidades',
            colorByPoint: true,
            data: resultado
        }]
    });
	}
})

<%@ page import="java.util.*"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%-- <div class="container-wrapper">
	<div class="container">
		<h2>
			<spring:message code="product.title" />
		</h2>
		<p>${param.lang}</p>

		<table class="table table-striped">
			<thead>
				<tr class="bg-success">
					<th><spring:message code="product.name" /></th>
					<th><spring:message code="product.category" /></th>
					<th><spring:message code="product.price" /></th>
					<th><spring:message code="product.manufacture" /></th>
					<th><spring:message code="product.unitInstock" /></th>
					<th><spring:message code="product.description" /></th>

				</tr>
			</thead>
			<tbody>

				<c:forEach var="product" items="${products}">
					<tr>
						<td>${product.name}</td>
						<td>${product.category}</td>
						<td>${product.price}</td>
						<td>${product.manufacture }</td>
						<td>${product.unitInStock }</td>
						<td>${product.description }</td>

					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div> --%>

<script>
	$(document).ready(function() {

		$.ajax({

			url : '/covid',
			type : 'get',
			dataType : 'json',

			success : function(data) {

				//var jsonArray = new Array();

				//console.log(data)

				amChart(data);
			}

		});

		$.ajax({

			url : '/covidstatus',
			type : 'get',
			dataType : 'json',

			success : function(data) {
				console.log(data);
				
				pieChart(data);
			}

		});

	});
</script>


<!-- Styles -->
<style>
#chartdiv {
	width: 30%;
	height: 500px;
}
</style>

<!-- Resources -->
<script src="https://cdn.amcharts.com/lib/4/core.js"></script>
<script src="https://cdn.amcharts.com/lib/4/charts.js"></script>
<script src="https://cdn.amcharts.com/lib/4/themes/animated.js"></script>

<!-- Chart code -->
<script>
	function amChart(data) {
		am4core.ready(function() {

			// Themes begin
			am4core.useTheme(am4themes_animated);
			// Themes end

			var chart = am4core.create("chartdiv", am4charts.XYChart);
			chart.hiddenState.properties.opacity = 0; // this creates initial fade-in

			chart.data = data;

			var categoryAxis = chart.xAxes.push(new am4charts.CategoryAxis());
			categoryAxis.renderer.grid.template.location = 0;
			categoryAxis.dataFields.category = "sido";
			categoryAxis.renderer.minGridDistance = 40;
			categoryAxis.fontSize = 11;

			var valueAxis = chart.yAxes.push(new am4charts.ValueAxis());
			valueAxis.min = 0;
			valueAxis.max = 600000;
			valueAxis.strictMinMax = true;
			valueAxis.renderer.minGridDistance = 30;
			// axis break
			var axisBreak = valueAxis.axisBreaks.create();
			axisBreak.startValue = 110000;
			axisBreak.endValue = 500000;
			//axisBreak.breakSize = 0.005;

			// fixed axis break
			var d = (axisBreak.endValue - axisBreak.startValue)
					/ (valueAxis.max - valueAxis.min);
			axisBreak.breakSize = 0.1 * (1 - d) / d;

			// make break expand on hover
			var hoverState = axisBreak.states.create("hover");
			hoverState.properties.breakSize = 1;
			hoverState.properties.opacity = 0.1;
			hoverState.transitionDuration = 1500;

			axisBreak.defaultState.transitionDuration = 1000;

			var series = chart.series.push(new am4charts.ColumnSeries());
			series.dataFields.categoryX = "sido";
			series.dataFields.valueY = "accumulatedFirstCnt";
			series.columns.template.tooltipText = "{valueY.value}";
			series.columns.template.tooltipY = 0;
			series.columns.template.strokeOpacity = 0;

			// as by default columns of the same series are of the same color, we add adapter which takes colors from chart.colors color set
			series.columns.template.adapter.add("fill", function(fill, target) {
				return chart.colors.getIndex(target.dataItem.index);
			});

		}); // end am4core.ready()
	}
</script>

<!-- HTML -->
<div id="chartdiv"></div>



<!-- Styles -->
<style>
#chartdiv {
	width: 100%;
	height: 500px;
}
</style>

<!-- Resources -->
<script src="https://cdn.amcharts.com/lib/4/core.js"></script>
<script src="https://cdn.amcharts.com/lib/4/charts.js"></script>
<script src="https://cdn.amcharts.com/lib/4/themes/animated.js"></script>

<!-- Chart code -->
<script>
	function pieChart(data) {

		am4core.ready(function() {

			// Themes begin
			am4core.useTheme(am4themes_animated);
			// Themes end

			// Create chart instance
			var chart = am4core.create("chartdiv", am4charts.PieChart);

			// Add data
			chart.data = data;

			// Add and configure Series
			var pieSeries = chart.series.push(new am4charts.PieSeries());
			pieSeries.dataFields.value = "litres";
			pieSeries.dataFields.category = "country";
			pieSeries.slices.template.stroke = am4core.color("#fff");
			pieSeries.slices.template.strokeOpacity = 1;

			// This creates initial animation
			pieSeries.hiddenState.properties.opacity = 1;
			pieSeries.hiddenState.properties.endAngle = -90;
			pieSeries.hiddenState.properties.startAngle = -90;

			chart.hiddenState.properties.radius = am4core.percent(0);

		}); // end am4core.ready()
	}
</script>

<!-- HTML -->
<div id="chartdiv"></div>


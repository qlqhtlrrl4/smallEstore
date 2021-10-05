<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.*"%>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js"></script>

<script type="text/javascript">
	$(document).ready(function() {

		setInterval(realTime, 1000);
	});

	function realTime() {
		var today = new Date();
		document.getElementById('currentTime').innerText = today;

	}
</script>

<div class="container-fluid bg-primary pb-5 pt-5 pt-md-5">
	<h2 class="mb-3 text-white text-center">Covid Info</h2>
	<h5 class="mb-5 text-white text-center" id="currentTime">=======</h5>
	<div class="row mx-3">
		<div class="col-lg-3 col-md-6">
			<div class="card card-stats mb-4 mb-xl-0">
				<div class="card-body">
					<div class="row">
						<div class="col">
							<h5 class="card-title text-muted text-center mb-1 ml-3">총
								확진자</h5>
							<p class="h2 text-danger text-center font-weight-bold mb-0 ml-3"
								id="totalConfirm">========</p>
						</div>
						<div class="col-auto">
							<div
								class="icon icon-shape bg-danger text-white rounded-circle shadow">
								<i class="fa fa-chart-bar"></i>
							</div>
						</div>
					</div>
					<p class="mt-3 mb-0 text-muted text-sm">
						<span class="text-danger"><i class="fa fa-arrow-up"></i></span> <span
							class="text-danger pr-2" id="totalConfirmIncrease"></span> <span
							class="text-nowrap" id="totalUpdateDate"></span>
					</p>
				</div>
			</div>
		</div>
		<div class="col-xl-3 col-md-6">
			<div class="card card-stats mb-4 mb-xl-0">
				<div class="card-body">
					<div class="row">
						<div class="col">
							<h5 class="card-title text-muted text-center mb-1 ml-3">사망자</h5>
							<p class="h2 font-weight-bold text-center ml-3 mb-0" id="dead">======</p>
						</div>
						<div class="col-auto">
							<div
								class="icon icon-shape bg-warning text-white rounded-circle shadow">
								<i class="fa fa-chart-pie"></i>
							</div>
						</div>
					</div>
					<p class="mt-3 mb-0 text-muted text-sm">
						<span class="text-danger"><i class="fa fa-arrow-down"></i></span>
						<span class="text-danger pr-2" id="deadIncrease"></span> <span
							class="text-nowrap">aa</span>
					</p>
				</div>
			</div>
		</div>
		<div class="col-xl-3 col-md-6">
			<div class="card card-stats mb-4 mb-xl-0">
				<div class="card-body text-center">
					<div class="row">
						<div class="col auto">
							<h5 class="card-title text-muted mb-1">완치자</h5>

							<p class="h2 font-weight-bold text-center mb-0 ml-3" id="healer">--</p>
						</div>
						<div class="col">
							<p
								class="icon icon-shape bg-yellow text-white rounded-circle shadow">
								<i class="fa fa-users"></i>
							</p>
						</div>
					</div>
					<p class="mt-3 mb-0 text-muted text-sm">
						<span class="text-warning"><i class="fa fa-arrow-down"></i></span>
						<span class="text-warning pr-2" id="healIncrease"></span> <span
							class="text-nowrap">완치날짜</span>
					</p>
				</div>
			</div>
		</div>
		<div class="col-xl-3 col-md-6">
			<div class="card card-stats mb-4 mb-xl-0">
				<div class="card-body">
					<div class="row">
						<div class="col">
							<h5 class="card-title text-muted mb-1">검사자</h5>
							<span class="h2 font-weight-bold mb-0  ml-3" id="inspector">ㅁㅁㅁ</span>
						</div>
						<div class="col-auto">
							<div
								class="icon icon-shape bg-info text-white rounded-circle shadow">
								<i class="fa fa-percent"></i>
							</div>
						</div>
					</div>
					<p class="mt-3 mb-0 text-muted text-sm">
						<span class="text-success mr-2"><i class="fa fa-arrow-up"></i></span>
						<span class="text-success pr-2" id="inspectorIncrese"></span> <span
							class="text-nowrap">Since last month</span>
					</p>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- <div class="container-fluid pb-5 pt-5">

	<div class="row mx-3">
		<div class="col-lg-6 col-md-12">
			<div class="card card-stats mb-4 mb-xl-0">
				<div class="card-body">
					<div class="row">
						<div class="col">
							<h5 class="card-title text-muted mb-0">확진자 수</h5>
							<span class="h2 font-weight-bold pt-2" id="todayConfirm">ㅁㅁㅁ</span>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="col-lg-6 col-md-12">
			<div class="card card-stats mb-4 mb-xl-0">
				<div class="card-body"></div>
			</div>
		</div>
	</div>

</div> -->

<div class="container-fluid pb-5 pt-5 px-auto">
	<div class="row mx-3">
		<div class="col-lg-12">
			<h5 class="mb-3">Covid 추세(최근 14일 기준)</h5>
			<div class="card card-stats mb-4 mb-xl-0">
				<div class="card-body">
					<div id="chartdiv"></div>
					<form action="/covidStatusDataDownLoad" method="get">
						<input type="submit" value="Excel Down">
					</form>
						<input type="button" value="Export PNG" id="image"/>
				</div>
			</div>
		</div>
	</div>
</div>


<!-- Page content -->

<script>
	

	$(document).ready(function() {
		
						
						$.ajax({
							
							url : '/leastDay',
							type : 'get',
							dataType : 'json',
							
							success : function(data) {
								console.log(data);
								lineChart(data);
							}
						});
						
						$.ajax({

							url : '/covid',
							type : 'get',
							dataType : 'json',

							success : function(data) {

							}

						});

						$.ajax({

							url : '/covidstatus',
							type : 'get',
							dataType : 'json',
							success : function(data) {
								var today = new Date();
								
								  	
								for (var i = 0; i < data.length; i++) {
									console.log(moment(today).format('YYYYMMDD'));
									if ($.trim(data[i].stateDt).toString() == moment(today).format('YYYYMMDD').toString()) {
										
										$('#healer').text(data[i].clearCnt.toString()
														.replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g,","));
										
										$('#dead').text(data[i].deathCnt.toString()
														.replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g,","));
										
										$('#totalConfirm').text(data[i].decideCnt.toString()
														.replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g,","));
											
										$('#inspector').text(data[i].accExamCnt.toString()
														.replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g,","));
										
										$('#todayConfirm').text((data[i].decideCnt - data[i - 1].decideCnt).toString()
														.replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g,","));
											
										$('#totalConfirmIncrease').text((data[i].decideCnt - data[i - 1].decideCnt).toString()
														.replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g,","));

										$('#deadIncrease').text((data[i].deathCnt - data[i - 1].deathCnt).toString()
														.replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g,","));
													
										$('#healIncrease').text((data[i].clearCnt - data[i - 1].clearCnt).toString()
														.replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g,","));
											
										$('#inspectorIncrese').text((data[i].accExamCnt - data[i - 1].accExamCnt).toString()
														.replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g,","));
										
										$('#totalUpdateDate').text(data[i].updateDt);
											
										}
									
									
										
								}
								//amChart(data);
								
								
							}

						});
					});
	
	
</script>


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
			categoryAxis.dataFields.category = "stateDt";
			categoryAxis.renderer.minGridDistance = 40;
			categoryAxis.fontSize = 11;

			var valueAxis = chart.yAxes.push(new am4charts.ValueAxis());
			valueAxis.min = 0;
			valueAxis.max = 600000;
			valueAxis.strictMinMax = true;
			valueAxis.renderer.minGridDistance = 30;
			
			var series = chart.series.push(new am4charts.ColumnSeries());
			series.dataFields.categoryX = "stateDt";
			series.dataFields.valueY = "accExamCnt";
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

<!-- Chart code -->
<script>
function lineChart(data) {
	am4core.ready(function() {

		// Themes begin
		am4core.useTheme(am4themes_animated);
		// Themes end

		// Create chart instance
		var chart = am4core.create("chartdiv", am4charts.XYChart);

		// Add data
		chart.data = data;

		// Set input format for the dates
		//chart.dateFormatter.inputDateFormat = "yyyy-MM-dd";

		// Create axes
		var dateAxis = chart.xAxes.push(new am4charts.DateAxis());
		var valueAxis = chart.yAxes.push(new am4charts.ValueAxis());

		// Create series
		var series = chart.series.push(new am4charts.LineSeries());
		series.dataFields.valueY = "decideCnt";
		series.dataFields.dateX = "createDt";
		series.tooltipText = "{createDt}"
		series.strokeWidth = 2;
		series.minBulletDistance = 10;

		// Drop-shaped tooltips
		series.tooltip.background.cornerRadius = 20;
		series.tooltip.background.strokeOpacity = 0;
		series.tooltip.pointerOrientation = "vertical";
		series.tooltip.label.minWidth = 40;
		series.tooltip.label.minHeight = 40;
		series.tooltip.label.textAlign = "middle";
		series.tooltip.label.textValign = "middle";

		// Make bullets grow on hover
		var bullet = series.bullets.push(new am4charts.CircleBullet());
		bullet.circle.strokeWidth = 2;
		bullet.circle.radius = 4;
		bullet.circle.fill = am4core.color("#fff");

		var bullethover = bullet.states.create("hover");
		bullethover.properties.scale = 1.3;

		// Make a panning cursor
		chart.cursor = new am4charts.XYCursor();
		chart.cursor.behavior = "panXY";
		chart.cursor.xAxis = dateAxis;
		chart.cursor.snapToSeries = series;

		/* // Create vertical scrollbar and place it before the value axis
		chart.scrollbarY = new am4core.Scrollbar();
		chart.scrollbarY.parent = chart.leftAxesContainer;
		chart.scrollbarY.toBack();

		// Create a horizontal scrollbar with previe and place it underneath the date axis
		chart.scrollbarX = new am4charts.XYChartScrollbar();
		chart.scrollbarX.series.push(series);
		chart.scrollbarX.parent = chart.bottomAxesContainer; */

		//dateAxis.start = 0.79;
		dateAxis.keepSelection = true;

		// Enable export
		/* var btn = document.getElementById('image');
		btn.onclick = function() {
			var tmp = new AmCharts.AmExport(chart);
			tmp.init();
			tmp.output({
				
			})
		} */
		
		document.getElementById("image").onclick= 
			function exportPNG() {
			  chart.exporting.export("png");
			};

		var options = chart.exporting.getFormatOptions("png");
		options.keepTainted = true;
		chart.exporting.setFormatOptions("png", options);
		
		
		}); // end am4core.ready()
	}



</script>





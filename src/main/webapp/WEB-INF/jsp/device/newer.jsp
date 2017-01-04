<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>Sagiller | User</title>
<%@ include file="/WEB-INF/common/base_js_css_top.jsp"%>
<!-- daterange picker -->
<link rel="stylesheet" href="${ctx}/static/plugins/daterangepicker/daterangepicker.css">
<!-- bootstrap datepicker -->
<link rel="stylesheet" href="${ctx}/static/plugins/datepicker/datepicker3.css">
</head>
<body class="hold-transition skin-blue sidebar-mini fixed">
	<!-- Site wrapper -->
	<div class="wrapper">
		<%@ include file="/WEB-INF/common/header.jsp"%>
		<%@ include file="/WEB-INF/common/sidebar.jsp"%>
		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>
					用户管理 <small>新增用户</small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="#"><i class="fa fa-dashboard"></i> 首页</a></li>
					<li><a href="#">用户管理</a></li>
					<li class="active">新增用户</li>
				</ol>
			</section>

			<!-- Main content -->
			<section class="content">
						<!-- Line CHART -->
						<div class="box box-primary">
							<div class="box-header with-border">
								<h3 class="box-title">新增用户趋势</h3>

								<div class="box-tools pull-right">
									<%--<button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>--%>
									<%--</button>--%>
									<%--<button type="button" class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>--%>
                                        <!-- Date and time range -->
                                        <div class="form-group">

                                            <div class="input-group">
                                                <button type="button" class="btn btn-default pull-right" id="daterange-btn">
                                                <span>
                                                  <i class="fa fa-calendar"></i> 选择周期
                                                </span>
                                                    <i class="fa fa-caret-down"></i>
                                                </button>
                                            </div>
                                        </div>
                                        <!-- /.form group -->
                                </div>
							</div>
							<div class="box-body">
								<div class="chart">
									<canvas id="lineChart" style="height:250px"></canvas>
								</div>
							</div>
							<!-- /.box-body -->
						</div>

			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->
		<%@ include file="/WEB-INF/common/footer.jsp"%>
		<%@ include file="/WEB-INF/common/controlbar.jsp"%>
	</div>
	<!-- ./wrapper -->

	<%@ include file="/WEB-INF/common/base_js_css_bottom.jsp"%>
	<!-- ChartJS -->
	<script src="${ctx}/static/plugins/chartjs/Chart.min.js"></script>
    <!-- date-range-picker -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.11.2/moment.min.js"></script>
    <script src="${ctx}/static/plugins/daterangepicker/daterangepicker.js"></script>


    <script>
		$(function () {
			/* ChartJS
			 * -------
			 * Here we will create a few charts using ChartJS
			 */

			//-------------
			//- LINE CHART -
			//--------------
            var labels = [],data=[];
            <c:forEach var="daily" items="${newerdailylist}" varStatus="status">
            labels.push("${daily.dateDisplay}");
            data.push("${daily.number}");
            </c:forEach>
			var lineChartData = {

//				labels: ["9-1", "9-2", "9-3", "9-4", "9-5", "9-6", "9-7", "9-8", "9-9", "9-10", "9-11", "9-12", "9-13", "9-14", "9-15", "9-16", "9-17", "9-18", "9-19","9-20", "9-21", "9-22", "9-23", "9-24", "9-25", "9-26", "9-27", "9-28", "9-29"],
				labels:labels,
                datasets: [
					{
						label: "newer",
						fillColor: "rgba(60,141,188,0.9)",
						strokeColor: "rgba(60,141,188,0.8)",
						pointColor: "#3b8bba",
						pointStrokeColor: "rgba(60,141,188,1)",
						pointHighlightFill: "#fff",
						pointHighlightStroke: "rgba(60,141,188,1)",
//						data: [28, 48, 40, 19, 86, 27, 90, 48, 40, 19, 86, 27, 90, 48, 40, 19, 86, 27, 90,28, 48, 40, 19, 86, 27, 90, 48, 40, 19]
					    data:data
					}
				]
			};
            var lineChartOptions = {
                //Boolean - If we should show the scale at all
                showScale: true,
                //Boolean - Whether grid lines are shown across the chart
                scaleShowGridLines: false,
                //String - Colour of the grid lines
                scaleGridLineColor: "rgba(0,0,0,.05)",
                //Number - Width of the grid lines
                scaleGridLineWidth: 1,
                //Boolean - Whether to show horizontal lines (except X axis)
                scaleShowHorizontalLines: true,
                //Boolean - Whether to show vertical lines (except Y axis)
                scaleShowVerticalLines: true,
                //Boolean - Whether the line is curved between points
                bezierCurve: true,
                //Number - Tension of the bezier curve between points
                bezierCurveTension: 0.3,
                //Boolean - Whether to show a dot for each point
                pointDot: false,
                //Number - Radius of each point dot in pixels
                pointDotRadius: 4,
                //Number - Pixel width of point dot stroke
                pointDotStrokeWidth: 1,
                //Number - amount extra to add to the radius to cater for hit detection outside the drawn point
                pointHitDetectionRadius: 20,
                //Boolean - Whether to show a stroke for datasets
                datasetStroke: true,
                //Number - Pixel width of dataset stroke
                datasetStrokeWidth: 2,
                //Boolean - Whether to fill the dataset with a color
                datasetFill: true,
                //String - A legend template
                //Boolean - whether to maintain the starting aspect ratio or not when responsive, if set to false, will take up entire container
                maintainAspectRatio: true,
                //Boolean - whether to make the chart responsive to window resizing
                responsive: true
            };
			var lineChartCanvas = $("#lineChart").get(0).getContext("2d");
			var lineChart = new Chart(lineChartCanvas);
			lineChartOptions.datasetFill = false;
			lineChart.Line(lineChartData, lineChartOptions);

            $('#daterange-btn').daterangepicker(
                    {
                        ranges: {
                            '今天': [moment(), moment()],
                            '昨天': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
                            '过去7天': [moment().subtract(6, 'days'), moment()],
                            '过去30天': [moment().subtract(29, 'days'), moment()],
                            '本月': [moment().startOf('month'), moment().endOf('month')],
                            '上月': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
                        },
                        startDate: moment().subtract(29, 'days'),
                        endDate: moment()
                    },
                    function (start, end) {
                        $('#daterange-btn span').html(start.format('YYYY-MM-DD') + ' - ' + end.format('YYYY-MM-DD'));
                    }
            );
		});
	</script>
</body>
</html>

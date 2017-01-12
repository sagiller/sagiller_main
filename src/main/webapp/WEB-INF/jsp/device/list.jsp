<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>Sagiller | User</title>
<%@ include file="/WEB-INF/common/base_js_css_top.jsp"%>
<!-- DataTables -->
<link rel="stylesheet" href="${ctx}/static/plugins/datatables/dataTables.bootstrap.css">
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
					用户管理 <small>列表</small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="#"><i class="fa fa-dashboard"></i> 首页</a></li>
					<li><a href="#">用户管理</a></li>
					<li class="active">列表页</li>
				</ol>
			</section>
            <!-- Main content -->
            <section class="content">
			<!-- Info boxes -->
			<div class="row">
				<div class="col-md-3 col-sm-6 col-xs-12">
					<div class="info-box">
						<span class="info-box-icon bg-aqua"><i class="ion ion-ios-gear-outline"></i></span>

						<div class="info-box-content">
							<span class="info-box-text">总用户</span>
							<span class="info-box-number">${global['total']}<small></small></span>
						</div>
						<!-- /.info-box-content -->
					</div>
					<!-- /.info-box -->
				</div>
				<!-- /.col -->
				<div class="col-md-3 col-sm-6 col-xs-12">
					<div class="info-box">
						<span class="info-box-icon bg-red"><i class="fa fa-google-plus"></i></span>

						<div class="info-box-content">
							<span class="info-box-text">昨日新增</span>
							<span class="info-box-number">${global['yesterdayNew']}</span>
						</div>
						<!-- /.info-box-content -->
					</div>
					<!-- /.info-box -->
				</div>
				<!-- /.col -->

				<!-- fix for small devices only -->
				<div class="clearfix visible-sm-block"></div>

				<div class="col-md-3 col-sm-6 col-xs-12">
					<div class="info-box">
						<span class="info-box-icon bg-green"><i class="ion ion-ios-cart-outline"></i></span>

						<div class="info-box-content">
							<span class="info-box-text">活跃用户</span>
							<span class="info-box-number">${global['activeDevice']}</span>
						</div>
						<!-- /.info-box-content -->
					</div>
					<!-- /.info-box -->
				</div>
				<!-- /.col -->
				<div class="col-md-3 col-sm-6 col-xs-12">
					<div class="info-box">
						<span class="info-box-icon bg-yellow"><i class="ion ion-ios-people-outline"></i></span>

						<div class="info-box-content">
							<span class="info-box-text">....</span>
							<span class="info-box-number">0</span>
						</div>
						<!-- /.info-box-content -->
					</div>
					<!-- /.info-box -->
				</div>
				<!-- /.col -->
			</div>
			<!-- /.row -->


				<!-- Default box -->
				<div class="box">
		            <!-- /.box-header -->
		            <div class="box-body">
						<table id="list" class="table table-bordered table-striped">
							<thead>
								<tr>
									<th>序列号</th>
									<th>IP地址</th>
									<th>设备型号</th>
									<th>系统版本</th>
									<th>Lake版本</th>
									<th>注册时间</th>
									<th class="nosort" >操作</th>
								</tr>
							</thead>
							
							<tbody>
								<c:forEach var="vo" items="${devicelist}">
									<tr>
										<td>${vo['serialNumber']}</td>
										<td>${vo['ip']}</td>
										<td>${vo['vendor']}/${vo['model']}</td>
										<td>${vo['osVersion']}</td>
										<td>${vo['appVersion']}</td>
										<td><fmt:formatDate value="${ vo['createTime'] }" type="both" dateStyle="medium"/></td>
										<td>
											<a class="blue" href="javascript:void(0);" onclick="" title="查看">
												<i class="fa fa-fw fa-chrome "></i>
											</a>
										</td>

									</tr>
								</c:forEach>
							</tbody>
							
							<tfoot>
								<tr>
                                    <th>序列号</th>
                                    <th>IP地址</th>
                                    <th>设备型号</th>
                                    <th>系统版本</th>
                                    <th>Lake版本</th>
                                    <th>注册时间</th>
                                    <th class="nosort" >操作</th>
								</tr>
							</tfoot>
						</table>
		            </div>
				</div>
				<!-- /.box -->

			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->
		<%@ include file="/WEB-INF/common/footer.jsp"%>
		<%@ include file="/WEB-INF/common/controlbar.jsp"%>
	</div>
	<!-- ./wrapper -->
	
	<%@ include file="/WEB-INF/common/base_js_css_bottom.jsp"%>
	<!-- DataTables -->
	<script src="${ctx}/static/plugins/datatables/jquery.dataTables.min.js"></script>
	<script src="${ctx}/static/plugins/datatables/dataTables.bootstrap.min.js"></script>
	
	<script>
		$(function () {
			$("#list").DataTable({
				//汉化
			    language: {
			        "sProcessing": "处理中...",
			        "sLengthMenu": "显示 _MENU_ 项结果",
			        "sZeroRecords": "没有匹配结果",
			        "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
			        "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
			        "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
			        "sInfoPostFix": "",
			        "sSearch": "搜索:",
			        "sUrl": "",
			        "sEmptyTable": "暂无信息",
			        "sLoadingRecords": "载入中...",
			        "sInfoThousands": ",",
			        "oPaginate": {
			            "sFirst": "首页",
			            "sPrevious": "上页",
			            "sNext": "下页",
			            "sLast": "末页"
			        },
			        "oAria": {
			            "sSortAscending": ": 以升序排列此列",
			            "sSortDescending": ": 以降序排列此列"
			        }
			    },
				//详细分页组，可以支持直接跳转到某页
			    "pagingType": "full_numbers",
			    //每页显示多少条选择
			    "lengthMenu": [ [ 5, 10, 25, 50, 75, 100, -1 ], [ 5, 10, 25, 50, 75, 100, "所有" ] ],
			    //默认显示条数
			    "displayLength": 10,
			    //默认排序行与排序方式
			    "order": [[ 1, "asc" ]],
			    //不排序的列的class
			    "columnDefs": [ {
			        "targets": 'nosort',
			        "orderable": false
			      } ]
			});
		});
	</script>
</body>
</html>

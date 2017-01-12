<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>Sagiller | Index</title>
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
					后台helloworld <small>首页</small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="#"><i class="fa fa-dashboard"></i> 首页</a></li>
				</ol>
			</section>

            <!-- Main content -->
            <section class="content">
                <div class="callout callout-info">
                    <h4>Welcome!</h4>

                    <p>系统已经更新到1.0.1版本，新增了一些特性。</p>
                </div>
                <!-- Default box -->
                <div class="box">
                    <div class="box-header with-border">
                        <h3 class="box-title">1.0.1</h3>

                        <div class="box-tools pull-right">
                            <button type="button" class="btn btn-box-tool" data-widget="collapse" data-toggle="tooltip" title="Collapse">
                                <i class="fa fa-minus"></i></button>
                            <button type="button" class="btn btn-box-tool" data-widget="remove" data-toggle="tooltip" title="Remove">
                                <i class="fa fa-times"></i></button>
                        </div>
                    </div>
                    <div class="box-body">
                        新增用户分析模块
                    </div>
                    <!-- /.box-body -->
                    <div class="box-footer">
                        2016年10月17日
                    </div>
                    <!-- /.box-footer-->
                </div>
                <!-- /.box -->
                <!-- Default box -->
                <div class="box">
                    <div class="box-header with-border">
                        <h3 class="box-title">1.0.0</h3>

                        <div class="box-tools pull-right">
                            <button type="button" class="btn btn-box-tool" data-widget="collapse" data-toggle="tooltip" title="Collapse">
                                <i class="fa fa-minus"></i></button>
                            <button type="button" class="btn btn-box-tool" data-widget="remove" data-toggle="tooltip" title="Remove">
                                <i class="fa fa-times"></i></button>
                        </div>
                    </div>
                    <div class="box-body">
                        新增设备管理模块
                    </div>
                    <!-- /.box-body -->
                    <div class="box-footer">
                        2016年10月03日
                    </div>
                    <!-- /.box-footer-->
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
</body>
</html>

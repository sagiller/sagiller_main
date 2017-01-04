<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- Left side column. contains the sidebar -->
<aside class="main-sidebar">
  <!-- sidebar: style can be found in sidebar.less -->
  <section class="sidebar">
    <!-- Sidebar user panel -->
    <div class="user-panel">
      <div class="pull-left image">
        <img src="${ctx}/static/dist/img/avatar11.jpg" class="img-circle" alt="User Image">
      </div>
      <div class="pull-left info">
        <p>Perry Hu</p>
        <a href="#"><i class="fa fa-circle text-success"></i> 在线</a>
      </div>
    </div>
    <!-- search form -->
    <form action="#" method="get" class="sidebar-form">
      <div class="input-group">
        <input type="text" name="q" class="form-control" placeholder="搜索...">
            <span class="input-group-btn">
              <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
              </button>
            </span>
      </div>
    </form>
    <!-- /.search form -->
    <!-- sidebar menu: : style can be found in sidebar.less -->
    <ul class="sidebar-menu">
      <li class="treeview">
        <a href="#">
          <i class="fa fa-pie-chart"></i>
          <span>用户分析</span>
          <i class="fa fa-angle-left pull-right"></i>
        </a>
        <ul class="treeview-menu">
          <li><a href="${ctx}/device/list"><i class="fa fa-circle-o"></i>用户列表</a></li>
          <li><a href="${ctx}/device/newer"><i class="fa fa-circle-o"></i>新增用户</a></li>
            <%--<li><a href="${ctx}/device/active"><i class="fa fa-circle-o"></i>活跃用户</a></li>--%>
        </ul>
      </li>
        <li class="treeview">
            <a href="#">
                <i class="fa fa-pie-chart"></i>
                <span>性能分析</span>
                <i class="fa fa-angle-left pull-right"></i>
            </a>
            <ul class="treeview-menu">
                <li><a href="${ctx}/analysis/imageloading"><i class="fa fa-circle-o"></i>图片加载速度</a></li>
            </ul>
        </li>
    </ul>
  </section>
  <!-- /.sidebar -->
</aside>

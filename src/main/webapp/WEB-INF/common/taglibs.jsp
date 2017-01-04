<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	request.setCharacterEncoding("utf-8");
	String path = request.getContextPath();
	String ctxPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String url = ctxPath + request.getServletPath().substring(0,request.getServletPath().lastIndexOf("/")+1);
	request.setAttribute("ctx", path);
	request.setAttribute("ctxPath", ctxPath);
	request.setAttribute("url", url);
	request.setAttribute("appname", "Sagiller Main");
%>
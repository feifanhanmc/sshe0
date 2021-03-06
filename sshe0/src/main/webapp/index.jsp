<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head> 
    <title>Index</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    <script type="text/javascript" src="jslib/jquery-easyui-1.3.1/jquery-1.8.0.min.js"></script>
    <script type="text/javascript" src="jslib/jquery-easyui-1.3.1/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="jslib/jquery-easyui-1.3.1/locale/easyui-lang-zh_CN.js"></script>
    <link rel="stylesheet" href="jslib/jquery-easyui-1.3.1/themes/metro/easyui.css" type="text/css"></link>
    <link rel="stylesheet" href="jslib/jquery-easyui-1.3.1/themes/icon.css" type="text/css"></link>
    <script type="text/javascript" src="jslib/syUtil.js"></script>
</head>

<body class="easyui-layout">
    <div data-options="region:'north',split:false,border:true" style="height:100px;">
    	<img src="image/head.png" width="100%" height="100%"/>
    </div>
    <div data-options="region:'south',title:'South Title',split:false,border:true" style="height:100px;"></div>
    <!-- 
    <div data-options="region:'east',title:'East',split:true,border:false,collapsible:false" style="width:180px;"></div>
     -->
     <div data-options="region:'east'"style="width:180px;">
     	<div class="easyui-panel" data-options="title:'快速链接',border:false,fit:true"></div>
     </div>
    <div data-options="region:'west'" style="width:180px;">
		<jsp:include page="layout/west.jsp"></jsp:include>
	</div>
    <div data-options="region:'center',title:'欢迎使用示例系统',border:false" style="overflow: hidden;">
		<jsp:include page="layout/center.jsp"></jsp:include>
	</div>
	
	<jsp:include page="user/login.jsp"></jsp:include>
	<!--
	<jsp:include page="user/reg.jsp"></jsp:include>
	-->
</body>
</html>

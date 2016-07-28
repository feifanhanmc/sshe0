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
    <div data-options="region:'north',title:'North Title',split:false,border:true" style="height:100px;"></div>
    <div data-options="region:'south',title:'South Title',split:false,border:true" style="height:100px;"></div>
    <div data-options="region:'east',title:'East',split:true,border:false" style="width:180px;"></div>
    <div data-options="region:'west'" style="width:180px;">
    	<div class ="easyui-panel" data-options="title:'功能导航',border:false,fit:true">
    		<div class="easyui-accordion" data-options="fit:true,border:false">
    			<div title="系统菜单" data-options="iconCls:'icon-tip'">
    				<!-- 
    					<ul class="easyui-tree" data-options="url:'${pageContext.request.contextPath}/menuAction!getAllTreeNode.action'
    						,parentField : 'pid'
    						,lines : true
    						,onLoadSuccess:function(node,data){$(this).tree('collapseAll')}"></ul>
    				 -->
    				<ul class="easyui-tree" data-options="url:'${pageContext.request.contextPath}/menuAction!getTreeNode.action',lines : true"></ul>
    			</div>
    			<div title="Title2" data-options="iconCls:'icon-search'">content2</div>
    		</div>
    	</div>
    </div> 
    <div data-options="region:'center',title:'center title',border:false" style="padding:5px;background:#eee;"></div>
    
	<jsp:include page="user/login.jsp"></jsp:include>
	<jsp:include page="user/reg.jsp"></jsp:include>

	
</body>
</html>

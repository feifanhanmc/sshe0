<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript">
	function addTab(opts) {
		var t = $('#layout_center_tabs');
		if (t.tabs('exists', opts.title)) {
			t.tabs('select', opts.title);
		} else {
			t.tabs('add', opts);
		}
	}
</script>
<body >
<div id="layout_center_tabs" class="easyui-tabs" data-options="fit:true" style="overflow: hidden;">
	<div title="首页"><img src="image/bg.jpg" width="100%" height="100%"/>
	</div>
</div>
</body>
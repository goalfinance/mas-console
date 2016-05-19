<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>Mas web console</title>
<link rel="shortcut icon" href="images/favicon.png" />

<link rel="stylesheet" href="/frameui/frameui.css" type="text/css" />
<link rel="stylesheet" href="/css/buttons.css" type="text/css" />
<link rel="stylesheet" href="/dojox/layout/resources/FloatingPane.css"
	type="text/css" />
<link rel="stylesheet" href="/dojox/layout/resources/ResizeHandle.css"
	type="text/css" />

<script type="text/javascript">
	dojoConfig = {
		async : true,
		locale : "en-gb"
	};
</script>
<script type="text/javascript" src="../dojo/dojo.js"></script>
<script type="text/javascript" src="/frameui/src.js" charset="utf-8"></script>

</head>
<body class="claro">
	<div id="preloader">Loading Application...</div>
	<script type="dojo/require">
	  "frame.dom": "dojo/dom",
	  "frame.registry": "dijit/registry",
	  "frame.request": "dojo/request",
      "dojox.grid.EnhancedGrid": "dojox/grid/EnhancedGrid",
	  "dojox.grid.enhanced.plugins.Pagination": "dojox/grid/enhanced/plugins/Pagination",
	  "dojox.grid.enhanced.plugins.Filter": "dojox/grid/enhanced/plugins/Filter",
	  "dojox/grid/enhanced/plugins.IndirectSelection": "dojox/grid/enhanced/plugins/IndirectSelection",
	  "dojo.store.JsonRest": "dojo/store/JsonRest",
	  "dojo.json": "dojo/json",
      "dojo.array":"dojo/_base/array"
</script>
	<script type="text/javascript">
		function showSrvErrmsg(error) {
			frameui.srvsideErrmsgDialog.set("href",
					"commons/show_error_message?errmsg=" + error["status"]);
			frameui.srvsideErrmsgDialog.show();
		}

		function multiSelectOptions2JsonString(multiSelect) {
			var i;
			var a = new Array();
			for (i = 0; i < multiSelect.length; i++) {
				var s = new Object();
				s.name = multiSelect.options[i].innerHTML;
				s.value = multiSelect.options[i].value;
				a[i] = s;
			}
			return dojo.json.stringify(a);
		}

		function jsonString2MultiSelectOptions(s, multiSelect) {
			var a = dojo.json.parse(s);
			var i;
			for (i = 0; i < a.length; i++) {
				var c = dojo.doc.createElement('option');
				c.innerHTML = a[i].name;
				c.value = a[i].value;
				multiSelect.appendChild(c);
			}
		}

		function addTab(container, rloc, title) {
			frame.addTab(container, rloc, title, true);
			return false;
		}
	</script>
	<div data-dojo-type="dijit/Dialog"
		data-dojo-id="frameui.srvsideErrmsgDialog"
		data-dojo-props="title:'Error message from server',style: 'width: 300px'"></div>
	<div data-dojo-type="dijit/Dialog"
		data-dojo-id="frameui.changePasswordDialog"
		id="security.maintaining.user.view.changePasswordDialog"
		data-dojo-props="title:'Change Password'"></div>
	<div data-dojo-type="dijit/layout/BorderContainer" id="mainContainer"
		data-dojo-props="gutters:true">
		<div data-dojo-type="dijit/layout/ContentPane" id="headerPane"
				data-dojo-props="splitter:false, region:'top', href:'/security/show_login'">
		</div>		
		<div data-dojo-type="dijit/layout/AccordionContainer"
			id="leftAccordion" data-dojo-props="region: 'leading', splitter:true" style="width: 250px;" >
			<c:choose>
				<c:when test="${groups.size() <= 0}">
					<div data-dojo-type="dijit/layout/AccordionPane" id="9999"
					data-dojo-props="title: 'No Content', href:'/security/show_resources/9999'"
					class="paneAccordion"></div>
				</c:when>
				<c:otherwise>
					<c:forEach items="${groups}" var="group">
						<div data-dojo-type="dijit/layout/AccordionPane" id="${group.gid}"
							data-dojo-props="title: '${group.name}', href:'/security/show_resources/${group.gid}'"
							class="paneAccordion"></div>
					</c:forEach>
				</c:otherwise>
			</c:choose>
			
		</div>
		<div data-dojo-type="dijit/layout/TabContainer" id="mainTabContainer"
			data-dojo-props="region: 'center', tabStrip:true">
			<div data-dojo-type="dijit/layout/ContentPane" id="tabWelcome"
				data-dojo-props="title: 'Welcome', href:'/frameui/tabWelcome.html'"></div>
		</div>
	</div>
</body>
</html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <title>Mas web console</title>
    <link rel="shortcut icon" href="images/favicon.png"/>

    <link rel="stylesheet" href="/frameui/frameui.css" type="text/css"/>

    <script type="text/javascript">
        dojoConfig = {
            async: true,
            locale: "en-gb"
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
	  "dojo.store.JsonRest": "dojo/store/JsonRest",
	  "dojo.json": "dojo/json",
      "dojo.array":"dojo/_base/array"
</script>
    <script type="text/javascript">
         function showSrvErrmsg(error){
            frameui.srvsideErrmsgDialog.set("href", "commons/show_error_message?errmsg=" + error["status"]);
            frameui.srvsideErrmsgDialog.show();
          }
    </script>
    <div data-dojo-type="dijit/Dialog" data-dojo-id="frameui.srvsideErrmsgDialog" data-dojo-props="title:'Error message from server',style: 'width: 300px'"></div>
<div data-dojo-type="dijit/layout/BorderContainer" id="mainContainer"
     data-dojo-props="gutters:true">

    <div data-dojo-type="dijit/layout/ContentPane" id="headerPane"
         data-dojo-props="splitter:false, region:'top', href:'/security/show_login'">
    </div>
    <div data-dojo-type="dijit/layout/BorderContainer" id="mainSplitter"
         data-dojo-props="liveSplitters: false, design: 'sidebar', region: 'center'">
        <div data-dojo-type="dijit/layout/AccordionContainer"
             id="leftAccordion" data-dojo-props="minSize: 20, region: 'leading'">
             <c:forEach items="${groups}" var="group">
             	<div data-dojo-type="dijit/layout/ContentPane" id="${group.gid}"
                 data-dojo-props="title: '${group.name}', href:'/security/show_resources/${group.gid}'"
                 class="paneAccordion"></div>
             </c:forEach>
        </div>
        <div data-dojo-type="dijit/layout/TabContainer" id="mainTabContainer"
             data-dojo-props="region: 'center'">
            <div data-dojo-type="dijit/layout/ContentPane" id="tabWelcome"
                 data-dojo-props="title: 'Welcome', href:'/security/maintaining/user/maintain_security_user'"></div>
        </div>
    </div>
</div>
</body>
</html>
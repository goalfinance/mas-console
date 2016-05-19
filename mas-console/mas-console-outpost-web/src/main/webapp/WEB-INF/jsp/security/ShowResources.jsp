<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>

<c:forEach items="${resources}" var="resource">
	<shiro:hasPermission name="${resource.rid}:view">
	<!-- button type="button" data-dojo-type="dijit/form/Button">
		${resource.name}
		<script type="dojo/on" data-dojo-event="click">
			frame.addTab("mainTabContainer", "${resource.rlocation}", "${resource.name}", true);
		</script>
	</button-->
	<div class="info-res">
		<a href="javascript:void(0)"
		onClick="addTab('mainTabContainer', '${resource.rlocation}', '${resource.name}', true)"
		class="securityresource">${resource.name}</a>
	</div>
	
	</shiro:hasPermission>
</c:forEach>


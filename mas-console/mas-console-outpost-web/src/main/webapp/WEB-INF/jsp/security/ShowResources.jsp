<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:forEach items="${resources}" var="resource">
	<shiro:hasPermission name="${resource.rid}:view">
	<button type="button" data-dojo-type="dijit/form/Button" class="commandButton">
		  <span>${resource.name}</span>
			<script type="dojo/on" data-dojo-event="click">
				frame.addTab("mainTabContainer", "editor.html", "Editor", true);
			</script>
	</button>
	</shiro:hasPermission>
</c:forEach>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>

<c:choose>
	<c:when test="${resources.size() <= 0}">
		<div class="info-res">
			No Content......
		</div>
	</c:when>
	<c:otherwise>
		<c:forEach items="${resources}" var="resource">
			<shiro:hasPermission name="${resource.rid}:view">
				<div class="info-res">
					<a href="javascript:void(0)"
					onClick="addTab('mainTabContainer', '${resource.rlocation}', '${resource.name}', true)"
					class="securityresource">${resource.name}</a>
				</div>
			
			</shiro:hasPermission>
		</c:forEach>
	</c:otherwise>
</c:choose>


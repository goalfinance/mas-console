<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div data-dojo-type="dijit/form/Form"
	data-dojo-id="security.maintaining.role.resSelection.SelectionForm"
	id="security.maintaining.role.resSelection.SelectionForm">
	<c:forEach items="${groups}" var="group" varStatus="status">
		<div data-dojo-type="dijit/TitlePane"
			id="security.maintaining.role.resSelection.tp${group.gid}"
			data-dojo-id="security.maintaining.role.resSelection.tp${group.gid}"
			data-dojo-props="title: '${group.name}'" style="width: 300px;">
			<table>
				<c:forEach items="${group.resources}" var="resource"
					varStatus="status">
					<tr>
						<td>
							<div data-dojo-type="dijit/form/CheckBox"
								data-dojo-props="name:'${resource.rid}',value:'${resource.rid}'"
								checked=${resource.checked}></div>
						</td>
						<td>${resource.name}</td>
					</tr>
				</c:forEach>
			</table>
		</div>

	</c:forEach>
</div>
<div class="dijitDialogPaneActionBar">
	<div class="left-side"></div>
	<div class="right-side">
		<button data-dojo-type="dijit/form/Button">
			add
			<script type="dojo/on" data-dojo-event="click">
				var formObj = dojo.formToObject("security.maintaining.role.resSelection.SelectionForm");
				security.maintaining.role.RolePermissionJsonStore.put(formObj,{id:"${roleSid}"}).then(function(result){
					security.maintaining.role.RolePermissionsGrid._refresh();
					security.maintaining.role.dialogResSelection.hide();
				}, showSrvErrmsg);
			</script>
		</button>
	</div>
</div>
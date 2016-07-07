<table data-dojo-type="dojox.grid.EnhancedGrid" id="security.maintaining.user.view.RoleListGrid"
	data-dojo-id="security.maintaining.user.view.RoleListGrid"
	data-dojo-props="store:security.maintaining.user.RoleStore, noDataMessage:'no role defined...',
					plugins:{
						indirectSelection: {
							headerSelector: true
						},			
						filter:{
							itemsName:'security roles',
							isServerSide: false
						}
					}"
	style="width:300px;height:200px">
	<thead>
		<tr>
			<th field="roleName" width="100%" datatype="string">Role Name</th>
		</tr>
	</thead>
</table>

<div class="dijitDialogPaneActionBar">
	<div class="left-side"></div>
	<div class="right-side">
		<button data-dojo-type="dijit/form/Button">
			OK
			<script type="dojo/on" data-dojo-event="click">
				var items = security.maintaining.user.view.RoleListGrid.selection.getSelected();
				var sel = frame.dom.byId('security.maintaining.user.view.RoleAssigned');
				dojo.empty("security.maintaining.user.view.RoleAssigned");				
				dojo.array.forEach(items, function(selectedItem){
					if (selectedItem != null){
						var sid = security.maintaining.user.view.RoleListGrid.store.getValue(selectedItem, "sid", null);
						var name = security.maintaining.user.view.RoleListGrid.store.getValue(selectedItem, "roleName", null);
						
						var c = dojo.doc.createElement('option');
						c.innerHTML = name;
						c.value = sid;
						sel.appendChild(c);
					}
				});
				
				security.maintaining.user.view.roleSelectionDialog.hide();
				security.maintaining.user.view.RoleListGrid._refresh();
				security.maintaining.user.view.RoleListGrid.selection.deselectAll();
			</script>
		</button>
	</div>
</div>
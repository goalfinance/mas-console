<div data-dojo-type="dojo/store/JsonRest"
	data-dojo-id="security.maintaining.role.ActiveRoleJsonStore"
	data-dojo-props="target:'/security/maintaining/role/maintain/active', idProperty:'sid'">
</div>

<div data-dojo-type="dojo/store/JsonRest"
	data-dojo-id="security.maintaining.role.RoleJsonStore"
	data-dojo-props="target:'/security/maintaining/role/maintain', idProperty:'sid'">
</div>

<div data-dojo-type="dojo/data/ObjectStore"
	data-dojo-id="security.maintaining.role.RoleStore"
	data-dojo-props="objectStore:security.maintaining.role.ActiveRoleJsonStore">
</div>

<div data-dojo-type="dojo/store/JsonRest"
	data-dojo-id="security.maintaining.role.RolePermissionJsonStore"
	data-dojo-props="target:'/security/maintaining/permission/role', idProperty:'sid'">
</div>

<div data-dojo-type="dojo/data/ObjectStore"
	data-dojo-id="security.maintaining.role.RolePermissionStore"
	data-dojo-props="objectStore:security.maintaining.role.RolePermissionJsonStore">
</div>

<div data-dojo-type="dijit/Dialog"
	data-dojo-id="security.maintaining.role.dialogAddForm"
	data-dojo-props="title:'Add Role', href:'/security/maintaining/role/show_add_form'">
</div>

<div data-dojo-type="dijit/Dialog"
	data-dojo-id="security.maintaining.role.dialogViewForm"
	data-dojo-props="title:'View Role'"></div>

<div data-dojo-type="dijit/Dialog"
	data-dojo-id="security.maintaining.role.dialogResSelection"
	data-dojo-props="title:'Select Resources'">
</div>

<div data-dojo-type="dijit/layout/BorderContainer"
	id="security.maintaining.role.BorderContainer"
	data-dojo-id="security.maintaining.role.BorderContainer">
	<div data-dojo-type="dijit/layout/ContentPane"
		id="security.maintaining.role.RoleMaintainingPane"
		data-dojo-id="security.maintaining.role.RoleMaintainingPane"
		data-dojo-props="region:'top'">
		<div data-dojo-type="dojox.grid.EnhancedGrid"
			id="security.maintaining.role.RoleListGrid"
			data-dojo-id="security.maintaining.role.RoleListGrid"
			data-dojo-props="store:security.maintaining.role.RoleStore, noDataMessage:'no role defined...',
					plugins:{		
						filter:{
							itemsName:'security roles',
							isServerSide: false
						}
					},
					structure:[
						{field:'roleName', name:'Role Name', width:'80%'},
						{field:'createTime', name:'Create Time', width:'20%'}
					]"
			style="width: 100%; height: 150px">
			<script type="dojo/on" data-dojo-event="rowClick">
				var selectedItems = security.maintaining.role.RoleListGrid.selection.getSelected();
				if (selectedItems.length){
					dojo.array.forEach(selectedItems, function(item){
						var sid = security.maintaining.role.RoleListGrid.store.getValue(item, "sid", null);
						security.maintaining.role.RolePermissionsGrid.setQuery("?roleSid=" + sid);
					});
				}
			</script>
		</div>
		<button data-dojo-type="dijit/form/Button" name="addRoleButton">
			Add Role
			<script type="dojo/on" data-dojo-event="click">
				security.maintaining.role.dialogAddForm.show();
														
			</script>
		</button>
		<button data-dojo-type="dijit/form/Button" name="removeRoleButton">
			Remove Role
			<script type="dojo/on" data-dojo-event="click">
				var selectedItems = security.maintaining.role.RoleListGrid.selection.getSelected();
				if (selectedItems.length){
					dojo.array.forEach(selectedItems, function(item){
						var sid = security.maintaining.role.RoleListGrid.store.getValue(item, "sid", null);
						if (sid == null) return;
						security.maintaining.role.RoleJsonStore.remove(sid).then(function(results){
							security.maintaining.role.RoleListGrid._refresh();
                        }, showSrvErrmsg);
                    });
				}			
			</script>
		</button>
		<button data-dojo-type="dijit/form/Button" name="EditRoleButton">
			Open Role
			<script type="dojo/on" data-dojo-event="click">
				try{
				var selectedItemsCnt = security.maintaining.role.RoleListGrid.selection.getSelectedCount();
				if (selectedItemsCnt != 1){
					alert("Please select one row once.");
					return;
				}

				var selectedItems = security.maintaining.role.RoleListGrid.selection.getSelected();
				dojo.array.forEach(selectedItems, function(selectedItem){
					var sid = security.maintaining.role.RoleListGrid.store.getValue(selectedItem, "sid", null);
					if (sid == null){
						return;
					}
				security.maintaining.role.dialogViewForm.set("href", "/security/maintaining/role/show_view_form/?sId=" + sid);
				security.maintaining.role.dialogViewForm.show();
					
				});
			}catch(e){
				alert(e);
			}
			</script>
		</button>
	</div>
	<div data-dojo-type="dijit/layout/ContentPane"
		id="security.maintaining.role.RolePermissionMaintainingPane"
		data-dojo-id="security.maintaining.role.RolePermissionMaintainingPane"
		data-dojo-props="region:'center'">
		<table data-dojo-type="dojox.grid.EnhancedGrid"
			id="security.maintaining.role.RolePermissionsGrid"
			data-dojo-id="security.maintaining.role.RolePermissionsGrid"
			data-dojo-props="store:security.maintaining.role.RolePermissionStore, noDataMessage:'no permissions assigned...',
					plugins:{	
						filter:{
							itemsName:'security roles',
							isServerSide: false
						}
					}"
			style="width: 100%; height: 90%">
			<thead>
				<tr>
					<th field="resourceName" width="40%">Resource Name</th>
					<th field="permittedAction" width="30%">Permitted Action</th>
					<th field="createTime" width="30%">Create Time</th>
				</tr>
			</thead>
		</table>
		<button data-dojo-type="dijit/form/Button" name="addPermissionButton">
			Add Permission
			<script type="dojo/on" data-dojo-event="click">
				var selectedItems = security.maintaining.role.RoleListGrid.selection.getSelected();
				if (selectedItems.length != 1){return;}
				dojo.array.forEach(selectedItems, function(selectedItem){
					var sid = security.maintaining.role.RoleListGrid.store.getValue(selectedItem, "sid", null);
					if (sid == null){
						return;
					}
				
					security.maintaining.role.dialogResSelection.set("href", "security/maintaining/resource/show_resource_selection_view?roleSid=" + sid)
					security.maintaining.role.dialogResSelection.show();
					
				});
			</script>
		</button>
		<button data-dojo-type="dijit/form/Button" name="removePermissionButton">
			Remove Permission
			<script type="dojo/on" data-dojo-event="click">
				var selectedItems = security.maintaining.role.RolePermissionsGrid.selection.getSelected();
				if (selectedItems.length){
					dojo.array.forEach(selectedItems, function(item){
						var sid = security.maintaining.role.RolePermissionsGrid.store.getValue(item, "sid", null);
						if (sid == null) return;
						security.maintaining.role.RolePermissionJsonStore.remove(sid).then(function(results){
							security.maintaining.role.RolePermissionsGrid._refresh();
                        }, showSrvErrmsg);
                    });
				}													
			</script>
		</button>
	</div>
</div>

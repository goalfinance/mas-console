<div data-dojo-type="dojo/store/JsonRest"
	data-dojo-id="security.maintaining.user.RoleJsonStore"
	data-dojo-props="target:'/security/maintaining/role/maintain/active', idProperty:'sid'">
</div>

<div data-dojo-type="dojo/data/ObjectStore"
	data-dojo-id="security.maintaining.user.RoleStore"
	data-dojo-props="objectStore:security.maintaining.user.RoleJsonStore">
</div>

<div data-dojo-type="dojo/store/JsonRest"
	data-dojo-id="security.maintaining.user.JsonRest"
	data-dojo-props="target:'/security/maintaining/user/maintain', idProperty:'sid'">
</div>
<div data-dojo-type="dojo/data/ObjectStore"
	data-dojo-id="security.maintaining.user.store"
	data-dojo-props="objectStore:security.maintaining.user.JsonRest">
</div>

<div data-dojo-type="dijit/Dialog"
	data-dojo-id="security.maintaining.user.dialogAddForm"
	id="security.maintaining.user.dialogAddForm"
	data-dojo-props="title:'Add security user',
					href:'/security/maintaining/user/show_add_form'">
</div>

<div data-dojo-type="dijit/Dialog"
	data-dojo-id="security.maintaining.user.dialogViewForm"
	id="security.maintaining.user.dialogViewForm"
	data-dojo-props="title:'View security user'"></div>

<div data-dojo-type="dijit/layout/BorderContainer"
	data-dojo-id="security.maintaining.user.BorderContainer"
	id="security.maintaining.user.BorderContrainer">
	<div data-dojo-type="dijit/layout/ContentPane"
		data-dojo-id="security.maintaining.user.QueryPane"
		id="security.maintaining.user.QueryPane"
		data-dojo-props="region:'top'">
		<div data-dojo-type="dijit/form/Form"
			data-dojo-id="security.maintaining.user.QueryForm"
			id="security.maintaining.user.QueryForm">
			<table id="security.maintaining.user.QueryFormTable">
				<tr>
					<td><label for="userId">User ID:</label></td>
					<td><input type="text" name="userId"
						data-dojo-type="dijit/form/TextBox" /></td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>
						<button data-dojo-type="dijit/form/Button" name="queryButton">
							Query
							<script type="dojo/on" data-dojo-event="click">
								var s = dojo.formToQuery("security.maintaining.user.QueryForm");
								s = "?" + s;
								security.maintaining.user.grid.setQuery(s);								
							</script>
						</button>
					</td>
				</tr>
			</table>
		</div>
	</div>


	<div data-dojo-type="dijit/layout/ContentPane"
		data-dojo-id="security.maintaining.user.QueryResultPane"
		id="security.maintaining.user.QueryResultPane"
		data-dojo-props="region:'center'">
		<table data-dojo-type="dojox.grid.EnhancedGrid"
			data-dojo-id="security.maintaining.user.grid"
			id="security.maintaining.user.grid"
			data-dojo-props="store:security.maintaining.user.store, noDataMessage:'No registered security user...',
				plugins:{
					pagination:true
				}">

			<thead>
				<tr>
					<th field="sid" width="5%">Sid</th>
					<th field="userId" width="10%">user id</th>
					<th field="fullName" width="20%">full name</th>
					<th field="nickName" width="20%">nick name</th>
					<th field="createTime" width="15%">create time</th>
					<th field="updateTime" width="15%">update time</th>
					<th field="pwdChangeTime" width="15%">password change time</th>
				</tr>
			</thead>
		</table>

	</div>

	<div data-dojo-type="dijit/layout/ContentPane"
		data-dojo-id="security.maintaining.user.ButtonPane"
		id="security.maintaining.user.ButtonPane"
		data-dojo-props="region:'bottom'">
		<div data-dojo-type="dijit/form/Button">
			New...
			<script type="dojo/on" data-dojo-event="click">
				frame.registry.byId("security.maintaining.user.dialogAddForm").show();
			</script>
		</div>

		<div data-dojo-type="dijit/form/Button">
			Delete
			<script type="dojo/on" data-dojo-event="click">
				var items = security.maintaining.user.grid.selection.getSelected();
				if (items.length){
					dojo.array.forEach(items, function(selectedItem){
						var sid = security.maintaining.user.grid.store.getValue(selectedItem, "sid", null);
						if (sid == null) return;
						security.maintaining.user.JsonRest.remove(sid).then(function(result){
							security.maintaining.user.grid._refresh();
						}, showSrvErrmsg);
					});
				}
			</script>
		</div>

		<div data-dojo-type="dijit/form/Button">
			Open
			<script type="dojo/on" data-dojo-event="click">
			try{
				var selectedItemsCnt = security.maintaining.user.grid.selection.getSelectedCount();
				if (selectedItemsCnt != 1){
					alert("Please select one row once.");
					return;
				}

				var selectedItems = security.maintaining.user.grid.selection.getSelected();
				dojo.array.forEach(selectedItems, function(selectedItem){
					var sid = security.maintaining.user.grid.store.getValue(selectedItem, "sid", null);
					if (sid == null){
						return;
					}
					security.maintaining.user.dialogViewForm.set("href", "/security/maintaining/user/show_view_form?sId=" + sid);
					security.maintaining.user.dialogViewForm.show();
					
				});
			}catch(e){
				alert(e);
			}
			</script>
		</div>
	</div>

</div>
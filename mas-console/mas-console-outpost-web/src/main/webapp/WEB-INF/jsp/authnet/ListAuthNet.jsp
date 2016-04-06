<div data-dojo-type="dijit/Dialog" data-dojo-id="authnet.dialogAddForm" id="authnet.dialogAddForm" data-dojo-props="title:'Add new authorized network',href:'/authnet/show_add_form'"></div>
<div data-dojo-type="dijit/Dialog" data-dojo-id="authnet.dialogViewForm" id="authnet.dialogViewForm" data-dojo-props="title: 'View&Update authorized network'"></div>
<div data-dojo-type="dijit/layout/BorderContainer" id="containerAuthnet"
	data-dojo-props="liveSplitters: true, design: 'sidebar'">

	<div data-dojo-type="dojo/store/JsonRest"
		data-dojo-id="authnet.jsonrest"
		data-dojo-props="target:'/authnet/maintains', idProperty:'sid'">
	</div>

	<div data-dojo-type="dojo/data/ObjectStore"
		data-dojo-id="authnet.store"
		data-dojo-props="objectStore: authnet.jsonrest"></div>
	<div data-dojo-type="dijit/layout/ContentPane" id="authnetQueryPane"
		data-dojo-props="region:'top'">
		<form data-dojo-type="dijit/form/Form" data-dojo-id="authnet.queryForm" id="authnet.queryForm">
			<table id="authnetQueryForm">
				<tr>
					<td><label for="authNetId">Auth Net ID:</label></td>
					<td><input type="text" name="authNetId"
						data-dojo-type="dijit/form/TextBox" /></td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td><label for="name">Auth Net Name:</label></td>
					<td><input type="text" name="name"
						data-dojo-type="dijit/form/TextBox" /></td>
				</tr>
				<tr>
					<td><label for="authNetType">Auth Net Type:</label></td>
					<td>
						<div data-dojo-type="dijit/form/Select" name="authNetType" data-dojo-props="value:'0'">
							<span data-dojo-value="0">Auto Channel</span>
							<span data-dojo-value="1">Agent Channel</span>
						</div>
					</td>
				</tr>
				<tr>
					<td><br />
						<br />
						<button data-dojo-type="dijit/form/Button" name="queryButton">
							Query
							<script type="dojo/on" data-dojo-event="click">
								var s = dojo.formToQuery("authnet.queryForm");
								s = "?" + s;
								authnet.grid.setQuery(s);								
							</script>
						</button>
					</td>
				</tr>
			</table>
		</form>
	</div>

	<div data-dojo-type="dijit/layout/ContentPane"
		id="authnetQueryResultPane" data-dojo-props="region:'center'">
		<table data-dojo-type="dojox.grid.EnhancedGrid" data-dojo-id="authnet.grid"
			data-dojo-props="store: authnet.store, noDataMessage: 'No authorized networks...',
			plugins:{pagination:true}">
			<thead>
				<tr>
					<th field="sid" width="50px">ID</th>
					<th field="authNetId" width="100px">Auth Network ID</th>
					<th field="name" width="200px">Name</th>
				</tr>
			</thead>
		</table>
	</div>
	<div data-dojo-type="dijit/layout/ContentPane" id="authnetButtonPane"
		data-dojo-props="region: 'bottom', splitter: false">
		<button data-dojo-type="dijit/form/Button">New...
			<script type="dojo/on" data-dojo-event="click">
				frame.registry.byId("authnet.dialogAddForm").show();
			</script>
		</button>
		<button data-dojo-type="dijit/form/Button">Delete
			<script type="dojo/on" data-dojo-event="click">
				//authnet.grid.removeSelectedRows();
				var items = authnet.grid.selection.getSelected();
				if (items.length){
					dojo.array.forEach(items, function(selectedItem){
						if (selectedItem != null){
							var id = authnet.grid.store.getValue(selectedItem, "sid", null);
							if (id == null) return;
							authnet.jsonrest.remove(id).then(function(results){
								authnet.grid._refresh();
	                        },showSrvErrmsg);

						}
					});
				}
			</script>
		</button>
		<button data-dojo-type="dijit/form/Button">Open
			<script type="dojo/on" data-dojo-event="click">
				try{
					var selectedItemsCnt = authnet.grid.selection.getSelectedCount();
					if (selectedItemsCnt != 1){
						alert("Please select one row once.");
						return;
					}
					var selectedItems = authnet.grid.selection.getSelected();
					dojo.forEach(selectedItems, function(selectedItem){
						var id = authnet.grid.store.getValue(selectedItem, "sid", null);
						if (id == null) return;
						authnet.dialogViewForm.set("href","/authnet/show_view_form?sId=" + id);
						authnet.dialogViewForm.show();
					});
				}catch(e){
					alert(e);
				}

		    </script>
		</button>
	</div>
</div>

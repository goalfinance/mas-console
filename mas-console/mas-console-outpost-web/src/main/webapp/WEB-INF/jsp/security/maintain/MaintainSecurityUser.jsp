<div data-dojo-type="dijit/layout/BorderContainer" data-dojo-id="security.maintaining.user.BorderContainer"
		id="security.maintaining.user.BorderContrainer">
	<div data-dojo-type="dojo/store/JsonRest" data-dojo-id="security.maintaining.user.jsonrest"
		data-dojo-props="target:'/security/maintaining/user/maintain', idProperty:'sid'">
	</div>
	<div data-dojo-type="dojo/data/ObjectStore" data-dojo-id="security.maintaining.user.store"
		data-dojo-props="objectStore:security.maintaining.user.jsonrest">
	</div>
	
	<div data-dojo-type="dijit/layout/ContentPane" data-dojo-id="security.maintaining.user.QueryPane"
		id="security.maintaining.user.QueryPane"
		data-dojo-props="region:'top'">
		<div data-dojo-type="dijit/form/Form" data-dojo-id="security.maintaining.user.QueryForm"
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
	
	<div data-dojo-type="dijit/layout/ContentPane" data-dojo-id="security.maintaining.user.QueryResultPane"
		id="security.maintaining.user.QueryResultPane"
		data-dojo-props="region:'center'">
		<table data-dojo-type="dojox.grid.EnhancedGrid" data-dojo-id="security.maintaining.user.grid"
				id="security.maintaining.user.grid"
				data-dojo-props="store:security.maintaining.user.store, noDataMessage:'No registered security user...',
				plugins:{pagination:true}">
			<thead>
				<tr>
					<th field="sid" width="50px">Sid</th>
					<th field="userId">user id</th>
					<th field="fullName">full name</th>
					<th field="nickName">nick name</th>
					<th field="createTime" width="130px">create time</th>
				</tr>
			</thead>
		</table>
		
	</div>
	
	<div data-dojo-type="dijit/layout/ContentPane" data-dojo-id="security.maintaining.user.ButtonPane"
		id="security.maintaining.user.ButtonPane"
		data-dojo-props="region:'bottom'">
		<div data-dojo-type="dijit/form/Button">New...
		</div>
		
		<div data-dojo-type="dijit/form/Button">Delete
		</div>
		
		<div data-dojo-type="dijit/form/Button">Open
		</div>
	</div>
	
</div>
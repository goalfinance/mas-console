<script type="text/javascript">
	function formReset() {
		security.maintaining.user.addForm.reset();
		dojo.empty("security.maintaining.user.view.RoleAssigned");
	}
</script>

<div data-dojo-type="dijit/Dialog"
	data-dojo-id="security.maintaining.user.view.roleSelectionDialog"
	id="security.maintaining.user.view.roleSelectionDialog"
	data-dojo-props="title:'Select security roles', 
	href:'security/maintaining/role/show_role_selection_forview'">
</div>

<div data-dojo-type="dijit/form/Form"
	data-dojo-id="security.maintaining.user.viewForm"
	id="security.maintaining.user.viewForm">
	<table id="security.maintaining.user.tableViewForm">
		<tr>
			<td><label for="userId">User ID:</label></td>
			<td><input type="text" name="userId"
				data-dojo-type="dijit/form/TextBox" readOnly="true"
				data-dojo-id="security.maintaining.user.viewform.userId" /></td>
		</tr>

		<tr>
			<td><label for="fullName">Full Name:</label></td>
			<td><input type="text" name="fullName"
				data-dojo-id="security.maintaining.user.viewform.fullName"
				data-dojo-type="dijit/form/ValidationTextBox" required="false" /></td>
		</tr>

		<tr>
			<td><label for="nickName">Nick Name:</label></td>
			<td><input type="text" name="nickName"
				data-dojo-id="security.maintaining.user.viewform.nickName"
				data-dojo-type="dijit/form/ValidationTextBox" required="false" /></td>
		</tr>

		<tr>
			<td><label for="rolesAssigned">Roles Assigned:</label></td>
			<td><input type="hidden" name="roles" id="roles"
				data-dojo-type="dijit/form/TextBox"
				data-dojo-id="security.maintaining.user.viewform.roles" /> <select
				data-dojo-type="dijit/form/MultiSelect"
				id="security.maintaining.user.view.RoleAssigned"
				data-dojo-id="security.maintaining.user.view.RoleAssigned"
				name="rolesAssigned" style="width: 167px">
			</select></td>
			<td>
				<button data-dojo-type="dijit/form/Button">
					A
					<script type="dojo/on" data-dojo-event="click">
					security.maintaining.user.view.roleSelectionDialog.show();
				</script>
				</button> <br />
				<button data-dojo-type="dijit/form/Button">
					D
					<script type="dojo/on" data-dojo-event="click">
						var sel = frame.dom.byId('security.maintaining.user.view.RoleAssigned');
						security.maintaining.user.view.RoleAssigned.getSelected().forEach(function(n){
							sel.remove(n);
						});
					</script>
				</button>
			</td>

		</tr>
	</table>
	<div class="dijitDialogPaneActionBar">
		<div class="left-side">
			<button data-dojo-type="dijit/form/Button">
				Change Password
				<script type="dojo/on" data-dojo-event="click">
					frameui.changePasswordDialog.set("href", "security/maintaining/user/change_password?sId=${sId}");
					frameui.changePasswordDialog.show();	
				</script>
			</button>
		</div>
		<div class="right-side">
			<button data-dojo-type="dijit/form/Button">
				save
				<script type="dojo/on" data-dojo-event="click">
					var sel = frame.dom.byId('security.maintaining.user.view.RoleAssigned');
					
					security.maintaining.user.viewform.roles.set("value", multiSelectOptions2JsonString(sel));
					if (security.maintaining.user.viewForm.validate() == false){return;}
					var formObj = dojo.formToObject("security.maintaining.user.viewForm");
					security.maintaining.user.JsonRest.put(formObj, {id:"${sId}"}).then(function(results){
						security.maintaining.user.grid._refresh();
						security.maintaining.user.viewForm.reset();
						dojo.empty("security.maintaining.user.view.RoleAssigned");
						security.maintaining.user.dialogViewForm.hide();
					}, showSrvErrmsg);
					
				</script>
			</button>
			<!--button data-dojo-type="dijit/form/Button">
				reset
				<script type="dojo/on" data-dojo-event="click">
					security.maintaining.user.viewForm.reset();
					dojo.empty("security.maintaining.user.view.RoleAssigned");
				</script>
			</button-->
		</div>
	</div>
	<script type="dojo/on" data-dojo-event="show">
		security.maintaining.user.JsonRest.get("${sId}").then(function(result){
			var sel = frame.dom.byId('security.maintaining.user.view.RoleAssigned');
			security.maintaining.user.viewform.userId.set("value", result.userId);
			security.maintaining.user.viewform.nickName.set("value", result.nickName);
			security.maintaining.user.viewform.fullName.set("value", result.fullName);
			security.maintaining.user.viewform.roles.set("value", result.roles);
			jsonString2MultiSelectOptions(result.roles, sel);
		});
		
	</script>
</div>
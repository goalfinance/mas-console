<script type="text/javascript">
	function formReset() {
		security.maintaining.user.addForm.reset();
		dojo.empty("security.maintaining.user.RoleAssigned");
	}
</script>

<div data-dojo-type="dijit/Dialog"
	data-dojo-id="security.maintaining.user.roleSelectionDialog"
	id="security.maintaining.user.roleSelectionDialog"
	data-dojo-props="title:'Select security roles', 
	href:'security/maintaining/role/show_role_selection'">
</div>

<div data-dojo-type="dijit/form/Form"
	data-dojo-id="security.maintaining.user.addForm"
	id="security.maintaining.user.addForm">
	<table id="security.maintaining.user.tableAddForm">
		<tr>
			<td><label for="userId">User ID:</label></td>
			<td><input type="text" name="userId"
				data-dojo-type="dijit/form/ValidationTextBox" required="true"
				promptMessage="Please enter the unique user id for login the sysytem." /></td>
		</tr>

		<tr>
			<td><label for="fullName">Full Name:</label></td>
			<td><input type="text" name="fullName"
				data-dojo-type="dijit/form/ValidationTextBox" required="false" /></td>
		</tr>

		<tr>
			<td><label for="nickName">Nick Name:</label></td>
			<td><input type="text" name="nickName"
				data-dojo-type="dijit/form/ValidationTextBox" required="false" /></td>
		</tr>

		<tr>
			<td><label for="plainPasswd">Password:</label></td>
			<td><input type="password" name="plainPasswd" id="password"
				data-dojo-type="dijit/form/ValidationTextBox" required="true"
				trim="true" invalidMessage="Please enter password" /></td>
		</tr>

		<tr>
			<td><label for="confirmPlainPwd">Confirm Password:</label></td>
			<td><input type="password" name="confirmPlainPwd"
				data-dojo-type="dijit/form/ValidationTextBox" required="true"
				trim="true"
				validator="return this.getValue()==dijit.byId('password').getValue()"
				invalidMessage="It isn't equal to the password entered above" /></td>
		</tr>

		<tr>
			<td><label for="rolesAssigned">Roles Assigned:</label></td>
			<td><input type="hidden" name="roles" id="roles" /> <select
				data-dojo-type="dijit/form/MultiSelect"
				id="security.maintaining.user.RoleAssigned"
				data-dojo-id="security.maintaining.user.RoleAssigned"
				name="rolesAssigned" style="width: 167px">
			</select></td>
			<td>
				<button data-dojo-type="dijit/form/Button">
					A
					<script type="dojo/on" data-dojo-event="click">
					security.maintaining.user.roleSelectionDialog.show();
				</script>
				</button> <br />
				<button data-dojo-type="dijit/form/Button">
					D
					<script type="dojo/on" data-dojo-event="click">
						var sel = frame.dom.byId('security.maintaining.user.RoleAssigned');
						security.maintaining.user.RoleAssigned.getSelected().forEach(function(n){
							sel.remove(n);
						});
					</script>
				</button>
			</td>

		</tr>
	</table>
	<div class="dijitDialogPaneActionBar">
		<div class="left-side"></div>
		<div class="right-side">
			<button data-dojo-type="dijit/form/Button">
				add
				<script type="dojo/on" data-dojo-event="click">
					var sel = frame.dom.byId('security.maintaining.user.RoleAssigned');
					var roles = frame.dom.byId('roles');
					roles.value = multiSelectOptions2JsonString(sel);
					if (security.maintaining.user.addForm.validate() == false){return;}
					var formObj = dojo.formToObject("security.maintaining.user.addForm");
					security.maintaining.user.JsonRest.add(formObj).then(function(results){
						security.maintaining.user.grid._refresh();
					}, showSrvErrmsg);
					security.maintaining.user.addForm.reset();
					dojo.empty("security.maintaining.user.RoleAssigned");
					security.maintaining.user.dialogAddForm.hide();
				</script>
			</button>
			<button data-dojo-type="dijit/form/Button">
				reset
				<script type="dojo/on" data-dojo-event="click">
					security.maintaining.user.addForm.reset();
					dojo.empty("security.maintaining.user.RoleAssigned");
				</script>
			</button>
		</div>
	</div>
</div>
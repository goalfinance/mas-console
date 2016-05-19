<div data-dojo-type="dijit/form/Form" id="security.maintaining.role.addForm"
	data-dojo-id="security.maintaining.role.addForm">
	<table>
		<tr>
			<td><label for="roleName">Role Name</label></td>
			<td>
				<input type="text" name="roleName" data-dojo-type="dijit/form/ValidationTextBox"
						required="true" promptMessage="Please enter the role's name."/>
			</td>
		</tr>
	</table>
	
	<div class="dijitDialogPaneActionBar">
		<div class="left-side">
		</div>
		
		<div class="right-side">
			<button data-dojo-type="dijit/form/Button">add
				<script type="dojo/on" data-dojo-event="click">
					if (security.maintaining.role.addForm.validate() == false) {return;}
					var formObj = dojo.formToObject("security.maintaining.role.addForm");
					security.maintaining.role.RoleJsonStore.add(formObj).then(function(results){
						security.maintaining.role.RoleListGrid._refresh();
					}, showSrvErrmsg);
					security.maintaining.role.addForm.reset();
					security.maintaining.role.dialogAddForm.hide();
				</script>
			</button>
		</div>
	</div>
</div>
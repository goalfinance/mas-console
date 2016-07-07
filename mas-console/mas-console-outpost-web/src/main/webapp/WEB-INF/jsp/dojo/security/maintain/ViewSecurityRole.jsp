<div data-dojo-type="dijit/form/Form" id="security.maintaining.role.viewForm"
	data-dojo-id="security.maintaining.role.viewForm">
	<table>
		<tr>
			<td><label for="roleName">Role Name</label></td>
			<td>
				<input type="text" name="roleName" 
						data-dojo-id="security.maintaining.role.viewform.roleName"
						data-dojo-type="dijit/form/ValidationTextBox"
						required="true" 
						promptMessage="Please enter the role's name."/>
			</td>
		</tr>
	</table>
	
	<script type="dojo/on" data-dojo-event="show">
		security.maintaining.role.RoleJsonStore.get("${sId}").then(function(result){
			security.maintaining.role.viewform.roleName.set("value", result.roleName);
		});
    </script>
	
	<div class="dijitDialogPaneActionBar">
		<div class="left-side">
		</div>
		
		<div class="right-side">
			<button data-dojo-type="dijit/form/Button">save
				<script type="dojo/on" data-dojo-event="click">
					if (security.maintaining.role.viewForm.validate() == false) {return;}
					var formObj = dojo.formToObject("security.maintaining.role.viewForm");
					security.maintaining.role.RoleJsonStore.put(formObj, {id:"${sId}"}).then(function(results){
						security.maintaining.role.RoleListGrid._refresh();
						security.maintaining.role.viewForm.reset();
						security.maintaining.role.dialogViewForm.hide();
					}, showSrvErrmsg);
				</script>
			</button>
		</div>
	</div>
</div>
<div data-dojo-type="dijit/form/Form" data-dojo-id="security.maintaining.user.addForm"
	id="security.maintaining.user.addForm">
	<div class="instructionBox">Please input the information of the new security user.</div>
	<table id="security.maintaining.user.tableAddForm">
		<tr>
			<td><label for="userId">User ID:</label></td>
			<td><input type="text" name="userId" data-dojo-type="dijit/form/ValidationTextBox"
						required="true"
						promptMessage="Please enter the unique user id for login the sysytem."/></td>
		</tr>
		
		<tr>
			<td><label for="fullName">Full Name:</label></td>
			<td><input type="text" name="fullName" data-dojo-type="dijit/form/ValidationTextBox"
						required="false"/></td>
		</tr>
		
		<tr>
			<td><label for="nickName">Nick Name:</label></td>
			<td><input type="text" name="nickName" data-dojo-type="dijit/form/ValidationTextBox"
						required="false"/></td>
		</tr>
		
		<tr>
			<td><label for="passwd">Password:</label></td>
			<td><input type="password" name="passwd" data-dojo-type="dijit/form/ValidationTextBox"
						required="false"/></td>
		</tr>
		
	</table>
	<div class="dijitDialogPaneActionBar">
		<div class="left-side">
		</div>
		<div class="right-side">
			<button data-dojo-type="dijit/form/Button">add
				<script type="dojo/on" data-dojo-event="click">
					if (security.maintaining.user.addForm.validate() == false){return;}
					var formObj = dojo.formToObject("security.maintaining.user.addForm");
					security.maintaining.user.JsonRest.add(formObj).then(function(results){
						security.maintaining.user.grid._refresh();
					}, showSrvErrmsg);
					security.maintaining.user.dialogAddForm.hide();
				</script>
			</button>
		</div>
	</div>
</div>
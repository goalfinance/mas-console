<div data-dojo-type="dijit/form/Form"
	data-dojo-id="security.maintaining.user.viewProfileForm"
	id="security.maintaining.user.viewProfileForm">
	<table id="security.maintaining.user.tableViewProfileForm">
		<tr>
			<td><label for="userId">User ID:</label></td>
			<td><input type="text" name="userId"
				data-dojo-type="dijit/form/TextBox" readOnly="true"
				data-dojo-id="security.maintaining.user.viewprofileform.userId" /></td>
		</tr>

		<tr>
			<td><label for="fullName">Full Name:</label></td>
			<td><input type="text" name="fullName"
				data-dojo-id="security.maintaining.user.viewprofileform.fullName"
				data-dojo-type="dijit/form/ValidationTextBox" required="false" /></td>
		</tr>

		<tr>
			<td><label for="nickName">Nick Name:</label></td>
			<td><input type="text" name="nickName"
				data-dojo-id="security.maintaining.user.viewprofileform.nickName"
				data-dojo-type="dijit/form/ValidationTextBox" required="false" /></td>
		</tr>
	</table>
	<div class="dijitDialogPaneActionBar">
		<div class="left-side">
			<button data-dojo-type="dijit/form/Button">
				Change Password
				<script type="dojo/on" data-dojo-event="click">
					frameui.changePasswordDialog.set("href", "dojo/security/maintaining/user/change_password?sId=${sId}");
					frameui.changePasswordDialog.show();	
				</script>
			</button>
		</div>
		<div class="right-side">
			<button data-dojo-type="dijit/form/Button">
				save
				<script type="dojo/on" data-dojo-event="click">
					if (security.maintaining.user.viewProfileForm.validate() == false){return;}
					var formObj = dojo.formToObject("security.maintaining.user.viewProfileForm");
					frame.user.JsonRest.put(formObj, {id:"${sId}"}).then(function(results){
						//security.maintaining.user.viewProfileForm.reset();
						frame.dialogUsersProfile.hide();
					}, showSrvErrmsg);
				</script>
			</button>
		</div>
	</div>
	<script type="dojo/on" data-dojo-event="show">
		frame.user.JsonRest.get("${sId}").then(function(result){
			security.maintaining.user.viewprofileform.userId.set("value", result.userId);
			security.maintaining.user.viewprofileform.nickName.set("value", result.nickName);
			security.maintaining.user.viewprofileform.fullName.set("value", result.fullName);
		});
		
	</script>
</div>
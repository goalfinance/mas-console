<div data-dojo-type="dijit/form/Form" id="security.maintaining.user.changePasswordForm"
	data-dojo-id="security.maintaining.user.changePasswordForm">
	<table data-dojo-id="security.maintaining.user.changePasswordFormTable" 
			id="security.maintaining.user.changePasswordFormTable">
		<tr>
			<td><label for="oldPassword">Old Password:</label></td>
			<td>
				<input type="password" name="oldPassword" data-dojo-type="dijit/form/ValidationTextBox" reqiured="true"
							data-dojo-id="security.maintaining.user.changepassword.oldPassword"
							id="security.maintainng.user.changepassword.oldPassword"
							trim="true"
							promptMessage="Enter the old password."/>
			</td>
		</tr>
		
		<tr>
			<td><label for="newPassword">New Password:</label></td>
			<td>
				<input type="password" name="newPassword" data-dojo-type="dijit/form/ValidationTextBox" required="true"
						data-dojo-id="security.maintaining.user.changepassword.newPassword"
						trim="true"
						id="security.maintaining.user.changepassword.newPassword"
						promptMessage="Enter the new password."/>
			</td>
		</tr>
		
		<tr>
			<td><label for="confirmNewPassword">Confirm the new Password:</label></td>
			<td>
				<input type="password" name="confirmNewPassword" data-dojo-type="dijit/form/ValidationTextBox" required="true"
						data-dojo-id="security.maintaining.user.changepassword.confirmNewPassword"
						id="security.maintaining.user.changepassword.confirmNewPassword"
						trim="true"
						validator="return this.getValue()==dijit.byId('security.maintaining.user.changepassword.newPassword').getValue()"
						invalidMessage="It isn't equal to the password entered above" />
			</td>
		</tr>
	</table>
</div>

<div data-dojo-type="dojo/store/JsonRest" data-dojo-id="security.maintaining.user.changepassword.JsonRest"
	data-dojo-props="target:'/security/maintaining/user/maintain/changepassword/${sId}'"/>


<div class="dijitDialogPaneActionBar">
	<div class="left-side">
	</div>
	<div class="right-side">
		<button data-dojo-type="dijit/form/Button">
			Commit
			<script type="dojo/on" data-dojo-event="click">
				if (security.maintaining.user.changePasswordForm.validate() == false){
					return;
				}
				var formObj = dojo.formToObject("security.maintaining.user.changePasswordForm");
				security.maintaining.user.changepassword.JsonRest.put(formObj).then(function(results){
					frameui.changePasswordDialog.hide();
					frameui.changePasswordDialog.reset();
				}, showSrvErrmsg);				
			</script>
		</button>
	</div>
</div>
<style type="text/css">
#tableLogin {
	margin-left: auto;
	margin-right: auto;
}
</style>

<div data-dojo-type="dojo/store/JsonRest"
	data-dojo-id="frame.login.jsonrest"
	data-dojo-props="target:'dojo/security/login'">
</div>

<div data-dojo-type="dijit/form/Form" data-dojo-id="frame.loginForm" id="frame.loginForm">
	<div class="instructionBox">Please provide you username and
		password to login.</div>
	<table id="tableLogin">
		<tr>
			<td><label for="username">Username:</label></td>
			<td><input type="text" name="username"
				data-dojo-type="dijit/form/ValidationTextBox"
				required="true"
				promptMessage="Enter username for authorization."/></td>
		</tr>
		<tr>
			<td><label for="password">Password:</label></td>
			<td><input type="password" name="password"
				data-dojo-type="dijit/form/ValidationTextBox"
				required="true"
				promptMessage="Enter the password."/></td>
		</tr>
	</table>
	<div class="dijitDialogPaneActionBar">
		<button type="reset" data-dojo-type="dijit/form/Button">Reset</button>
		<button data-dojo-type="dijit/form/Button">Login
			<script type="dojo/on" data-dojo-event="click">
				if (frame.loginForm.validate() == false){return;}
				var formObj = dojo.formToObject("frame.loginForm");
				frame.login.jsonrest.add(formObj).then(function(results){
					window.location="index.jsp";
					frame.dialogLogin.hide();
				}, showSrvErrmsg);
				
			</script>
		</button>
	</div>
</div>
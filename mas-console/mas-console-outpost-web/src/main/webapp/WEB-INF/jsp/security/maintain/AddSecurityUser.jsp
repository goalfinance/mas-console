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
			<td><input type="text" name="passwd" data-dojo-type="dijit/form/ValidationTextBox"
						required="false"/></td>
		</tr>
		
	</table>
</div>
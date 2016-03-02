<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<style type="text/css">
#tableLogin {
	margin-left: auto;
	margin-right: auto;
}
</style>
<form:form commandName="login" action="security/login" method="post">
	<div class="instructionBox">Please provide you username and
		password to login.</div>
	<table id="tableLogin">
		<tr>
			<td><label for="username">Username:</label></td>
			<td><input type="text" name="username"
				data-dojo-type="dijit/form/TextBox" /></td>
		</tr>
		<tr>
			<td><label for="password">Password:</label></td>
			<td><input type="password" name="password"
				data-dojo-type="dijit/form/TextBox" /></td>
		</tr>
	</table>
	<div class="dijitDialogPaneActionBar">
		<button type="reset" data-dojo-type="dijit/form/Button">Reset</button>
		<button type="submit" data-dojo-type="dijit/form/Button">Login</button>
	</div>
</form:form>
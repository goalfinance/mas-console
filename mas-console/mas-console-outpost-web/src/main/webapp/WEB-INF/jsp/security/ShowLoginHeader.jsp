<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div data-dojo-type="dijit/Dialog" data-dojo-id="frame.dialogLogin" id="frame.dialogLogin" data-dojo-props="title: 'Login...', href: '/security/dialog_login'"></div>

<div class="left-side">
</div>
<div class="right-side">
	<shiro:user>
		<div data-dojo-type="dijit/form/DropDownButton" data-dojo-props="iconClass:'dijitIconEdit'">
			<span>Hello, ${nickname}</span>
			<div data-dojo-type="dijit/Menu" id="editMenu1" style="display: none;">
				<div data-dojo-type="dijit/MenuItem" data-dojo-props="
								iconClass:'dijitIconCut',">Log out
				<script type="dojo/on" data-dojo-event="click">
					frame.request.get("/logout").then(function(text){window.location="index.jsp"});
				</script>
				</div>
	
			</div>
		</div>
	</shiro:user>
	<shiro:guest>
		<button data-dojo-type="dijit/form/Button" id="loginButton">Login Form
			<script type="dojo/on" data-dojo-event="click">
				frame.dialogLogin.show();
			</script>
		</button>
	</shiro:guest>
</div>


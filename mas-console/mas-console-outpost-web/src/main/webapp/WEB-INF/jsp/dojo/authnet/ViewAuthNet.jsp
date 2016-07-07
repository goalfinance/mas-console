<div data-dojo-type="dijit/form/Form" data-dojo-id="authnet.viewForm"
	id="authnet.viewForm">
	<div class="instructionBox">Please update the information of the
		new authorized network.</div>
	<table id="tableAddForm">
		<tr>
			<td><label for="authNetId">Authorized Network ID:</label></td>
			<td><input data-dojo-id="authnet.view.authNetId" type="text"
				name="authNetId" data-dojo-type="dijit/form/ValidationTextBox"
				required="true" regExp="^[0-9]{8}$"
				promptMessage="Enter unique authorized network id, 8 digits."
				invalidMessage="8 digits, example:'88888888'" /></td>
		</tr>
		<tr>
			<td><label for="name">Name:</label></td>
			<td><input type="text" name="name"
				data-dojo-id="authnet.view.name"
				data-dojo-type="dijit/form/ValidationTextBox" required="true"
				promptMessage="Enter the name of the authorized network, the max length is 255 characters."
				invalidMessage="Invalid name" /></td>
		</tr>

		<tr>
			<td><label for="authNetType">Authorized Network Type:</label></td>
			<td>
				<div name="authNetType" data-dojo-id="authnet.view.authNetType"
					data-dojo-type="dijit/form/Select" data-dojo-props="value:'0'">
					<span data-dojo-value="0">Auto Channel</span> <span
						data-dojo-value="1">Agent Channel</span>
				</div>
			</td>
		</tr>
		<tr>
			<td><label for="acquirerId">Acquier ID:</label></td>
			<td><input type="text" name="acquirerId"
				data-dojo-id="authnet.view.acquirerId"
				data-dojo-type="dijit/form/ValidationTextBox" maxLength="20" /></td>
		</tr>

		<tr>
			<td><label for="issuerId">Issuer ID:</label></td>
			<td><input type="text" name="issuerId"
				data-dojo-id="authnet.view.issuerId"
				data-dojo-type="dijit/form/ValidationTextBox" maxLength="20" /></td>
		</tr>
		<tr>
			<td>
				<div data-dojo-type="dijit/form/CheckBox"
					data-dojo-id="authnet.view.openFlag" name="openFlag" checked=false
					value="true"></div> <label for="openFlag">Open Flag</label>
			</td>
		</tr>
		<tr>
			<td><label for="networkStatus">Status:</label></td>
			<td>
				<div name="networkStatus" data-dojo-id="authnet.view.networkStatus"
					data-dojo-type="dijit/form/Select" data-dojo-props="value:'O'">
					<span data-dojo-value="O">Normal</span> <span data-dojo-value="E">Error</span>
					<span data-dojo-value="C">Close</span> <span data-dojo-value="M">Mornitoring</span>
				</div>
			</td>
		</tr>
	</table>
	<div class="dijitDialogPaneActionBar">
		<div class="left-side"></div>

		<div class="right-side">
			<button data-dojo-type="dijit/form/Button">
				update
				<script type="dojo/on" data-dojo-event="click">
				var formObj = dojo.formToObject("authnet.viewForm");
				authnet.jsonrest.put(formObj, {id:"${sId}"}).then(function(item){
                    authnet.grid._refresh();
                }, showSrvErrmsg);
			
				authnet.dialogViewForm.hide();
				authnet.viewForm.reset();
		    </script>
			</button>
		</div>
	</div>
	<script type="dojo/on" data-dojo-event="show">
		authnet.jsonrest.get("${sId}").then(function(result){
			authnet.view.authNetId.set("value", result.authNetId);
			authnet.view.name.set("value", result.name);
			authnet.view.authNetType.set("value", result.authNetType);
			authnet.view.acquirerId.set("value", result.acquirerId);
			authnet.view.issuerId.set("value", result.issuerId);
			authnet.view.openFlag.set("value", result.openFlag);
			authnet.view.networkStatus.set("value", result.networkStatus);
        }, showSrvErrmsg);
	</script>
</div>
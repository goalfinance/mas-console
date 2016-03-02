<div data-dojo-type="dijit/form/Form" data-dojo-id="authnet.addForm" id="authnet.addForm">
	<div class="instructionBox">Please input the information of the new authorized network.</div>
	<table id="tableAddForm">
		<tr>
			<td><label for="authNetId">Authorized Network ID:</label></td>
			<td><input type="text" name="authNetId" data-dojo-type="dijit/form/ValidationTextBox"
						required="true"
						regExp="^[0-9]{8}$"
						promptMessage="Enter unique authorized network id, 8 digits."
						invalidMessage="8 digits, example:'88888888'"
						/></td>
		</tr>
		<tr>
			<td><label for="authNetType">Authorized Network Type:</label></td>
			<td>
				<div name="authNetType" data-dojo-type="dijit/form/Select" data-dojo-props="value:'0'">
					<span data-dojo-value="0">Auto Channel</span>
					<span data-dojo-value="1">Agent Channel</span>
				</div>
			</td>
		</tr>
		<tr>
			<td><label for="name">Name:</label></td>
			<td><input type="text" name="name" data-dojo-type="dijit/form/ValidationTextBox"
	                    required="true"
						promptMessage="Enter the name of the authorized network, the max length is 255 characters."
						invalidMessage="Invalid name"/></td>
		</tr>
		<tr>
			<td><label for="acquirerId">Acquier ID:</label></td>
			<td><input type="text" name="acquirerId" data-dojo-type="dijit/form/ValidationTextBox"
						maxLength="20"/></td>
		</tr>

		<tr>
			<td><label for="issuerId">Issuer ID:</label></td>
			<td><input type="text" name="issuerId" data-dojo-type="dijit/form/ValidationTextBox"
						maxLength="20"/></td>
		</tr>
		<tr>
			<td>
				<div data-dojo-type="dijit/form/CheckBox" name="openFlag" checked=false value="true">

				</div>
				<label for="openFlag">Open Flag</label>
			</td>
		</tr>
		<tr>
			<td><label for="networkStatus">Status:</label></td>
			<td>
				<div name="networkStatus" data-dojo-type="dijit/form/Select" data-dojo-props="value:'O'">
					<span data-dojo-value="O">Normal</span>
					<span data-dojo-value="E">Error</span>
					<span data-dojo-value="C">Close</span>
					<span data-dojo-value="M">Mornitoring</span>
				</div>
			</td>
		</tr>
	</table>
	<div class="dijitDialogPaneActionBar">
		<div class="left-side">
			<br/>
			<div data-dojo-type="dijit/form/CheckBox" 
			     data-dojo-id="authnet.checkIfContinuousAdding"
			     checked=false></div>
			<label>Continuous Adding</label>
		</div>
		
		<div class="right-side">
			<button type="submit" data-dojo-type="dijit/form/Button">add
				<script type="dojo/on" data-dojo-event="click">
				if (authnet.addForm.validate() == false){return;}
				var formObj = dojo.formToObject("authnet.addForm");
				authnet.jsonrest.add(formObj).then(function(results){
					authnet.grid._refresh();
	            }, showSrvErrmsg);
				var continuousAddingFlg = false;
				if (authnet.checkIfContinuousAdding.checked == false){
					authnet.dialogAddForm.hide();
				}else{
					continuousAddingFlg = true;
				}
				authnet.addForm.reset();
				if (continuousAddingFlg==true){
					authnet.checkIfContinuousAdding.checked = true;
				}

		    </script>
			</button>
		</div>
	</div>
</div>
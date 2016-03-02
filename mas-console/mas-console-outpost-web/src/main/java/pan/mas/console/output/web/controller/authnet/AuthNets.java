package pan.mas.console.output.web.controller.authnet;

import java.util.ArrayList;
import java.util.List;


public class AuthNets {
	private String identifier = "authNetId";
	
	private List<AuthNet> items = new ArrayList<AuthNet>();

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public List<AuthNet> getItems() {
		return items;
	}

	public void setItems(List<AuthNet> items) {
		this.items = items;
	}	
}

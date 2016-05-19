/**
 * 
 */
package pan.mas.console.output.web.controller.security.reception.viewmodels;

import java.util.ArrayList;
import java.util.List;

/**
 * @author panqingrong
 *
 */
public class ResourcesGroup {
	private String gid;
	private String name;
	private List<SecuredResource> resources = new ArrayList<SecuredResource>();
	private String description;
	private char status;
	
	public ResourcesGroup() {
		super();
	}
	
	public String getGid() {
		return gid;
	}
	public void setGid(String gid) {
		this.gid = gid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<SecuredResource> getResources() {
		return resources;
	}
	public void setResources(List<SecuredResource> resources) {
		this.resources = resources;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}

}

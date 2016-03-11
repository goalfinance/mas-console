/**
 * 
 */
package pan.mas.console.output.web.controller.security.reception.viewmodels;

import java.util.Date;

/**
 * @author panqingrong
 *
 */
public class SecuredResource {
	private String rid;
	private String name;
	private String description;
	private String rlocation;
	private Date createTime;
	private char status;
	
	
	public SecuredResource() {
		super();
	}
	public String getRid() {
		return rid;
	}
	public void setRid(String rid) {
		this.rid = rid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRlocation() {
		return rlocation;
	}
	public void setRlocation(String rlocation) {
		this.rlocation = rlocation;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}

}

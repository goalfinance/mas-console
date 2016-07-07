/**
 * 
 */
package pan.mas.console.output.web.service.security.reception.models;

import java.util.Date;

/**
 * @author panqingrong
 *
 */
public class SecuredResource {
	public final static String CHECKED_TRUE="true";
	public final static String CHECKED_FALSE="false";
	private String rid;
	private String checked = CHECKED_FALSE;
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
	public String getChecked() {
		return checked;
	}
	public void setChecked(String checked) {
		this.checked = checked;
	}

}

/**
 * 
 */
package pan.mas.console.output.web.controller.dojo.security.maintaining.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author panqingrong
 *
 */
public class RolePermission implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8588834257679772225L;
	
	private Long sid;
	
	private Long resourceSid;
	
	private String resourceName;
	
	private String permittedAction;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	private Date createTime;

	
	public RolePermission() {
		super();
	}

	public Long getResourceSid() {
		return resourceSid;
	}

	public void setResourceSid(Long resourceSid) {
		this.resourceSid = resourceSid;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getPermittedAction() {
		return permittedAction;
	}

	public void setPermittedAction(String permittedAction) {
		this.permittedAction = permittedAction;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}


}

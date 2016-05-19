/**
 * 
 */
package pan.mas.console.core.outpost.web.security.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author panqingrong
 *
 */
@Entity
@Table(name="t_security_role_permission")
@Data
public class SecurityRolePermission implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6691190042051402003L;

	public static final String STATUS_ACTIVE = "A";
	public static final String STATUS_DISABLE = "D";
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="sid")
	private Long sId;
	
	@Column(name="role_sid")
	private Long roleSid;
	
	@Column(name="resource_sid")
	private Long resourceSid;
	
	@Column(name="resource_group_sid")
	private Long resourceGroupSid;
	
	@Column(name="permission_string")
	private String permission;
	
	@Column(name="create_time")
	private Date createtime;
	
	@Column(name="status")
	private String status;
}

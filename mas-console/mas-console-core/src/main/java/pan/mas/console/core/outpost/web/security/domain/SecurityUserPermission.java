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
@Table(name="t_security_user_permission")
@Data
public class SecurityUserPermission implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1871371868319462625L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="sid")
	private Long sId;
	
	@Column(name="user_sid")
	private Long userSid;
	
	@Column(name="resource_sid")
	private Long resourceSid;
	
	@Column(name="resource_group_sid")
	private Long resourceGroupSid;
	
	@Column(name="permission_string")
	private String permission;
	
	@Column(name="create_time")
	private Date createTime;
	
	@Column(name="status")
	private String status;
}

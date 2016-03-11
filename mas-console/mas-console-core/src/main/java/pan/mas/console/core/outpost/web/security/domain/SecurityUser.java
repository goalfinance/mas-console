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
@Table(name="t_security_user")
@Data
public class SecurityUser implements Serializable{
	public static final String STATUS_ACTIVE = "A";
	public static final String STATUS_DISABLE = "D";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -13346578889203827L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="sid")
	private Long sId;
	
	@Column(name="user_id")
	private String userId;
	
	@Column(name="roles")
	private String roles;
	
	@Column(name="full_name")
	private String fullName;
	
	@Column(name="nick_name")
	private String nickName;
	
	@Column(name="passwd")
	private String passwd;
	
	@Column(name="create_time")
	private Date createTime;
	
	@Column(name="status")
	private String status = STATUS_ACTIVE;
}

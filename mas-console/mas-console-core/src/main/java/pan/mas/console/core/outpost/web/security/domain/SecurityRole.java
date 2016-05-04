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

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * @author panqingrong
 *
 */
@Entity
@Table(name="t_security_role")
@Data
public class SecurityRole implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2846969983297889088L;
	
	public static final String STATUS_ACTIVE = "A";
	public static final String STATUS_DISABLE = "D";
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="sid")
	private Long sId;
	
	@Column(name="role_name")
	private String roleName;
	
	@Column(name="status")
	private String status = STATUS_ACTIVE;

	@Column(name="create_time")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	private Date createTime;
	
}

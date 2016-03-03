/**
 * 
 */
package pan.mas.console.core.outpost.web.security.domain;

import java.io.Serializable;

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
@Table(name="t_security_resource_group")
@Data
public class SecurityResourceGroup implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1451217053541246534L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="sid")
	private Long sid;
	
	@Column(name="name")
	private String name;
	
	@Column(name="status")
	private String status;
	
	@Column(name="desc")
	private String description;

}

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
@Table(name="t_security_resource")
@Data
public class SecurityResource implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8985743883164561587L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="sid")
	private Long sid;
	
	@Column(name="sort_index")
	private int sortIdx = 0;
	
	@Column(name="name")
	private String name;
	
	@Column(name="location")
	private String location;
	
	@Column(name="group_sid")
	private Long groupSid;
	
	@Column(name="status")
	private String status;
	
	@Column(name="desc")
	private String description;
	
}

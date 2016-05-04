/**
 * 
 */
package pan.mas.console.core.outpost.web.security.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;

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
	public static final String ROLESSTRING_SPLITTER = ",";
	
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
	
	@Transient
	private String plainPasswd;
	
	@Column(name="passwd")
	private String passwd;
	
	@Column(name="salt")
	private String salt;
	
	@Column(name="create_time")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	private Date createTime;
	
	@Column(name="status")
	private String status = STATUS_ACTIVE;
	
	@Transient
	//TODO Is it worth to define a instance variable for calculating roles-set only once?
	Set<String> rolesSet = new HashSet<String>();
	
	public Set<String> getRolesSet(){
		if (rolesSet.isEmpty()){
			if (roles != null && !roles.equals("")){
				String[] rolesArray = roles.split(ROLESSTRING_SPLITTER);
				for (int i = 0; i < roles.length(); i++){
					rolesSet.add(rolesArray[i]);
				}
			}
			return rolesSet;
		}else
			return rolesSet;
		}
	}


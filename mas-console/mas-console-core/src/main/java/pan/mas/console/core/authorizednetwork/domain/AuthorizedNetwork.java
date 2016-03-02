/**
 * 
 */
package pan.mas.console.core.authorizednetwork.domain;

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
 * The authorized network, often including the entrances allowed to be used by financial institutes
 * 
 * @author panqingrong
 *
 */
@Entity
@Table(name = "t_auth_net")
@Data
public class AuthorizedNetwork implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -618801553980632587L;

	/**
	 * 网络正常状态
	 */
	public static final String NETWORK_STATUS_NORMAL = "O";
	
	/**
	 * 网络故障状态
	 */
	public static final String NETWORK_STATUS_ERROR = "E";
	
	/**
	 * 网络关闭状态
	 */
	public static final String NETWORK_STATUS_CLOSED = "C";
	
	/**
	 * 网络连接中状态
	 */
	public static final String NETWORK_STATUS_MONITORING = "M";
	
	/**
	 * 授权网络类型-自动通道
	 */
	public static final String AUTH_NET_TYPE_AUTO = "0";
	
	/**
	 * 授权网络类型-授权代理通道
	 */
	public static final String AUTH_NET_TYPE_AGENT = "1";
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="auth_net_sid")
	private Long sId;
	
	@Column(name="auth_net_id", length=8)
	private String authNetId;
	
	@Column(name="auth_net_type", length=1)
	private String authNetType;
	
	@Column(name="acquirer_id", length=20)
	private String acquirerId;
	
	@Column(name="issuer_id", length=20)
	private String issuerId;
	
	@Column(name="name", length=255)
	private String name;
	
	@Column(name="open_flg")
	private Boolean openFlag = false;
	
	@Column(name="net_status", length=1)
	private String networkStatus = NETWORK_STATUS_CLOSED;
	
	@Column(name="login_flg")
	private Boolean loginFlag = false;
	
	@Column(name="last_login_time")
	private Date lastLoginTime;
	
	
}

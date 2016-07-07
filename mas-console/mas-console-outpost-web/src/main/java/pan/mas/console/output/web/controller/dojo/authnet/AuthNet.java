package pan.mas.console.output.web.controller.dojo.authnet;

import java.util.Date;

import lombok.Data;

@Data
public class AuthNet {
	private Long sId;
	
	private String authNetId;
	
	private String authNetType;
	
	private String acquirerId;
	
	private String issuerId;
	
	private String name;
	
	private Boolean openFlag = false;
	
	private String networkStatus;
	
	private Boolean loginFlag = false;
	
	private Date lastLoginTime;
}

/**
 * 
 */
package pan.mas.console.output.web.controller.security.maintaining;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author panqingrong
 *
 */
@Controller
@RequestMapping("/security/maintaining/user")
public class SecurityUserViewController {
	@RequestMapping("maintain_security_user")
	public String showMaintainSecurityUser(){
		return "security/maintain/MaintainSecurityUser";
	}
	
	@RequestMapping("show_add_form")
	public String showAddForm(){
		return "security/maintain/AddSecurityUser";
	}
}

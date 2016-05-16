/**
 * 
 */
package pan.mas.console.output.web.controller.security.maintaining;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@RequestMapping("show_view_form")
	public String showViewForm(@RequestParam(name="sId", required=true)String sId, Model model){
		model.addAttribute("sId", sId);
		return "security/maintain/ViewSecurityUser";
	}
	
	@RequestMapping("change_password")
	public String changePassword(@RequestParam(name="sId", required=true)String sId, Model model){
		model.addAttribute("sId", sId);
		return "security/maintain/ChangeSecurityUsersPassword";
		
	}
}

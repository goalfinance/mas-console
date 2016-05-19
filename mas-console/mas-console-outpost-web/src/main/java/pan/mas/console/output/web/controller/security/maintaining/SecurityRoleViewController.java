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
@RequestMapping("security/maintaining/role")
public class SecurityRoleViewController {
	
	@RequestMapping("show_role_selection")
	public String showRoleSelectionView(){
		return "security/maintain/RoleSelectionView";
	}
	
	@RequestMapping("show_role_selection_forview")
	public String showRoleSelectionForView(){
		return "security/maintain/RoleSelectionForView";
	}
	
	@RequestMapping("maintain_security_role")
	public String showMaintainSecurityRole(){
		return "security/maintain/MaintainSecurityRole";
	}
	
	@RequestMapping("show_add_form")
	public String  showAddForm(){
		return "security/maintain/AddSecurityRole";		
	}
	
	@RequestMapping("show_view_form")
	public String showViewForm(@RequestParam(name="sId", required=true)String sId, Model model){
		model.addAttribute("sId", sId);
		return "security/maintain/ViewSecurityRole";
	}

}

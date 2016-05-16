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

}

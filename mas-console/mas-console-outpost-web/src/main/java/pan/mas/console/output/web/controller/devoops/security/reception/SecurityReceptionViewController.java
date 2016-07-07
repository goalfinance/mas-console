/**
 * 
 */
package pan.mas.console.output.web.controller.devoops.security.reception;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import pan.mas.console.output.web.service.security.reception.SecurityReceptionService;
import pan.mas.console.output.web.service.security.reception.models.LoginBean;
import pan.mas.console.output.web.service.security.reception.models.ResourcesGroup;
import pan.utils.AppBizException;

/**
 * @author panqingrong
 *
 */
@Controller("devoops.SecurityReceptionViewController")
@RequestMapping("/bs/security")
public class SecurityReceptionViewController {

	@Autowired
	private SecurityReceptionService securityReceptionService;

	@RequestMapping("show_frame")
	public String showAppFrame(Model model) {
		try {
			List<ResourcesGroup> groups = null;
			groups = securityReceptionService.getResourceGroup(-1L);
			for (ResourcesGroup rg : groups) {
				rg.setResources(securityReceptionService.getSecuredResources(Long.valueOf(rg.getGid())));
			}
			model.addAttribute("groups", groups);
		} catch (AppBizException e) {

		}
		return "devoops/security/AppFrame";
	}
	
	@RequestMapping("show_login")
	public String showLogin(Model model){
		LoginBean loginBean = new LoginBean();
		model.addAttribute("login", loginBean);
		
		return "devoops/security/Login";
	}

}

package pan.mas.console.output.web.controller.dojo.security.reception;

import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pan.mas.console.output.web.service.security.reception.SecurityReceptionService;
import pan.mas.console.output.web.service.security.reception.models.LoginBean;
import pan.mas.console.output.web.service.security.reception.models.ResourcesGroup;
import pan.mas.console.output.web.service.security.reception.models.SecuredResource;
import pan.mas.console.output.web.shiro.ApplicationRealm.ShiroUser;
import pan.utils.AppBizException;

@Controller("dojo.SecurityReceptionViewController")
@RequestMapping("dojo/security")
public class SecurityReceptionViewController {	
	private final Log log = LogFactory.getLog(this.getClass());
	
	@Autowired
	private SecurityReceptionService securityReceptionService;
	
	

	@RequestMapping(value = "show_login")
	public String showLogin(Model model) {
		Subject currentUser = SecurityUtils.getSubject();
		if (currentUser.isAuthenticated() == true) {
			ShiroUser sUser = (ShiroUser) currentUser.getPrincipals().getPrimaryPrincipal();
			model.addAttribute("nickname", sUser.getNickname());
			model.addAttribute("userSid", sUser.getUsersid());
		}
		return "dojo/security/ShowLoginHeader";
	}

	@RequestMapping(value = "dialog_login")
	public String dialogLogin(Model model) {
		LoginBean loginBean = new LoginBean();
		model.addAttribute("login", loginBean);

		return "dojo/security/DialogLogin";
	}

	@RequestMapping(value = "show_users_profile_form")
	public String showUsersProfileForm(@RequestParam(name = "sId", required = true) String sId, Model model) {
		model.addAttribute("sId", sId);
		return "dojo/security/maintain/ViewSecurityUsersProfile";
	}

	// @RequestMapping(value="login", method=RequestMethod.POST)
	// public String login(@ModelAttribute LoginBean loginBean){
	// assert (loginBean.getUsername() != null &&
	// loginBean.getUsername().equals("") == false);
	// assert (loginBean.getPassword() != null &&
	// loginBean.getPassword().equals("") == false);
	// Subject currentUser = SecurityUtils.getSubject();
	// if (currentUser.isAuthenticated() == false){
	// UsernamePasswordToken token = new
	// UsernamePasswordToken(loginBean.getUsername(), loginBean.getPassword());
	// currentUser.login(token);
	// }
	//
	// return "redirect:/index.jsp";
	// }

	@RequestMapping(value = "show_frame")
	public String showRGroups(Model model) {
		List<ResourcesGroup> groups = null;		
		Long userSid = -1L;
		Subject currentUser = SecurityUtils.getSubject();
		if (currentUser.isAuthenticated() == true) {
			ShiroUser shiroUser = (ShiroUser) currentUser.getPrincipals().getPrimaryPrincipal();
			userSid = Long.valueOf(shiroUser.getUsersid());
		}
	
		try {
			groups = securityReceptionService.getResourceGroup(userSid);
			model.addAttribute("groups", groups);
		} catch (AppBizException e) {
			log.error(e.getMessage(), e);
		}

		return "dojo/security/FrameUI";
	}

	@RequestMapping(value = "show_resources/{gid}")
	public String showResources(@PathVariable String gid, Model model) {
		Set<SecuredResource> resources = null;
		try {
			resources = securityReceptionService.getSecuredResources(Long.valueOf(gid));
			model.addAttribute("resources", resources);
		} catch (NumberFormatException e) {
			log.error(e.getMessage(), e);
		} catch (AppBizException e) {
			log.error(e.getMessage(), e);
		}
		return "dojo/security/ShowResources";
	}
}

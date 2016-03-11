package pan.mas.console.output.web.controller.security.reception;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.dubbo.config.annotation.Reference;

import pan.mas.console.core.outpost.web.security.domain.SecurityResource;
import pan.mas.console.core.outpost.web.security.domain.SecurityResourceGroup;
import pan.mas.console.core.outpost.web.security.service.SecurityResourceService;
import pan.mas.console.output.web.controller.security.reception.viewmodels.LoginBean;
import pan.mas.console.output.web.controller.security.reception.viewmodels.ResourcesGroup;
import pan.mas.console.output.web.controller.security.reception.viewmodels.SecuredResource;
import pan.utils.AppBizException;

@Controller
@RequestMapping("/security")
public class SecurityReceptionViewController {
	private final Log log = LogFactory.getLog(this.getClass());
	
	@Reference
	@Autowired(required=false)
	private SecurityResourceService securityResourceService;

	@RequestMapping(value="show_login")
	public String showLogin(Model model){
		Subject currentUser = SecurityUtils.getSubject();
		if (currentUser.isAuthenticated() == true){
			Map<String, String> account = currentUser.getPrincipals().oneByType(java.util.Map.class);
			model.addAttribute("nickname", account.get("nickname"));
		}
		return "security/ShowLoginHeader";
	}
	
	@RequestMapping(value="dialog_login")
	public String dialogLogin(Model model){
		LoginBean loginBean = new LoginBean();
		model.addAttribute("login", loginBean);
		
		return "security/DialogLogin";
	}
	
//	@RequestMapping(value="login", method=RequestMethod.POST)
//	public String login(@ModelAttribute LoginBean loginBean){
//		assert (loginBean.getUsername() != null && loginBean.getUsername().equals("") == false);
//		assert (loginBean.getPassword() != null && loginBean.getPassword().equals("") == false);
//		Subject currentUser = SecurityUtils.getSubject();
//		if (currentUser.isAuthenticated() == false){
//			UsernamePasswordToken token = new UsernamePasswordToken(loginBean.getUsername(), loginBean.getPassword());
//			currentUser.login(token);
//		}
//		
//		return "redirect:/index.jsp"; 
//	}
	
	@RequestMapping(value="show_frame")
	public String showRGroups(Model model){	
		Set<ResourcesGroup> groups = new HashSet<ResourcesGroup>();
		model.addAttribute("groups", groups);
		try {
			List<SecurityResourceGroup> srgs = securityResourceService.findAllSecurityResourceGroup();
			
			for(SecurityResourceGroup srg:srgs){
				ResourcesGroup rg = new ResourcesGroup();
				rg.setGid(srg.getSid().toString());
				rg.setName(srg.getName());
				groups.add(rg);				
			}
		} catch (AppBizException e) {
			log.error(e.getMessage(), e);
		}
		
		return "security/FrameUI";					
	}
	
	@RequestMapping(value="show_resources/{gid}")
	public String showResources(@PathVariable String gid, Model model){
		Set<SecuredResource> resources = new HashSet<SecuredResource>();
		model.addAttribute("resources", resources);
		try {
			List<SecurityResource> srs = securityResourceService.findSecurityResourceByGroupSid(Long.valueOf(gid));
			for (SecurityResource sr:srs){
				SecuredResource securedResource = new SecuredResource();
				securedResource.setRid(sr.getSid().toString());
				securedResource.setName(sr.getName());
				securedResource.setRlocation(sr.getLocation());
				
				resources.add(securedResource);
			}
		} catch (NumberFormatException e) {
			log.error(e.getMessage(), e);
		} catch (AppBizException e) {
			log.error(e.getMessage(), e);
		}
		return "security/ShowResources";
	}
}

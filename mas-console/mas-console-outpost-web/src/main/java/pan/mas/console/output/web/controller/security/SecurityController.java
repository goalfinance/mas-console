package pan.mas.console.output.web.controller.security;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pan.mas.console.output.web.controller.security.formbean.LoginBean;
import pan.mas.console.output.web.controller.security.models.ResourcesGroup;
import pan.mas.console.output.web.controller.security.models.SecuredResource;

@Controller
@RequestMapping("/security")
public class SecurityController {

	@RequestMapping(value="show_login")
	public String showLogin(Model model){
		Subject currentUser = SecurityUtils.getSubject();
		if (currentUser.isAuthenticated() == true){
			Map account = currentUser.getPrincipals().oneByType(java.util.Map.class);
			model.addAttribute("username", account.get("givenName"));
		}
		return "security/ShowLoginHeader";
	}
	
	@RequestMapping(value="dialog_login")
	public String dialogLogin(Model model){
		LoginBean loginBean = new LoginBean();
		model.addAttribute("login", loginBean);
		
		return "security/DialogLogin";
	}
	
	@RequestMapping(value="login", method=RequestMethod.POST)
	public String login(@ModelAttribute LoginBean loginBean){
		assert (loginBean.getUsername() != null && loginBean.getUsername().equals("") == false);
		assert (loginBean.getPassword() != null && loginBean.getPassword().equals("") == false);
		Subject currentUser = SecurityUtils.getSubject();
		if (currentUser.isAuthenticated() == false){
			UsernamePasswordToken token = new UsernamePasswordToken(loginBean.getUsername(), loginBean.getPassword());
			currentUser.login(token);
		}
		
		return "redirect:/index.jsp"; 
	}
	
	@RequestMapping(value="show_frame")
	public String showRGroups(Model model){
		Set<ResourcesGroup> groups = new HashSet<ResourcesGroup>();
		model.addAttribute("groups", groups);
		
		ResourcesGroup rGroup1 = new ResourcesGroup();
		rGroup1.setGid("G001");
		rGroup1.setName("Group one");
		rGroup1.setDescription("Group one");
		
		ResourcesGroup rGroup2 = new ResourcesGroup();
		rGroup2.setGid("G002");
		rGroup2.setName("Group Two");
		rGroup2.setDescription("Group Two");
		
		ResourcesGroup rGroup3 = new ResourcesGroup();
		rGroup3.setGid("G003");
		rGroup3.setName("Group Three");
		
		groups.add(rGroup1);
		groups.add(rGroup2);
		groups.add(rGroup3);
		
		return "security/FrameUI";
		
	}
	
	@RequestMapping(value="show_resources/{gid}")
	public String showResources(@PathVariable String gid, Model model){
		Set<SecuredResource> resources = new HashSet<SecuredResource>();
		SecuredResource resource1 = new SecuredResource();
		resource1.setRid("R001");
		resource1.setName("Resource One");
		
		SecuredResource resource2 = new SecuredResource();
		resource2.setRid("R002");
		resource2.setName("Resource Two");
		
		SecuredResource resource3 = new SecuredResource();
		resource3.setRid("R003");
		resource3.setName("Resource Three");
		
		resources.add(resource1);
		resources.add(resource2);
		resources.add(resource3);
		model.addAttribute("resources", resources);
		
		
		return "security/ShowResources";
	}
}

/**
 * 
 */
package pan.mas.console.output.web.controller.security.maintaining;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pan.mas.console.core.outpost.web.security.domain.SecurityRole;
import pan.mas.console.core.outpost.web.security.service.OutpostWebSecurityService;
import pan.utils.web.MediaTypes;

/**
 * @author panqingrong
 *
 */
@RestController
@RequestMapping("/security/maintaining/role/maintain")
public class SecurityRoleRestController {
	
	@Autowired
	private OutpostWebSecurityService outpostWebSecurityService;
	
	@RequestMapping(method=RequestMethod.GET, produces=MediaTypes.JSON_UTF_8)
	public List<SecurityRole> list(){
		return outpostWebSecurityService.findAllSecurityRoles();
	}

}

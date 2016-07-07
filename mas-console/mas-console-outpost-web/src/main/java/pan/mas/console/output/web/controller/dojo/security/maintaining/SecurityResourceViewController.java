/**
 * 
 */
package pan.mas.console.output.web.controller.dojo.security.maintaining;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pan.mas.console.core.outpost.web.security.domain.SecurityResource;
import pan.mas.console.core.outpost.web.security.domain.SecurityResourceGroup;
import pan.mas.console.core.outpost.web.security.domain.SecurityRolePermission;
import pan.mas.console.core.outpost.web.security.service.OutpostWebSecurityService;
import pan.mas.console.core.outpost.web.security.service.SecurityResourceService;
import pan.mas.console.output.web.service.security.reception.models.ResourcesGroup;
import pan.mas.console.output.web.service.security.reception.models.SecuredResource;
import pan.utils.AppBizException;

/**
 * @author panqingrong
 *
 */
@Controller
@RequestMapping("dojo/security/maintaining/resource")
public class SecurityResourceViewController {
	private final Log log = LogFactory.getLog(this.getClass());
	
	@Autowired
	private SecurityResourceService securityResourceService;
	
	@Autowired
	private OutpostWebSecurityService outpostWebSecurityService;
	
	@RequestMapping("show_resource_selection_view")
	public String showResourceSelectionView(@RequestParam(name="roleSid", required=true)Long roleSid, Model model){
		List<ResourcesGroup> groups = new ArrayList<ResourcesGroup>();
		model.addAttribute("groups", groups);
		model.addAttribute("roleSid", roleSid);
		try {
			List<SecurityResourceGroup> srgs = securityResourceService.findAllSecurityResourceGroup();

			List<SecurityRolePermission> srps = outpostWebSecurityService.findPermissionByRoleSid(roleSid);
			
			for (SecurityResourceGroup srg : srgs) {
				ResourcesGroup rg = new ResourcesGroup();
				rg.setGid(srg.getSid().toString());
				rg.setName(srg.getName());
				groups.add(rg);
				List<SecurityResource> srs = securityResourceService.findSecurityResourceByGroupSid(srg.getSid());
				for (SecurityResource sr : srs) {
					SecuredResource securedResource = new SecuredResource();
					securedResource.setRid(sr.getSid().toString());
					securedResource.setName(sr.getName());
					securedResource.setRlocation(sr.getLocation());
					
					String checked = SecuredResource.CHECKED_FALSE;
					for (SecurityRolePermission srp: srps){
						if (sr.getSid() == srp.getResourceSid()){
							checked = SecuredResource.CHECKED_TRUE;
						}
					}
					securedResource.setChecked(checked);

					rg.getResources().add(securedResource);
				}
			}
			
			
		} catch (AppBizException e) {
			log.error(e.getMessage(), e);
		}
		return "dojo/security/maintain/SecurityResourceSelection";
	}

}

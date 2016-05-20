/**
 * 
 */
package pan.mas.console.output.web.controller.security.maintaining;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import pan.mas.console.core.outpost.web.security.domain.SecurityResource;
import pan.mas.console.core.outpost.web.security.domain.SecurityRolePermission;
import pan.mas.console.core.outpost.web.security.service.OutpostWebSecurityService;
import pan.mas.console.core.outpost.web.security.service.SecurityResourceService;
import pan.mas.console.output.web.controller.security.maintaining.beans.RolePermission;
import pan.utils.AppBizException;
import pan.utils.web.MediaTypes;

/**
 * @author panqingrong
 *
 */
@RestController
@RequestMapping("/security/maintaining/permission")
public class SecurityPermissionRestController {
	private final Log log = LogFactory.getLog(this.getClass());
	
	@Autowired
	private OutpostWebSecurityService outpostWebSecurityService;
	
	@Autowired
	private SecurityResourceService securityResourceService;
		
	@RequestMapping(value="role", method=RequestMethod.GET, produces=MediaTypes.JSON_UTF_8)
	public List<RolePermission> listRolePermission(@RequestParam(name="roleSid", required=false)Long roleSid) throws AppBizException{
		List<RolePermission> rps = new ArrayList<RolePermission>();
	
		List<SecurityRolePermission> srps = outpostWebSecurityService.findPermissionByRoleSid(roleSid);

		for (SecurityRolePermission srp:srps){
			RolePermission rp = new RolePermission();
			SecurityResource sr = securityResourceService.findSecurityResourceBySid(srp.getResourceSid());
			rp.setResourceSid(srp.getResourceSid());
			rp.setResourceName(sr.getName());
			rp.setSid(srp.getSId());
			String permissionString = srp.getPermission();
			int idx = permissionString.lastIndexOf(":");
			String permittedAction = permissionString.substring(idx + 1);
			rp.setPermittedAction(permittedAction);
			rp.setCreateTime(srp.getCreatetime());
			rps.add(rp);
		}

		return rps;
	}
	
	@RequestMapping(value="role/{roleSid}", method=RequestMethod.PUT, consumes=MediaTypes.JSON_UTF_8)
	public void update(@PathVariable Long roleSid, @RequestBody Map<Long, Long> permissions) throws AppBizException{
		Set<Long> permissionSids = permissions.keySet();
		outpostWebSecurityService.saveSecurityRolePermissions(roleSid, permissionSids, "view");
		
	}
	@RequestMapping(value="role/{sId}", method=RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remove(@PathVariable Long sId) throws AppBizException{
		log.debug("remove security role permission[sid='" + sId + "']");

		SecurityRolePermission securityRolePermission= outpostWebSecurityService.findSecurityRolePermissionBySid(sId);
		if (securityRolePermission != null){
			outpostWebSecurityService.deleteSecurityRolePermission(securityRolePermission);
		}
	
		log.debug("remove security role permissio[sid='" + sId + "'] -- success");
	}

}

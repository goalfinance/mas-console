/**
 * 
 */
package pan.mas.console.output.web.controller.dojo.security.maintaining;

import java.net.URI;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import pan.mas.console.core.outpost.web.security.domain.SecurityRole;
import pan.mas.console.core.outpost.web.security.service.OutpostWebSecurityService;
import pan.utils.AppBizException;
import pan.utils.web.MediaTypes;

/**
 * @author panqingrong
 *
 */
@RestController
@RequestMapping("dojo/security/maintaining/role/maintain")
public class SecurityRoleRestController {
	private final Log log = LogFactory.getLog(this.getClass());
	
	@Autowired
	private OutpostWebSecurityService outpostWebSecurityService;
	
	@RequestMapping(value="active", method=RequestMethod.GET, produces=MediaTypes.JSON_UTF_8)
	public List<SecurityRole> listActiveRoles(){
		return outpostWebSecurityService.findAllActiveSecurityRoles();
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes=MediaTypes.JSON_UTF_8)
	public ResponseEntity<?> add(@RequestBody SecurityRole securityRole, UriComponentsBuilder uriBuilder) throws AppBizException{
		assert securityRole != null;
		log.debug("Add security role");
		securityRole.setCreateTime(new Date());
		outpostWebSecurityService.saveSecurityRole(securityRole);
		
		URI uri = uriBuilder.path("/security/maintaining/role/maintain/" + securityRole.getSId()).build().toUri();
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(uri);
		
		log.debug("Add security role[sid='" + securityRole.getSId() + "'] -- success");
		return new ResponseEntity<String>(httpHeaders, HttpStatus.CREATED);
		
	}
	
	@RequestMapping(value="{sId}", method=RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remove(@PathVariable Long sId) throws AppBizException{
		log.debug("remove security role[sid='" + sId + "']");
		
		SecurityRole securityRole = outpostWebSecurityService.findSecurityRoleBySid(sId);
		if (securityRole.getStatus().equals(SecurityRole.STATUS_DISABLE) == false){
			securityRole.setStatus(SecurityRole.STATUS_DISABLE);
			outpostWebSecurityService.saveSecurityRole(securityRole);
		}

		log.debug("remove security role[sid='" + sId + "'] -- success");
	}
	
	@RequestMapping(value="{sId}", method=RequestMethod.GET, produces=MediaTypes.JSON_UTF_8)
	public SecurityRole get(@PathVariable Long sId) throws AppBizException{
		return outpostWebSecurityService.findSecurityRoleBySid(sId);
	}
	
	@RequestMapping(value="{sId}", method=RequestMethod.PUT, consumes=MediaTypes.JSON_UTF_8)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable Long sId, @RequestBody SecurityRole securityRole) throws AppBizException{
		log.debug("update security role[sid='" + sId + "']");

		SecurityRole securityRoleFromDb = outpostWebSecurityService.findSecurityRoleBySid(sId);
		securityRoleFromDb.setRoleName(securityRole.getRoleName());
		outpostWebSecurityService.saveSecurityRole(securityRoleFromDb);
		
		log.debug("update security role[sid='" + sId + "'] -- success");
	}

}

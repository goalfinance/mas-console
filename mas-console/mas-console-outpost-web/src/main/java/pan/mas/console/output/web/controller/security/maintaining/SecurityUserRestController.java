/**
 * 
 */
package pan.mas.console.output.web.controller.security.maintaining;

import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.alibaba.dubbo.config.annotation.Reference;
import com.stormpath.sdk.http.HttpMethod;

import pan.mas.console.core.outpost.web.security.domain.SecurityUser;
import pan.mas.console.core.outpost.web.security.service.OutpostWebSecurityService;
import pan.mas.console.output.web.controller.security.maintaining.beans.ChangePasswordBean;
import pan.mas.console.output.web.dojo.DojoUtils;
import pan.mas.console.output.web.dojo.PaginationInfo;
import pan.utils.AppBizException;
import pan.utils.AppExceptionCodes;
import pan.utils.AppRTException;
import pan.utils.web.MediaTypes;

/**
 * @author panqingrong
 *
 */
@RestController
@RequestMapping("/security/maintaining/user/maintain")
public class SecurityUserRestController {
	
	private final Log log = LogFactory.getLog(this.getClass());
	
	@Autowired(required=false)
	@Reference
	private OutpostWebSecurityService outpostWebSecurityUserService;
	
	@RequestMapping(value="filter", method=RequestMethod.POST, produces=MediaTypes.JSON_UTF_8)
	public List<SecurityUser> filter(HttpServletRequest request){
		return new ArrayList<SecurityUser>();
	}
	
	@RequestMapping(method=RequestMethod.GET, produces=MediaTypes.JSON_UTF_8)
	public List<SecurityUser> listWithPagination(@RequestHeader("Range") String paginationString,
			@RequestParam(name="userId", required=false)String userId,
			HttpServletResponse response, HttpServletRequest request){
		PaginationInfo pi = DojoUtils.getPaginationInfo(paginationString);
		
		Page<SecurityUser> sus = outpostWebSecurityUserService.findSecurityUserByUserId(userId, pi.getPageNum(), pi.getCount());

		response.setHeader("Content-Range", DojoUtils.getHttpContentRange(pi, sus));
		return sus.getContent();
	}
	
	@RequestMapping(value="{sId}",method=RequestMethod.GET, produces=MediaTypes.JSON_UTF_8)
	public SecurityUser get(@PathVariable Long sId) throws AppBizException{
		SecurityUser securityUser = outpostWebSecurityUserService.findSecurityUserBySid(sId);
		
		return securityUser;
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes=MediaTypes.JSON_UTF_8)
	public ResponseEntity<?> add(@RequestBody SecurityUser securityUser, UriComponentsBuilder uriBuilder){
		assert securityUser != null;
		
		securityUser.setCreateTime(new Date());
		try {
			outpostWebSecurityUserService.registerSecurityUser(securityUser);
		} catch (AppBizException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		URI uri = uriBuilder.path("/security/maintaining/user/maintain/" + securityUser.getSId()).build().toUri();
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(uri);
		
		return new ResponseEntity<String>(httpHeaders, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="{sId}", method=RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remove(@PathVariable Long sId){
		log.debug("Remove the security user[sid='" + sId + "']");
		SecurityUser su = null;
		try {
			su = outpostWebSecurityUserService.findSecurityUserBySid(sId);
		} catch (AppBizException e) {
			log.error(e.getMessage(), e);
		}
		
		if (su == null){
			
		}else{
			outpostWebSecurityUserService.deleteSecurityUser(su);
		}
		
		log.debug("Remove the security user[sid='" + sId + "'] -- success");
	}
	
	@RequestMapping(value="{sId}", method=RequestMethod.PUT, consumes=MediaTypes.JSON_UTF_8)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable("sId")Long sId, @RequestBody SecurityUser su){
		log.debug("Update the security user[sid='" + sId + "']");
		SecurityUser suFromDb = null;
		try {
			suFromDb = outpostWebSecurityUserService.findSecurityUserBySid(sId);
		} catch (AppBizException e) {
			log.error(e.getMessage(), e);
		}
		
		try {
//			BeanUtils.copyProperties(suFromDb, su);
			if (su.getFullName() != null && !su.getFullName().equals("")){
				suFromDb.setFullName(su.getFullName());				
			}
			if (su.getNickName() != null && !su.getNickName().equals("")){
				suFromDb.setNickName(su.getNickName());				
			}
			if (su.getRoles() != null && !su.getRoles().equals("")){
				suFromDb.setRoles(su.getRoles());				
			}
			suFromDb.setUpdateTime(new Date());
			outpostWebSecurityUserService.saveSecurityUser(suFromDb);
//		} catch (IllegalAccessException e) {
//			throw new AppRTException(AppExceptionCodes.UNRECOVERABLE_SYSTEM_ERROR[0],
//					"The error in terms of bean copy operation occurred!", e);
//		} catch (InvocationTargetException e) {
//			throw new AppRTException(AppExceptionCodes.UNRECOVERABLE_SYSTEM_ERROR[0],
//					"The error in terms of bean copy operation occurred!", e);
		} catch (AppBizException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.debug("Update the security user[sid='" + sId + "'] --success");
	}
	
	@RequestMapping(value="changepassword/{sId}", method=RequestMethod.POST, consumes=MediaTypes.JSON_UTF_8)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void changePassword(@PathVariable Long sId, @RequestBody ChangePasswordBean cp){
		log.debug("Change the security user[sid'" + sId + "']'s password" );
		try {
			// TODO If the action is not permitted, how to handle it.
			outpostWebSecurityUserService.changePassword(sId, cp.getOldPassword(), cp.getNewPassword());
		} catch (AppBizException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

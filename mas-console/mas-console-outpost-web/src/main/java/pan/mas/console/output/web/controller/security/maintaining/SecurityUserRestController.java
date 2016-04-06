/**
 * 
 */
package pan.mas.console.output.web.controller.security.maintaining;

import java.net.URI;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.alibaba.dubbo.config.annotation.Reference;

import pan.mas.console.core.outpost.web.security.domain.SecurityUser;
import pan.mas.console.core.outpost.web.security.service.SecurityUserService;
import pan.mas.console.output.web.dojo.DojoUtils;
import pan.mas.console.output.web.dojo.PaginationInfo;
import pan.utils.AppBizException;
import pan.utils.web.MediaTypes;

/**
 * @author panqingrong
 *
 */
@RestController
@RequestMapping("/security/maintaining/user/maintain")
public class SecurityUserRestController {
	
	@Autowired
	@Reference
	private SecurityUserService securityUserService;
	
	@RequestMapping(method=RequestMethod.GET, produces=MediaTypes.JSON_UTF_8)
	public List<SecurityUser> listWithPagination(@RequestHeader("Range") String paginationString,
			@RequestParam(name="userId", required=false)String userId,
			HttpServletResponse response){
		PaginationInfo pi = DojoUtils.getPaginationInfo(paginationString);
		
		Page<SecurityUser> sus = securityUserService.findByUserId(userId, pi.getPageNum(), pi.getCount());

		response.setHeader("Content-Range", DojoUtils.getHttpContentRange(pi, sus));
		return sus.getContent();
	}
	
	@RequestMapping(value="{sId}",method=RequestMethod.GET, produces=MediaTypes.JSON_UTF_8)
	public SecurityUser get(@PathVariable Long sId) throws AppBizException{
		SecurityUser securityUser = securityUserService.findOne(sId);
		
		return securityUser;
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes=MediaTypes.JSON_UTF_8)
	public ResponseEntity<?> add(@RequestBody SecurityUser securityUser, UriComponentsBuilder uriBuilder){
		assert securityUser != null;
		
		securityUser.setCreateTime(new Date());
		securityUserService.save(securityUser);
		URI uri = uriBuilder.path("/security/maintaining/user/maintain/" + securityUser.getSId()).build().toUri();
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(uri);
		
		return new ResponseEntity<String>(httpHeaders, HttpStatus.CREATED);
	}

}

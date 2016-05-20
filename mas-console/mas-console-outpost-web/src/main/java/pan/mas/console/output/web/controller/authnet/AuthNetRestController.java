/**
 * 
 */
package pan.mas.console.output.web.controller.authnet;

import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
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

import pan.mas.console.core.authorizednetwork.domain.AuthorizedNetwork;
import pan.mas.console.core.authorizednetwork.service.AuthorizedNetworkService;
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
@RequestMapping("/authnet/maintains")
public class AuthNetRestController {

	private final Log log = LogFactory.getLog(this.getClass());

	@Autowired(required = false)
	@Reference
	private AuthorizedNetworkService authorizedNetworkService;

	@RequestMapping(method=RequestMethod.GET, produces=MediaTypes.JSON_UTF_8)
	public List<AuthNet> listAuthNetsWithPagination(@RequestHeader("Range") String paginationString,
			@RequestParam(name = "authNetId", required = false) String authNetId,
			@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "authNetType", required = false) String authNetType, HttpServletResponse response) {
		PaginationInfo pInfo = DojoUtils.getPaginationInfo(paginationString);

		Page<AuthorizedNetwork> anPagination = authorizedNetworkService.findByAuthNetIdAndNameEndsWithAndAuthNetType(
				authNetId, name, authNetType, new PageRequest(pInfo.getPageNum(), pInfo.getCount()));

		List<AuthNet> authNetList = new ArrayList<AuthNet>();

		for (AuthorizedNetwork an : anPagination.getContent()) {
			AuthNet authNet = new AuthNet();
			try {
				BeanUtils.copyProperties(authNet, an);
			} catch (Exception e) {
				throw new AppRTException(AppExceptionCodes.UNRECOVERABLE_SYSTEM_ERROR[0],
						"The error in terms of bean copy operation occurred!", e);
			}
			authNetList.add(authNet);
		}

		String contentRange = DojoUtils.getHttpContentRange(pInfo, anPagination);
		response.setHeader("Content-Range", contentRange);
		return authNetList;
	}

	@RequestMapping(value = "{sId}", method=RequestMethod.GET, produces=MediaTypes.JSON_UTF_8)
	public AuthNet get(@PathVariable Long sId) throws AppBizException {
		AuthorizedNetwork authorizedNetwork = authorizedNetworkService.findOne(sId);
		
		AuthNet an = new AuthNet();
		try {
			BeanUtils.copyProperties(an, authorizedNetwork);
		} catch (Exception e) {
			throw new AppRTException(AppExceptionCodes.UNRECOVERABLE_SYSTEM_ERROR[0],
					"The error in terms of bean copy operation occurred!", e);
		}
		return an;

	}

	@RequestMapping(value = "{sId}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remove(@PathVariable("sId") Long sId) {
		log.debug("Remove " + sId);
		AuthorizedNetwork an = null;
		try {
			an = authorizedNetworkService.findOne(sId);
		} catch (AppBizException e) {
			log.error(e.getMessage(), e);
		}
		if (an == null) {

		} else {
			authorizedNetworkService.delete(an);
		}
	}

	@RequestMapping(method = RequestMethod.POST, consumes=MediaTypes.JSON_UTF_8)
	public ResponseEntity<?> add(@RequestBody AuthNet authNet, UriComponentsBuilder uriBuilder) {

		AuthorizedNetwork authorizedNetwork = new AuthorizedNetwork();

		try {
			BeanUtils.copyProperties(authorizedNetwork, authNet);

			authorizedNetworkService.save(authorizedNetwork);
			
			Long sid = authorizedNetwork.getSId();
			URI uri = uriBuilder.path("/authnet/maintains/" + sid).build().toUri();
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setLocation(uri);
			
			return new ResponseEntity(httpHeaders, HttpStatus.CREATED);
		} catch (IllegalAccessException e) {
			throw new AppRTException(AppExceptionCodes.UNRECOVERABLE_SYSTEM_ERROR[0],
					"The error in terms of bean copy operation occurred!", e);
		} catch (InvocationTargetException e) {
			throw new AppRTException(AppExceptionCodes.UNRECOVERABLE_SYSTEM_ERROR[0],
					"The error in terms of bean copy operation occurred!", e);
		}
	}

	@RequestMapping(value = "{sId}", method = RequestMethod.PUT, consumes=MediaTypes.JSON_UTF_8)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable("sId") Long sId, @RequestBody AuthNet authNet) {
		log.debug("Update " + sId);
		AuthorizedNetwork authorizedNetwork = null;
		try {
			authorizedNetwork = authorizedNetworkService.findOne(sId);
		} catch (AppBizException e1) {
			log.error(e1.getMessage(), e1);
			return;
		}
		
		try {
			BeanUtils.copyProperties(authorizedNetwork, authNet);
			authorizedNetwork.setSId(sId);
			authorizedNetworkService.save(authorizedNetwork);
		} catch (IllegalAccessException e) {
			throw new AppRTException(AppExceptionCodes.UNRECOVERABLE_SYSTEM_ERROR[0],
					"The error in terms of bean copy operation occurred!", e);
		} catch (InvocationTargetException e) {
			throw new AppRTException(AppExceptionCodes.UNRECOVERABLE_SYSTEM_ERROR[0],
					"The error in terms of bean copy operation occurred!", e);
		}
	}
}

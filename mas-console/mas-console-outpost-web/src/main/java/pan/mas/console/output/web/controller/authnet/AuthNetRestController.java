/**
 * 
 */
package pan.mas.console.output.web.controller.authnet;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;

import pan.mas.console.core.authorizednetwork.domain.AuthorizedNetwork;
import pan.mas.console.core.authorizednetwork.service.AuthorizedNetworkService;
import pan.mas.console.output.web.dojo.DojoUtils;
import pan.mas.console.output.web.dojo.PaginationInfo;
import pan.utils.AppBizException;
import pan.utils.AppExceptionCodes;
import pan.utils.AppRTException;

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

	@RequestMapping(method = RequestMethod.GET)
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
						"The error in term of bean copy operation occurred!", e);
			}
			authNetList.add(authNet);
		}

		String contentRange = "items " + pInfo.getStart() + "-"
				+ (pInfo.getStart() + (anPagination.getContent().size() < pInfo.getCount()
						? anPagination.getContent().size() : pInfo.getCount()))
				+ "/" + anPagination.getTotalElements();
		response.setHeader("Content-Range", contentRange);
		return authNetList;
	}

	@RequestMapping(value = "{sId}", method = RequestMethod.GET)
	public AuthNet get(@PathVariable Long sId) throws AppBizException {
		AuthorizedNetwork authorizedNetwork = authorizedNetworkService.findOne(sId);
		if (authorizedNetwork == null) {
			Object[] args = new Object[1];
			args[0] = sId;
			throw new AppBizException(AppExceptionCodes.AUTHNET_DOES_NOT_EXIST[0],
					AppExceptionCodes.AUTHNET_DOES_NOT_EXIST[1], args);
		}
		AuthNet an = new AuthNet();
		try {
			BeanUtils.copyProperties(an, authorizedNetwork);
		} catch (Exception e) {
			throw new AppRTException(AppExceptionCodes.UNRECOVERABLE_SYSTEM_ERROR[0],
					"The error in term of bean copy operation occurred!", e);
		}
		return an;

	}

	@RequestMapping(value = "{sId}", method = RequestMethod.DELETE)
	public void remove(@PathVariable("sId") Long sId) {
		log.debug("Remove " + sId);
		AuthorizedNetwork an = authorizedNetworkService.findOne(sId);
		if (an == null) {

		} else {
			authorizedNetworkService.delete(an);
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	public void add(@RequestBody AuthNet authNet) {

		AuthorizedNetwork authorizedNetwork = new AuthorizedNetwork();

		try {
			BeanUtils.copyProperties(authorizedNetwork, authNet);

			authorizedNetworkService.save(authorizedNetwork);
		} catch (IllegalAccessException e) {
			throw new AppRTException(AppExceptionCodes.UNRECOVERABLE_SYSTEM_ERROR[0],
					"The error in term of bean copy operation occurred!", e);
		} catch (InvocationTargetException e) {
			throw new AppRTException(AppExceptionCodes.UNRECOVERABLE_SYSTEM_ERROR[0],
					"The error in term of bean copy operation occurred!", e);
		}
	}

	@RequestMapping(value = "{sId}", method = RequestMethod.PUT)
	public void update(@PathVariable("sId") Long sId, @RequestBody AuthNet authNet) {
		log.debug("Update " + sId);
		AuthorizedNetwork authorizedNetwork = authorizedNetworkService.findOne(sId);
		if (authorizedNetwork == null) {

		}

		try {
			BeanUtils.copyProperties(authorizedNetwork, authNet);
			authorizedNetwork.setSId(sId);
			authorizedNetworkService.save(authorizedNetwork);
		} catch (IllegalAccessException e) {
			throw new AppRTException(AppExceptionCodes.UNRECOVERABLE_SYSTEM_ERROR[0],
					"The error in term of bean copy operation occurred!", e);
		} catch (InvocationTargetException e) {
			throw new AppRTException(AppExceptionCodes.UNRECOVERABLE_SYSTEM_ERROR[0],
					"The error in term of bean copy operation occurred!", e);
		}
	}

	@ExceptionHandler(AppBizException.class)
	public ResponseEntity<String> handleAppBizException(AppBizException appBizException) {
		ResponseEntity<String> responseEntity = new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		return responseEntity;
	}
}

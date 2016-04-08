/**
 * 
 */
package pan.mas.console.outpost.rest.authorizednetwork;

import javax.validation.Valid;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;

import pan.mas.console.core.authorizednetwork.domain.AuthorizedNetwork;
import pan.mas.console.core.authorizednetwork.service.AuthorizedNetworkService;
import pan.utils.AppBizException;

/**
 * @author panqingrong
 *
 */
@Component
@RestController
@RequestMapping("/AuthNets")
public class AuthorizedNetworkController {
	@Reference(version="1.0.0")
	private AuthorizedNetworkService authorizedNetworkService;

	@RequestMapping(value="/{authNetId}", method=RequestMethod.GET)
	public AuthorizedNetwork getByAuthNetId(@PathVariable("sId") Long sId) throws AppBizException{
		assert authorizedNetworkService != null;
		System.out.println("AuthNetId='" + sId + "'");
		System.out.println(authorizedNetworkService == null? "authorizedNetworkService is null": "it's ok"); 
		return authorizedNetworkService.findOne(sId);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public void save(@Valid AuthorizedNetwork authorizedNetwork){
		assert authorizedNetwork != null;
		authorizedNetworkService.save(authorizedNetwork);
	}
	
	
	public AuthorizedNetworkService getAuthorizedNetworkService() {
		return authorizedNetworkService;
	}

	public void setAuthorizedNetworkService(AuthorizedNetworkService authorizedNetworkService) {
		this.authorizedNetworkService = authorizedNetworkService;
	}
	
	

}

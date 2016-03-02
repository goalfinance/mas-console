/**
 * 
 */
package pan.mas.console.outpost.rest.authorizednetwork.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pan.mas.console.core.authorizednetwork.service.AuthorizedNetworkService;

/**
 * @author panqingrong
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("dubboContext.xml")
public class AuthorizedNetworkServiceDubboInjectedTest {
	
	private AuthorizedNetworkService authorizedNetworkService;

	@Test
	public void testDubboInjectedService(){
		if (authorizedNetworkService == null){
			System.out.println("is null!");
			
		}else{
			System.out.println("not null!");
		}
	}
	public AuthorizedNetworkService getAuthorizedNetworkService() {
		return authorizedNetworkService;
	}
	@Autowired
	public void setAuthorizedNetworkService(AuthorizedNetworkService authorizedNetworkService) {
		this.authorizedNetworkService = authorizedNetworkService;
	}
	

}

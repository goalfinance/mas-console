/**
 * 
 */
package mas.console.core.authorizednetwork.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import pan.mas.console.core.authorizednetwork.domain.AuthorizedNetwork;
import pan.mas.console.core.authorizednetwork.service.AuthorizedNetworkService;
import pan.mas.console.output.web.ServiceLocalTestConfig;

/**
 * @author panqingrong
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(ServiceLocalTestConfig.class)
@ActiveProfiles("dev")
public class AuthorizedNetworkServiceLocalTest {
	@Autowired
	private AuthorizedNetworkService authorizedNetworkService;

	@Test
	public void testSave(){
		assertThat(authorizedNetworkService, notNullValue());
		
		AuthorizedNetwork authorizedNetwork = new AuthorizedNetwork();
		authorizedNetwork.setAuthNetId("A001");
		authorizedNetwork.setName("test");
		authorizedNetwork.setAuthNetType(AuthorizedNetwork.AUTH_NET_TYPE_AUTO);
		
		authorizedNetworkService.save(authorizedNetwork);
		
		AuthorizedNetwork authNetFromDb = authorizedNetworkService.findByAuthNetId("A001");
		
		assertThat(authorizedNetwork.getAuthNetId(), equalTo(authNetFromDb.getAuthNetId()));
		assertThat(authorizedNetwork.getName(), equalTo(authNetFromDb.getName()));
		assertThat(authorizedNetwork.getAuthNetType(), equalTo(authNetFromDb.getAuthNetType()));
		assertThat(authNetFromDb.getNetworkStatus(), equalTo(AuthorizedNetwork.NETWORK_STATUS_CLOSED));
	}
}

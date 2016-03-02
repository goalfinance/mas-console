/**
 * 
 */
package pan.mas.console.core.authorizednetwork.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import pan.mas.console.core.ApplicationConfig;
import pan.mas.console.core.authorizednetwork.domain.AuthorizedNetwork;
import pan.mas.console.core.authorizednetwork.repository.AuthorizedNetworkRepository;

/**
 * @author panqingrong
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=ApplicationConfig.class)
@ActiveProfiles("ut")
public class AuthorizedNetworkRepositoryIntegrationTesting {
	@Autowired
	private AuthorizedNetworkRepository authorizedNetworkRepository;
	
	@Test
	public void testSave(){
		AuthorizedNetwork authorizedNetwork = new AuthorizedNetwork();
		authorizedNetwork.setAuthNetId("A001");
		authorizedNetwork.setName("test");
		authorizedNetwork.setAuthNetType(AuthorizedNetwork.AUTH_NET_TYPE_AUTO);
		
		authorizedNetworkRepository.save(authorizedNetwork);
		
		AuthorizedNetwork authNetFromDb = authorizedNetworkRepository.findByAuthNetId("A001");
		
		assertThat(authorizedNetwork.getAuthNetId(), equalTo(authNetFromDb.getAuthNetId()));
		assertThat(authorizedNetwork.getName(), equalTo(authNetFromDb.getName()));
		assertThat(authorizedNetwork.getAuthNetType(), equalTo(authNetFromDb.getAuthNetType()));
		assertThat(authNetFromDb.getNetworkStatus(), equalTo(AuthorizedNetwork.NETWORK_STATUS_CLOSED));
		
		authorizedNetworkRepository.deleteAll();
		
	}
	
	private void generateDataForTest(int howMany){
		assert howMany >= 0;
		for(int i = 0;  i < howMany;i++){
			AuthorizedNetwork authNet = new AuthorizedNetwork();
			authNet.setAuthNetId("AN" + i);
			authNet.setName("AuthNet" + i);
			authNet.setAuthNetType(AuthorizedNetwork.AUTH_NET_TYPE_AUTO);
			
			authorizedNetworkRepository.save(authNet);
		}
	}
	
	@Test
	public void testQueryCreationFromMethodName(){
		long rowCount = 7;
		this.generateDataForTest((int)rowCount);
		assertThat((authorizedNetworkRepository.count()), equalTo(rowCount));
		
		AuthorizedNetwork anFromDb = authorizedNetworkRepository.findByAuthNetId("AN2");
		
		assertThat(anFromDb.getAuthNetId(), equalTo("AN2"));
		assertThat(anFromDb.getAuthNetType(), equalTo(AuthorizedNetwork.AUTH_NET_TYPE_AUTO));
		assertThat(anFromDb.getName(), equalTo("AuthNet2"));
		
		anFromDb  = authorizedNetworkRepository.findByName("AuthNet1");
		assertThat(anFromDb.getAuthNetId(), equalTo("AN1"));
		assertThat(anFromDb.getAuthNetType(), equalTo(AuthorizedNetwork.AUTH_NET_TYPE_AUTO));
		assertThat(anFromDb.getName(), equalTo("AuthNet1"));
		authorizedNetworkRepository.deleteAll();
	}
	
	@Test
	public void testQueryCreationFromMethodNameUsingPagination(){
		long rowCount = 7;
		this.generateDataForTest((int)rowCount);
		assertThat(authorizedNetworkRepository.count(), equalTo(rowCount));
		
		Page<AuthorizedNetwork> anPage = authorizedNetworkRepository.findByAuthNetType(AuthorizedNetwork.AUTH_NET_TYPE_AUTO, new PageRequest(2,3));
		assertThat(anPage.getTotalPages(), equalTo(3));
		assertThat(anPage.getSize(), equalTo(3));
		authorizedNetworkRepository.deleteAll();
		
	}
	
	//@Test
	public void testDynamicQueryUsingPagination(){
		long rowCount = 23;
		this.generateDataForTest((int)rowCount);
		assertThat((authorizedNetworkRepository.count()), equalTo(rowCount));
		
		Page<AuthorizedNetwork> anPage = authorizedNetworkRepository.findAll(getWhereClause("", "", "0"), new PageRequest(0, 10));
		assertThat(anPage.getTotalElements(), equalTo(rowCount));
		authorizedNetworkRepository.deleteAll();
	}
	
	private Specification<AuthorizedNetwork> getWhereClause(final String authNetId, final String name, final String authNetType){
		return new Specification<AuthorizedNetwork>(){

			public Predicate toPredicate(Root<AuthorizedNetwork> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
				if (authNetId != null && !authNetId.equals("")){
					predicate.getExpressions().add(cb.equal(root.<String>get("authNetId"), authNetId));
				}
				
				if (name != null && !name.equals("")){
					predicate.getExpressions().add(cb.like(root.<String>get("name"), "%" + name));
				}
				
				if (authNetType != null && !authNetType.equals("")){
					predicate.getExpressions().add(cb.equal(root.<String>get("authNetType"), authNetType));
				}
				return predicate;
			}
			
		};
	}
}

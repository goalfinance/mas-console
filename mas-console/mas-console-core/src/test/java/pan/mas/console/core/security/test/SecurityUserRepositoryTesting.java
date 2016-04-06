package pan.mas.console.core.security.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pan.mas.console.core.ApplicationConfig;
import pan.mas.console.core.TestingConfig;
import pan.mas.console.core.outpost.web.security.domain.SecurityUser;
import pan.mas.console.core.outpost.web.security.repository.SecurityUserRepository;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes={ApplicationConfig.class, TestingConfig.class})
@ActiveProfiles("dev")
public class SecurityUserRepositoryTesting {
	@Autowired
	private SecurityUserRepository securityUserRepository;
	
	private void generateDataForTesting(int howMany){
		assert howMany >= 0;
		
		for (int i=0; i < howMany; i++){
			SecurityUser su = new SecurityUser();
			su.setUserId(i + "test" + i);
			su.setStatus(SecurityUser.STATUS_ACTIVE);
			securityUserRepository.save(su);
		}
	}

	@Test
	public void testFindByUserId(){
		SecurityUser securityUser = new SecurityUser();
		securityUser.setUserId("panqr");
		securityUser.setFullName("Panqingrong");
		securityUser.setNickName("Panpan");
		securityUser.setStatus("N");
		
		securityUserRepository.save(securityUser);
		
		SecurityUser suFromDB = securityUserRepository.findByUserId("panqr");
		
		assertThat(suFromDB.getUserId(), equalTo(securityUser.getUserId()));
		assertThat(suFromDB.getFullName(), equalTo(securityUser.getFullName()));
		assertThat(suFromDB.getNickName(), equalTo(securityUser.getNickName()));
		assertThat(suFromDB.getStatus(),equalTo(securityUser.getStatus()));
		
		securityUserRepository.deleteAll();
	}
	
	@Test
	public void testFindByUserIdLike(){
		int numToGen = 7;
		generateDataForTesting(numToGen);
		
		Page<SecurityUser> sus = securityUserRepository.findByUserIdLike("", new PageRequest(2, 3));
		
		assertThat(sus.getTotalElements(), equalTo((long)numToGen));
		
		securityUserRepository.deleteAll();
	}
}

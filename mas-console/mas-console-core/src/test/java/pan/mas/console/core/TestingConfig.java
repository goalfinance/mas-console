/**
 * 
 */
package pan.mas.console.core;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import pan.utils.annotation.env.UnitTestEnv;

/**
 * @author panqingrong
 *
 */
@Configuration
public class TestingConfig {
	@Bean
	@UnitTestEnv
	public DataSource devDataSource() {
		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL).build();
	}
	
	@Bean
	@UnitTestEnv
	public EntityManagerFactory entityManagerFactory(DataSource dataSource) {
		HibernateJpaVendorAdapter hibernateAdapter = new HibernateJpaVendorAdapter();
		hibernateAdapter.setGenerateDdl(true);
		hibernateAdapter.setShowSql(true);
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		factoryBean.setJpaVendorAdapter(hibernateAdapter);
		factoryBean.setPackagesToScan("pan.mas.console.core.authorizednetwork.domain",
				"pan.mas.console.core.outpost.web.security.domain");
		factoryBean.setDataSource(dataSource);
		factoryBean.afterPropertiesSet();

		return factoryBean.getObject();
	}

}

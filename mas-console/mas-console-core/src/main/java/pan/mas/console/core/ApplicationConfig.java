/**
 * 
 */
package pan.mas.console.core;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import pan.mas.console.core.settings.JPASettings4Hibernate;
import pan.utils.annotation.env.Development;
import pan.utils.annotation.env.Production;
import pan.utils.annotation.env.UnitTestEnv;

/**
 * @author panqingrong
 *
 */
@Configuration
@EnableJpaRepositories
@EnableTransactionManagement
@ComponentScan(basePackages={"pan.mas.console.core.settings"})
@Import(ServiceLocalConfig.class)
public class ApplicationConfig {
	@Autowired
	private JPASettings4Hibernate jpaSettings4Hibernate;
	
	@Bean
	@UnitTestEnv
	public DataSource devDataSource(){
		return new EmbeddedDatabaseBuilder()
				.setType(EmbeddedDatabaseType.HSQL)
				.build();
	}
	
	@Bean
	@Production
	@Development
	@ConfigurationProperties(prefix="masconsolecore.datasource")
	public DataSource productionDataSource(){
		return DataSourceBuilder.create().build();
	}
	
	@Bean
	public EntityManagerFactory entityManagerFactory(DataSource dataSource){
		HibernateJpaVendorAdapter hibernateAdapter = new HibernateJpaVendorAdapter();
		hibernateAdapter.setGenerateDdl(jpaSettings4Hibernate.getGenerateDdl());
		hibernateAdapter.setShowSql(jpaSettings4Hibernate.getShowSql());
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		factoryBean.setJpaVendorAdapter(hibernateAdapter);
		factoryBean.setPackagesToScan(jpaSettings4Hibernate.getPackagesToScan().toArray(new String[this.jpaSettings4Hibernate.getPackagesToScan().size()]));
		factoryBean.setDataSource(dataSource);
		factoryBean.afterPropertiesSet();
		
		return factoryBean.getObject();
	}
	
	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf){
		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(emf);
		return txManager;
	}
}

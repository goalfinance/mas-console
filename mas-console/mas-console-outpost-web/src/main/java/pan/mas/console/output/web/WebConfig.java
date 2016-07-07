/**
 * 
 */
package pan.mas.console.output.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.permission.RolePermissionResolver;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.BeanInstantiationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.alibaba.dubbo.config.annotation.Reference;
import com.stormpath.shiro.client.ClientFactory;

import pan.mas.console.core.outpost.web.security.service.OutpostWebSecurityService;
import pan.mas.console.output.web.shiro.ApplicationRealm;
import pan.mas.console.output.web.shiro.DefaultRolePermissionResolver;
import pan.utils.security.shiro.CredentialsService;
import pan.utils.security.shiro.HashedCredentialsService;

/**
 * @author panqingrong
 *
 */
@Configuration
@EnableWebMvc

public class WebConfig extends WebMvcConfigurerAdapter {
	@Autowired(required = false)
	@Reference
	private OutpostWebSecurityService outpostWebSecurityService;

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		super.configureViewResolvers(registry);
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/jsp/");
		viewResolver.setSuffix(".jsp");
		registry.viewResolver(viewResolver);

	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		super.addResourceHandlers(registry);
		registry.addResourceHandler("**/dijit/**").addResourceLocations("/presence/");
		registry.addResourceHandler("**/frameui/**").addResourceLocations("/presence/");
		registry.addResourceHandler("**/grid/**").addResourceLocations("/presence/");
		registry.addResourceHandler("**/dojo/**").addResourceLocations("/presence/");
		registry.addResourceHandler("**/dojox/**").addResourceLocations("/presence/");
		registry.addResourceHandler("**/css/*.css").addResourceLocations("/presence/");
		registry.addResourceHandler("*.js").addResourceLocations("/presence/");
		registry.addResourceHandler("**/devoops/**").addResourceLocations("/presence/");
	}

	// private class WebCustomizer implements EmbeddedServletContainerCustomizer
	// {
	//
	// @Override
	// public void customize(ConfigurableEmbeddedServletContainer container) {
	// container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND,
	// "/WEB-INF/jsp/common/notfound.jsp"));
	// container.addErrorPages(new ErrorPage(HttpStatus.BAD_REQUEST,
	// "/WEB-INF/jsp/common/notfound.jsp"));
	// container.addErrorPages(new ErrorPage(HttpStatus.SERVICE_UNAVAILABLE,
	// "/WEB-INF/common/error.jsp"));
	// }
	//
	// }

	// @Bean
	// public EmbeddedServletContainerCustomizer containerCustomized() {
	// return new WebCustomizer();
	// }
	@Bean
	public ApplicationRealm applicationRealm(CredentialsService credentialsService) {
		ApplicationRealm realm = new ApplicationRealm(outpostWebSecurityService, credentialsService);
		RolePermissionResolver rolePermissionResolver = new DefaultRolePermissionResolver(outpostWebSecurityService);
		realm.setRolePermissionResolver(rolePermissionResolver);
		return realm;
	}

	@Bean
	//You can change the credentials-logic here.
	public CredentialsService credentialsService() {
		return new HashedCredentialsService();
	}

	@Bean
	public FilterRegistrationBean shiroFilterRegistration(ApplicationRealm applicationRealm) {
		MemoryConstrainedCacheManager cacheManager = new MemoryConstrainedCacheManager();
		// ClientFactory stormPathClientFactory = new ClientFactory();
		// ApplicationRealm applicationRealm = new ApplicationRealm();
		//
		// stormPathClientFactory.setApiKeyFileLocation("/Users/panqingrong/.stormpath/apiKey.properties");
		// stormPathClientFactory.setCacheManager(cacheManager);
		// applicationRealm.setClient(stormPathClientFactory.getInstance());
		// applicationRealm.setApplicationRestUrl("https://api.stormpath.com/v1/applications/6f5WWmpCug76oHXUaon2Nq");
		//

		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(applicationRealm);
		securityManager.setCacheManager(cacheManager);
		
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		shiroFilterFactoryBean.setLoginUrl("/bs/security/show_login");
		shiroFilterFactoryBean.setSuccessUrl("/bs/security/show_app_frame");
		// shiroFilterFactoryBean.setSuccessUrl("/index.jsp");

		LogoutFilter logoutFilter = new LogoutFilter();
		logoutFilter.setRedirectUrl("/frameui/frameui.html");
		shiroFilterFactoryBean.getFilters().put("logout", logoutFilter);
		
		Map<String, String> filterChainDefinitionMap = new HashMap<String, String>();
		filterChainDefinitionMap.put("/logout", "logout");
		filterChainDefinitionMap.put("/bs/**", "authc");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

		try {
			Filter shiroFilter = (Filter) shiroFilterFactoryBean.getObject();
			FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(shiroFilter);
			List<String> urlPatterns = new ArrayList<String>();
			urlPatterns.add("/*");
			filterRegistrationBean.setUrlPatterns(urlPatterns);
			return filterRegistrationBean;
		} catch (Exception e) {
			throw new BeanInstantiationException(FilterRegistrationBean.class,
					"A error in terms of instantiating bean occured!, errmsg:[" + e.getMessage() + "]");
		}
	}

}

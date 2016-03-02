/**
 * 
 */
package pan.mas.console.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import pan.utils.annotation.env.Development;

/**
 * @author panqingrong
 *
 */
@Configuration
@ComponentScan(basePackages={"pan.mas.console.core.authorizednetwork.service",
		"pan.mas.console.core.outpost.web.security.service"})
@Development
public class ServiceLocalConfig {

}

/**
 * 
 */
package pan.mas.console.output.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import pan.mas.console.core.ApplicationConfig;
import pan.utils.annotation.env.Development;
import pan.utils.annotation.env.UnitTestEnv;


/**
 * @author panqingrong
 *
 */
@Configuration
@Import(ApplicationConfig.class)
@Development
@UnitTestEnv
public class ServiceLocalTestConfig {

}

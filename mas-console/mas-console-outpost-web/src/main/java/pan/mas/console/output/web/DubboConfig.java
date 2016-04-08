/**
 * 
 */
package pan.mas.console.output.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import pan.utils.annotation.env.Production;

/**
 * @author panqingrong
 *
 */
@Configuration
@ImportResource("classpath:mas/console/outpost/web/config/dubbo/dubboContext.xml")
@Production
public class DubboConfig {

}

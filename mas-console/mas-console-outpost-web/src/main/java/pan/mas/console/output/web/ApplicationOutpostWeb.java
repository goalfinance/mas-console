/**
 * 
 */
package pan.mas.console.output.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import pan.mas.console.core.ApplicationConfig;

/**
 * @author panqingrong
 *
 */
@SpringBootApplication
@Import({WebConfig.class, ApplicationConfig.class})
public class ApplicationOutpostWeb {
	public static void main(String[] args){
		SpringApplication.run(ApplicationOutpostWeb.class, args);
	}

}

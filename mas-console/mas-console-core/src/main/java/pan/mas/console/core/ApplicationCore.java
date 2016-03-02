package pan.mas.console.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The entrance of the core system.
 *
 */
@SpringBootApplication
public class ApplicationCore 
{
    public static void main( String[] args )
    {
    	SpringApplication.run(ApplicationCore.class, args);
    	while(true){
    		try{
    			Thread.sleep(5000L);
    		}catch(InterruptedException e){
    			System.out.println("System is interrupted!");
    		}
    	}
    }
}

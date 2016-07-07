/**
 * 
 */
package pan.mas.console.output.web.controller.dojo.security.reception;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import pan.mas.console.output.web.service.security.reception.models.LoginBean;

/**
 * @author panqingrong
 *
 */
@RestController
@RequestMapping("dojo/security")
public class SecurityReceptionRestContoller {
	
	@RequestMapping(value="login", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void login(@RequestBody LoginBean loginBean){
		assert (loginBean.getUsername() != null && loginBean.getUsername().equals("") == false);
		assert (loginBean.getPassword() != null && loginBean.getPassword().equals("") == false);
		
		Subject currentUser = SecurityUtils.getSubject();
		if (currentUser.isAuthenticated() == false){
			UsernamePasswordToken token = new UsernamePasswordToken(loginBean.getUsername(), loginBean.getPassword());
			currentUser.login(token);
		}			
	}
	
	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<String> handleAuthenticationException(AuthenticationException e){
		ResponseEntity<String> responseEntity = new ResponseEntity<String>("You can not pass the authentication!", HttpStatus.FORBIDDEN);
		return responseEntity;
	}

}

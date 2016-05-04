/**
 * 
 */
package pan.utils.security.shiro;

import org.apache.shiro.authc.credential.CredentialsMatcher;

import pan.utils.AppBizException;

/**
 * @author panqingrong
 *
 */
public interface CredentialsService {
	
	public String encryptPassword(String password, String salt) throws AppBizException;
	
	public String generateSalt();
	
	public CredentialsMatcher getCredentialsMatcher(); 

}

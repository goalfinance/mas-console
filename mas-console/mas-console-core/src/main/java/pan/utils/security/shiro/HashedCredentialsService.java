/**
 * 
 */
package pan.utils.security.shiro;

import java.security.MessageDigest;
import java.security.SecureRandom;

import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

import pan.utils.AppBizException;
import pan.utils.AppExceptionCodes;
import pan.utils.Encodes;

/**
 * @author panqingrong
 *
 */
public class HashedCredentialsService implements CredentialsService {
	private static final String HASH_ALGORITHM = "SHA-1";
	private static final int HASH_INTERATIONS = 1024;
	private static final int SALT_SIZE = 8;
	
	private SecureRandom random = new SecureRandom();
	

	/* (non-Javadoc)
	 * @see pan.utils.security.shiro.CredentialsService#encryptPassword(java.lang.String)
	 */
	public String encryptPassword(String password, String salt) throws AppBizException{
		try {
			MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
			if (salt != null){
				digest.reset();
				digest.update(Encodes.decodeHex(salt));
			}
			byte[] result = digest.digest(password.getBytes());
			//already hashed once above, so the hash-interations was decreased 1.
			for(int i = 0; i < HASH_INTERATIONS - 1; i++){
				digest.reset();
				result = digest.digest(result);
			}
			return Encodes.encodeHex(result);
		} catch (Exception e) {
			throw new AppBizException(AppExceptionCodes.SEC_SECURITY_ALGORITHM__ERROR[0], AppExceptionCodes.SEC_SECURITY_ALGORITHM__ERROR[1]);		
		}
	}

	/* (non-Javadoc)
	 * @see pan.utils.security.shiro.CredentialsService#generateSalt(int)
	 */
	public String generateSalt() {
		byte[] bytes = new byte[SALT_SIZE];
		random.nextBytes(bytes);
		return Encodes.encodeHex(bytes);
	}

	/* (non-Javadoc)
	 * @see pan.utils.security.shiro.CredentialsService#getCredentialsMatcher()
	 */
	public CredentialsMatcher getCredentialsMatcher() {
		HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher(HASH_ALGORITHM);
		hashedCredentialsMatcher.setHashIterations(HASH_INTERATIONS);
		return hashedCredentialsMatcher;
	}

}

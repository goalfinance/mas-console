/**
 * 
 */
package pan.utils;

/**
 * @author panqingrong
 *
 */
public class AppExceptionCodes {
	
	public static final String[] UNRECOVERABLE_SYSTEM_ERROR = {"S.MAS.9999", ""};
	
	/**
	 * The authorized network does not exist in system.
	 */
	public static final String[] AUTHNET_DOES_NOT_EXIST = {"R.MAS.AUTHNET.0001", "The authorized network[authNet Sid={0}] does not exists in system."};
	
	
	
	/**
	 * The user that used to be authorized by outpost does not exist in system. 
	 */
	public static final String[] SEC_USER_DOES_NOT_EXIST = {"R.MAS.OUTPOST-WEB-SEC.0001", "The user[userId={0}]that used to be authorized by outpost does not exist in system."};
	
	/**
	 * Encountered a error when calculating security algorithm.
	 */
	public static final String[] SEC_SECURITY_ALGORITHM__ERROR = {"R.MAS.OUTPOST-WEB-SEC.0002", "Encountered a error when calculating security algorithm."};
	
	/**
	 * The old password input is not matched.
	 */
	public static final String[] SEC_NOT_MATCH_OLDPASSWORD = {"R.MAS.OUTPOST-WEB-SEC.0003", "The old password input is not matched."};

}

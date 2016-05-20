/**
 * 
 */
package pan.mas.console.output.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import pan.utils.AppBizException;
import pan.utils.AppExceptionCodes;
import pan.utils.AppRTException;

/**
 * @author panqingrong
 *
 */
@ControllerAdvice
public class CommonExceptionControllerAdvice {
	
	//Define the relationship between the app error code and http status code. 
	public static final Map<String, HttpStatus> httpStatusMapping = new HashMap<String, HttpStatus>(){{
		put(AppExceptionCodes.UNRECOVERABLE_SYSTEM_ERROR[0], HttpStatus.INTERNAL_SERVER_ERROR);
		put(AppExceptionCodes.AUTHNET_DOES_NOT_EXIST[0], HttpStatus.NOT_FOUND);
		put(AppExceptionCodes.SEC_USER_DOES_NOT_EXIST[0], HttpStatus.NOT_FOUND);
		put(AppExceptionCodes.SEC_SECURITY_ALGORITHM__ERROR[0], HttpStatus.INTERNAL_SERVER_ERROR);
		put(AppExceptionCodes.SEC_NOT_MATCH_OLDPASSWORD[0], HttpStatus.FORBIDDEN);
	}};
	
	public HttpStatus getHttpStatusCode(String errCode){
		return httpStatusMapping.get(errCode);
	}
	
	@ExceptionHandler(AppBizException.class)
	public ResponseEntity<String> handleAppBizException(AppBizException appBizException) {
		String errCode = appBizException.getCode();
		String errMsg = appBizException.getMessage();
		
		ResponseEntity<String> responseEntity = new ResponseEntity<String>(errMsg, getHttpStatusCode(errCode));
		return responseEntity;
	}
	
	@ExceptionHandler(AppRTException.class )
	public ResponseEntity<String> handleAppRTException(AppRTException appRTException) {
		String errCode = appRTException.getCode();
		String errMsg = appRTException.getMessage();
		
		ResponseEntity<String> responseEntity = new ResponseEntity<String>(errMsg, getHttpStatusCode(errCode));
		return responseEntity;
	}
}

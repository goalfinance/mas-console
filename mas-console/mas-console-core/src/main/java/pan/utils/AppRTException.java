/**
 * 
 */
package pan.utils;

/**
 * @author panqingrong
 *
 */
public class AppRTException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3942995865412084053L;

	private String code;
	private String textMessage;
	private Object[] args;
	
	private static MessageSourceService messageSourceService = new DefaultMessageSourceServiceImpl();
	
	private static String formatMessage(String msg, Object[] args){
		if (args == null || args.length == 0){
			return msg;
		}
		
		return messageSourceService.getMessage(msg, args);
	}
	
	public AppRTException(String code, String msg) {
		super(code + ": " + msg);
		this.code = code;
	}

	public AppRTException(String code, String msg, Throwable cause) {
		super(code + ": " + msg, cause);
		this.code = code;
	}

	public AppRTException(String code, Object[] args, String msg) {
		super(code + ": " + formatMessage(msg, args));
		this.code = code;
		this.args = args;
	}

	public AppRTException(String code, Object[] args, String msg, Throwable cause) {
		super(code + ": " + formatMessage(msg, args), cause);
		this.code = code;
		this.args = args;
	}

	public AppRTException(Throwable cause) {
		super(cause);
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getTextMessage() {
		return textMessage;
	}
	public void setTextMessage(String textMessage) {
		this.textMessage = textMessage;
	}
	public Object[] getArgs() {
		return args;
	}
	public void setArgs(Object[] args) {
		this.args = args;
	}
}

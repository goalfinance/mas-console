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

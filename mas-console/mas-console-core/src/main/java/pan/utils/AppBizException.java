/**
 * 
 */
package pan.utils;

/**
 * @author panqingrong
 *
 */
public class AppBizException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -498775149681055052L;
	
	private String code;
	private String textMessage;
	private Object[] args;
	
	
	public AppBizException(String code) {
		super();
		this.code = code;
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

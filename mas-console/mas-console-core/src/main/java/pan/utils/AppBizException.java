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
	
	private static MessageSourceService messageSourceService = new DefaultMessageSourceServiceImpl();
	
	private static String formatMessage(String msg, Object[] args){
		if (args == null || args.length == 0){
			return msg;
		}
		
		return messageSourceService.getMessage(msg, args);
	}
	
	public AppBizException(String code, String msg) {
		super(code + ": " + msg);
		this.code = code;
	}

	public AppBizException(String code, String msg, Throwable cause) {
		super(code + ": " + msg, cause);
		this.code = code;
	}

	public AppBizException(String code, Object[] args, String msg) {
		super(code + ": " + formatMessage(msg, args));
		this.code = code;
		this.args = args;
	}

	public AppBizException(String code, Object[] args, String msg, Throwable cause) {
		super(code + ": " + formatMessage(msg, args), cause);
		this.code = code;
		this.args = args;
	}

	public AppBizException(String code, String msg, Object... args) {
		super(code + ": " + formatMessage(msg, args));
		this.code = code;
		this.args = args;
	}

	public AppBizException(String code, Throwable cause, String msg, Object... args) {
		super(code + ": " + formatMessage(msg, args), cause);
		this.code = code;
		this.args = args;
	}

	public AppBizException(Throwable cause) {
		super(cause);
	}
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

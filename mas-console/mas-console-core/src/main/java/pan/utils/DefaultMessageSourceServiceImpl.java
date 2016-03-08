/**
 * 
 * 
 */
package pan.utils;

import java.util.Locale;

import org.springframework.context.support.MessageSourceSupport;

/**
 * @author panqingrong
 * 
 */
public class DefaultMessageSourceServiceImpl extends MessageSourceSupport implements MessageSourceService {

	/* (non-Javadoc)
	 * @see pan.utils.MessageSourceService#getMessage(java.lang.String, java.lang.Object[])
	 */
	public String getMessage(String msg, Object[] args) {
		return this.renderDefaultMessage(msg, args, Locale.getDefault());
	}

}

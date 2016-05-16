/**
 * 
 */
package pan.mas.console.output.web.controller.security.maintaining.beans;

import java.io.Serializable;

/**
 * @author panqingrong
 *
 */
public class ChangePasswordBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6302933452121026028L;
	
	private String oldPassword;
	
	private String newPassword;
	
	private String confirmNewPassword;

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmNewPassword() {
		return confirmNewPassword;
	}

	public void setConfirmNewPassword(String confirmNewPassword) {
		this.confirmNewPassword = confirmNewPassword;
	}
	

}

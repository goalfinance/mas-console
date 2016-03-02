/**
 * 
 */
package pan.mas.console.output.web.controller.commons;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author panqingrong
 *
 */
@Controller
@RequestMapping("/commons")
public class CommonViewController {
	
	@RequestMapping(value="show_error_message")
	public String showError(@RequestParam(name="errmsg", required=true)String errmsg, Model model){
		model.addAttribute("errMessageText", errmsg);
		return "commons/ShowErrorMessage";
	}

}

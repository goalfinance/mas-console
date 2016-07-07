/**
 * 
 */
package pan.mas.console.output.web.controller.dojo.authnet;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
;

/**
 * @author panqingrong
 *
 */
@Controller
@RequestMapping("dojo/authnet")
public class AuthNetViewController {
	@RequestMapping(value = "list_auth_nets")
	public String showAuthNets() {
		return "dojo/authnet/ListAuthNet";
	}
	
	@RequestMapping(value="show_add_form")
	public String showAddForm(){
		return "dojo/authnet/AddAuthNet";
	}
	
	@RequestMapping(value="show_view_form")
	public String showViewForm(@RequestParam(name="sId", required=true) String sId, Model model){
		model.addAttribute("sId", sId);
		return "dojo/authnet/ViewAuthNet";
	}

}

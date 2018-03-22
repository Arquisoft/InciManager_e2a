package manager.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
	
	@RequestMapping(value = "/formAgentHistory")
	public String formAgentHistory() {
		return "formAgentHistory";
	}

	@RequestMapping(value = "/actionAgentHistory")
	public String actionAgentHistory() {
		return "actionAgentHistory";
	}

}

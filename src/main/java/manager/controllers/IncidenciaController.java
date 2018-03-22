package manager.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IncidenciaController {
	
	@RequestMapping(value = "/formSendIncidence")
	public String formSendIncidence() {
		return "formSendIncidence";
	}

	@RequestMapping(value = "/actionSendIncidence")
	public String actionSendIncidence() {
		return "actionSendIncidence";
	}

}

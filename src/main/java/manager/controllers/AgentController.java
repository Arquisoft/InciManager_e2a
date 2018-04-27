package manager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import manager.entities.Agent;
import manager.services.AgentsService;
import manager.services.IncidenciaService;

@Controller
public class AgentController {

	@Autowired
	private IncidenciaService incidenciaService;

	@Autowired
	private AgentsService agentsService;

	@RequestMapping(value = "/index", method = RequestMethod.POST)
	public String showInfo(@RequestParam String login, @RequestParam String password,
			@RequestParam String kind, Model model) {
		Agent a = agentsService.findAgent(login, password, kind);
		incidenciaService.setAgent(a);
		if (a != null)
			return "index";
		return "error";
	}
}
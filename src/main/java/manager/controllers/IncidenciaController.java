package manager.controllers;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import manager.entities.IncidenciaMin;
import manager.validators.IncidenciaValidator;

@Controller
public class IncidenciaController {
	
	@Autowired 
	private IncidenciaValidator incidenciaValidator;
	
	@RequestMapping(value = "/formSendIncidence")
	public String formSendIncidence(Model modelo) {
		modelo.addAttribute("incidenciaMin", new IncidenciaMin());
		return "formSendIncidence";
	}
	
	@RequestMapping(value = "/formSendIncidence", method = RequestMethod.POST)
	public String setUser(@ModelAttribute @Validated IncidenciaMin incidencia, 
			BindingResult result, Model modelo) {
		
		incidenciaValidator.validate(incidencia, result); 
		if (result.hasErrors()) { 
			return "formSendIncidence"; 
		}
		
		//Añadir Incidencia a la bbdd.
		
		return "incidenciaEnviada";
	}
	
	@RequestMapping(value = "/list")
	public String listIncidence(HttpSession session, Model modelo) {
		//Agent agent = (Agent) session.getAttribute("agentIncidence");	
		return "list";
	}
}

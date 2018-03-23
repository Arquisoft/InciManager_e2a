package manager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import manager.entities.Incidencia;
import manager.validators.IncidenciaValidator;

@Controller
public class IncidenciaController {
	
	@Autowired 
	private IncidenciaValidator incidenciaValidator;
	
	@RequestMapping(value = "/formSendIncidence")
	public String formSendIncidence(Model modelo) {
		modelo.addAttribute("incidencia", new Incidencia());
		return "formSendIncidence";
	}
	
	@RequestMapping(value = "/formSendIncidence", method = RequestMethod.POST)
	public String setUser(@ModelAttribute @Validated Incidencia incidencia, 
			BindingResult result, Model modelo) {
		
		incidenciaValidator.validate(incidencia, result); 
		
		if (result.hasErrors()) { 
			return "formSendIncidence"; 
		}
		//user.setRole(rolesService.getRoles()[0]);
		//usersService.addUser(user);
		//securityService.autoLogin(user.getEmail(), user.getPasswordConfirm());
		return "redirect:incidenciaEnviada";
	}

}

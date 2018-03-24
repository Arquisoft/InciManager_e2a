package manager.controllers;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import manager.agents.GetAgent;
import manager.dbManagement.AgentDAO;
import manager.entities.Agent;
import manager.util.AgentMin;


@Controller
public class UserController {
	
	@Autowired
	private GetAgent getAgent;
	
	@Autowired
	private AgentDAO ad;
	
	@RequestMapping(value = "/index", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<AgentMin> getAgent(@RequestBody Map<String, Object> payload) {
		String login = (String) payload.get("login");
		String password = (String) payload.get("password");
		String kind = (String) payload.get("kind");
		AgentMin c = getAgent.getAgentsInfo(login, password, kind);
		if (c == null) {
			return new ResponseEntity<AgentMin>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<AgentMin>(c, HttpStatus.OK);
	}

	
	/**
	 * Recibe los datos de login del usuario, busca si exite ese usuario y en caso
	 * de exitir pasa a la siguiente página que muestra la informacion en caso
	 * contrario muestra la página de error
	 * 
	 * @param session
	 *            mantiene la sesion
	 * @param login
	 *            nombre del login
	 * @param password
	 *            contresena del usuario
	 * @param model
	 * @return view si exito, error si fracaso
	 */
	@RequestMapping(value = "/index", method = RequestMethod.POST)
	public String showInfo(HttpSession session, @RequestParam String login, @RequestParam String password,
			@RequestParam String kind, Model model) {
		Agent c = null;
		if (login != null && password != null && kind != null) {
			c = ad.getAgent(login, password, kind);
			if (c != null) {
				session.setAttribute("agent", c);
				model.addAttribute("resultado", "Bienvenid@ " + c.getNombre());
				return "index";
			}
		}
		return "error";
	}


}

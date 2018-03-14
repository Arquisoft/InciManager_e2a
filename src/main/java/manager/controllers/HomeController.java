package manager.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	@RequestMapping(value = "/sendIncidence")
	public String sendIncidence() {
		return "sendIncidence";
	}
	
	@RequestMapping(value = "/seeHistorical")
	public String seeHistorical() {
		return "seeHistorical";
	}
	
	@RequestMapping(value = "/createIncidence", method = RequestMethod.POST)
	public String createIncidence() { // Aqui faltarian parametros
		// Aqui faltaria leer los datos y ver si son validos
		return ""; 
		/* 
		 * Aqui falta reenviar en funcion de si se ha hecho la incidencia de forma correcta
		 * a una pagina de acierto o a una de fallo  en caso contrario
		 */
	}
}
package steps;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import manager.InciManagerApplication;
import manager.controllers.AgentController;
import manager.entities.Agent;
import manager.entities.Incidencia;
import manager.entities.IncidenciaMin;
import manager.services.AgentsService;
import manager.services.IncidenciaService;


@ContextConfiguration(classes=InciManagerApplication.class, loader=SpringApplicationContextLoader.class)
@IntegrationTest
@WebAppConfiguration
@ActiveProfiles("test")
public class LoginStep {
	
	@Autowired
	IncidenciaService iService;
	IncidenciaMin i ;
	@Autowired
	AgentsService aService;
	Agent agente ;
	@Autowired
	AgentController aControler;

  

	
	@Then("^El agente podra enviar incidencias")
	public void El_agente_podra_enviar_incidencias() throws Throwable {
		i = new IncidenciaMin("hola" , "hola" , "hola" , "holA:adios");
		i.setLatitud(7.9);
		i.setLongitud(7.9);
		Incidencia incidence = iService.addIncidencia(i);
		Assert.notNull(incidence);
		
		
	}
	
	@Given("^un Agente \"([^\"]*)\" Y contraseña \"([^\"]*)\" Y tipo \"([^\"]*)\"$")
	public void un_Agente_Y_contraseña_Y_tipo(String name , String password, String kind) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
Agent agent = new Agent();
		
		String nombreUsuario = "Pepe";
		Long kindcode = 1L;
		String dni = "345567678G";
		String nombre = "Daniel";
		String apellidos = "Suarez";
		String email = "danisua@uniovi.es";
		agent.setUsername(nombreUsuario);
		agent.setPassword(password);
		agent.setKind(kind);
		agent.setKindCode(kindcode);
		agent.setDni(dni);
		agent.setNombre(nombre);
		agent.setApellidos(apellidos);
		agent.setEmail(email);
		aService.addAgent(agent);
		
		String agente = aControler.showInfo(name, password, kind, null);
		assertEquals("index", agente);
	}

	
	

}
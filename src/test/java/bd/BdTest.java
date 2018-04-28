package bd;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import manager.InciManagerApplication;
import manager.entities.Agent;
import manager.entities.Incidencia;
import manager.repository.AgentsRepository;
import manager.services.AgentsService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = InciManagerApplication.class)
@WebAppConfiguration
@IntegrationTest
@ActiveProfiles("test")
public class BdTest {
	
	@Autowired
	AgentsService agentService;
	
	@Autowired
	AgentsRepository agentsRepository;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAñadirAgente() {
		
		Agent agent = new Agent();
		
		String nombreUsuario = "pepe";
		String password = "123";
		String kind = "Entity";	
		Long kindcode = 1L;
		String dni = "12345678P";
		String nombre = "Pelayo";
		String apellidos = "García";
		String email = "pelayo@uniovi.es";
		
		Incidencia i1 = new Incidencia();
		Incidencia i2 = new Incidencia();
		
		agent.getIncidencias().add(i1);
		agent.getIncidencias().add(i2);
		
		agent.setUsername(nombreUsuario);
		agent.setPassword(password);
		agent.setKind(kind);
		agent.setKindCode(kindcode);
		agent.setDni(dni);
		agent.setNombre(nombre);
		agent.setApellidos(apellidos);
		agent.setEmail(email);
		
		agentService.addAgent(agent);
		Agent a = agentsRepository.findAgent(nombreUsuario, password, kind);
		assertNotNull(a);
		agentsRepository.delete(a.getId());
		assertNull(agentsRepository.findAgent(nombreUsuario, password, kind));
	}

}

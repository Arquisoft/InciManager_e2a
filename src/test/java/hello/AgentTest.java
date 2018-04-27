package hello;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import manager.entities.Agent;
import manager.entities.Incidencia;

public class AgentTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void newAgent() {
		Agent agent = new Agent();
		
		Long id = 2L;
		
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
		
		agent.setId(id);
		agent.setUsername(nombreUsuario);
		agent.setPassword(password);
		agent.setKind(kind);
		agent.setKindCode(kindcode);
		agent.setDni(dni);
		agent.setNombre(nombre);
		agent.setApellidos(apellidos);
		agent.setEmail(email);
		
		assertEquals(id, agent.getId());
		assertEquals(nombreUsuario, agent.getUsername());
		assertEquals(password, agent.getPassword());
		assertEquals(kind, agent.getKind());
		assertEquals(kindcode, agent.getKindCode());
		assertEquals(dni, agent.getDni());
		assertEquals(nombre, agent.getNombre());
		assertEquals(apellidos, agent.getApellidos());
		assertEquals(email, agent.getEmail());
		
	}

}
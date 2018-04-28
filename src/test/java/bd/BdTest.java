package bd;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
import manager.entities.Campo;
import manager.entities.Etiqueta;
import manager.entities.Incidencia;
import manager.entities.IncidenciaMin;
import manager.repository.AgentsRepository;
import manager.repository.EtiquetasRepository;
import manager.services.AgentsService;
import manager.services.CampoService;
import manager.services.EtiquetaService;
import manager.services.IncidenciaService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = InciManagerApplication.class)
@WebAppConfiguration
@IntegrationTest
@ActiveProfiles("test")
public class BdTest {
	
	@Autowired
	AgentsService agentService;
	@Autowired
	IncidenciaService incidenciaService;
	@Autowired
	EtiquetaService etiquetasService;
	@Autowired
	CampoService campoService;
	
	@Autowired
	AgentsRepository agentsRepository;
	@Autowired
	private EtiquetasRepository etiquetasRepository;
	
	

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAñadirAgente() {
		
		Agent agent = new Agent();
		
		String nombreUsuario = "Dani123";
		String password = "123";
		String kind = "Person";	
		Long kindcode = 1L;
		String dni = "345567678G";
		String nombre = "Daniel";
		String apellidos = "Suarez";
		String email = "danisua@uniovi.es";
		
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
		agentsRepository.delete(agent.getId());
		assertNull(agentsRepository.findAgent(nombreUsuario, password, kind));
	}
	
	@Test
	public void testAñadirIncidencia() {
		IncidenciaMin im = new IncidenciaMin();
		
		im.setNombre("Fuego en Calle Uría");
		im.setDescripcion("Incendiose la calle");
		im.setEtiqueta("Fuego, Agua");
		im.setCampo("Temperatura:45");
		im.setLatitud(23.362598);
		im.setLongitud(-32.23658);
		
		Agent agent = new Agent();
		
		agent.setUsername("pepe");
		agent.setPassword("123");
		agent.setKind("Entity");
		agent.setKindCode(1L);
		agent.setDni("12345678P");
		agent.setNombre("Pelayo");
		agent.setApellidos("García");
		agent.setEmail("pelayo@uniovi.es");
		
		agentsRepository.save(agent);
		
		incidenciaService.setAgent(agent);
		assertEquals(agent, incidenciaService.getAgent());
		
		Incidencia incidenciaEnviada = incidenciaService.addIncidencia(im);
		
		//Comprobamos que la incidencia fue añadida 
		assertNotNull(incidenciaService.getIncidencia(incidenciaEnviada.getId()));
		
		//Obtenemos las etiquetas que fueron añadidas 
		Set<Etiqueta> etiquetas = incidenciaService.cogerEtiquetas(im.getEtiqueta(), incidenciaEnviada);
		List<Etiqueta> lista = new ArrayList<Etiqueta>();
		lista.addAll(etiquetas);
		
		List<Etiqueta> lista2 = etiquetasService.obtenerEtiquetas(incidenciaEnviada.getId());
		assertNotNull(lista2);
		
		//Comprobamos que las listas de etiquetas tienen el mismo tamaño y mismas etiquetas.
		assertTrue(lista.size() == lista2.size());
		
		for(int i = 0; i<lista.size(); i++) {
			assertTrue(lista.get(i).getNombre().equals(lista2.get(i).getNombre()));
		}
		
		//Obtenemos los campos que fueron añadidos
		Set<Campo> campos = incidenciaService.cogerCampos(im.getCampo(), incidenciaEnviada);
		List<Campo>listaCampos = new ArrayList<Campo>();
		listaCampos.addAll(campos);
		
		List<Campo> listaCampos2 = campoService.obtenerCampos(incidenciaEnviada.getId());
		assertNotNull(listaCampos2);
		
		//Comprobamos que las listas de etiquetas tienen el mismo tamaño y mismas etiquetas.
		assertTrue(listaCampos.size() == listaCampos2.size());
				
		for(int i = 0; i<listaCampos.size(); i++) {
			assertTrue(listaCampos.get(i).getValor().equals(listaCampos2.get(i).getValor()));
		}
		
	}

}

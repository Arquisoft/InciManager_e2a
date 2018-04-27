package hello;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import manager.entities.Etiqueta;
import manager.entities.Incidencia;

public class EtiquetaTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {

		Etiqueta e = new Etiqueta();
		
		Long id = 1L;
		String valor = "123456";
		Incidencia i = new Incidencia();
		
		e.setId(id);
		e.setIncidencia(i);
		e.setNombre(valor);
		
		assertEquals(id, e.getId());
		assertEquals(i, e.getIncidencia());
		assertEquals(valor, e.getNombre());
		
		assertEquals(valor, e.toString());
		
	}

}

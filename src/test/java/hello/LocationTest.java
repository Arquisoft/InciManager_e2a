package hello;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import manager.entities.Incidencia;
import manager.entities.Location;

public class LocationTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		Location l = new Location();
		
		Long id = 1L;
		double latitud = -32.236528;
		double longitud = 32.236256;
		Incidencia i = new Incidencia();
		
		l.setId(id);
		l.setIncidencia(i);
		l.setLatitud(latitud);
		l.setLongitud(longitud);
		
		assertEquals(id, l.getId());
		assertEquals(i, l.getIncidencia());
		assertEquals(latitud, l.getLatitud(), 000000);
		assertEquals(longitud, l.getLongitud(), 000000);
		
		assertEquals(latitud + "$" + longitud, l.toString());
	}

}

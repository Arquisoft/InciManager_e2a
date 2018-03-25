package hello;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

import manager.entities.Agent;
import manager.entities.Campo;
import manager.entities.Etiqueta;
import manager.entities.Incidencia;
import manager.entities.Location;

public class IncidenciaTest {

	@Test
	public void ToStringKafkatest() {
		Date fecha = new Date();
		Incidencia inc = new Incidencia();
		Set<Etiqueta> etiquetas = new HashSet<Etiqueta>();
		etiquetas.add(new Etiqueta().setNombre("Fuego"));
		etiquetas.add(new Etiqueta().setNombre("Parque"));
		Set<Campo> campos = new HashSet<Campo>();
		campos.add(new Campo().setClave("Fuego").setValor("Extremo"));
		campos.add(new Campo().setClave("Temperatura").setValor("Alta"));
		inc.setAgent(new Agent("Juan")).setNombre("Fuego en Oviedo")
				.setDescripcion("El parque San Francisco está quemándose a causa de un cigarrillo mal apagado")
				.setLocalizacion(new Location(43.3616142, -5.8506767)).setEtiquetas(etiquetas).setCampos(campos)
				.setFecha(fecha).setId((long) 1);
		assertEquals("Juan@Fuego en Oviedo@El parque San Francisco está quemándose a causa de un cigarrillo mal apagado"
				+ "@43.3616142$-5.8506767@Fuego$Parque@Temperatura:Alta$Fuego:Extremo@" + fecha.getTime() + "@1",
				inc.toString());
		System.err.println(inc);
	}
}
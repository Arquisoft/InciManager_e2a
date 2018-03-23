package manager.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import manager.entities.Campo;
import manager.entities.Etiqueta;
import manager.entities.Incidencia;
import manager.entities.IncidenciaMin;
import manager.entities.Location;
import manager.entities.User;

@Service
public class IncidenciaService {

	public void addIncidencia(IncidenciaMin incidencia) {
		Set<Etiqueta> etiquetas = cogerEtiquetas(incidencia.getEtiqueta());
		Set<Campo> campos = cogerCampos(incidencia.getCampo());
		Location location = new Location();
		Incidencia inc = new Incidencia(new User(incidencia.getNombreUsuario(), incidencia.getContraseña()),
				incidencia.getNombre(), incidencia.getDescripcion(), location, etiquetas, campos);
		// determinar bbdd
	}

	public Set<Campo> cogerCampos(String campo) {
		Set<Campo> c = new HashSet<Campo>();
		if (campo.equals(""))
			return c;
		String[] campos = campo.split(",");
		for (String s : campos)
			c.add(new Campo().setClave(s.split(":")[0]).setValor(s.split(":")[1]));
		return c;
	}

	public Set<Etiqueta> cogerEtiquetas(String etiqueta) {
		Set<Etiqueta> e = new HashSet<Etiqueta>();
		if (etiqueta.equals("")) {
			return e;
		}
		String[] etiquetas = etiqueta.split(",");
		for (String s : etiquetas)
			e.add(new Etiqueta().setNombre(s));
		return e;
	}
}
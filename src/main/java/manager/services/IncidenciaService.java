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
		Set<Etiqueta> etiquetas = new HashSet<Etiqueta>();
		Set<Campo> campos = new HashSet<Campo>();
		Location location = new Location();
		Incidencia inc = new Incidencia(new User(incidencia.getNombreUsuario(), incidencia.getContraseña()),
				incidencia.getNombre(), incidencia.getDescripcion(), location, etiquetas, campos);
		// determinar bbdd
	}

}
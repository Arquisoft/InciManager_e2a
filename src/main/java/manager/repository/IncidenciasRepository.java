package manager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import manager.entities.Incidencia;

public interface IncidenciasRepository extends CrudRepository<Incidencia, Long> {

	@Query("SELECT i FROM Incidencia i WHERE i.agent.id = ?1")
	List<Incidencia> incidenciasAgente(Long id);

	// @Query("SELECT i FROM Incidencia i WHERE i.agent = ?1 AND i.nombre = ?2 AND
	// i.descripcion = ?3 AND i.location = ?4")
	// Incidencia sacarIdDeIncidencia(Agent agent, String nombre, String
	// descripcion, Location location);
}
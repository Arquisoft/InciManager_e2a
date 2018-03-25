package manager.repository;

import org.springframework.data.repository.CrudRepository;

import manager.entities.Incidencia;

public interface IncidenciasRepository extends CrudRepository<Incidencia, Long> {
}
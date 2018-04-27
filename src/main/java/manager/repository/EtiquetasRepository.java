package manager.repository;

import org.springframework.data.repository.CrudRepository;

import manager.entities.Etiqueta;

public interface EtiquetasRepository extends CrudRepository <Etiqueta, Long> {

}
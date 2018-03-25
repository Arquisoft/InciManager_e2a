package manager.repository;

import org.springframework.data.repository.CrudRepository;

import manager.entities.Campo;

public interface CamposInterface extends CrudRepository<Campo, Long> {

}
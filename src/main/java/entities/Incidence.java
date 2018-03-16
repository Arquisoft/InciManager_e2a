package entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Incidence {
	
	@ManyToOne()
	private User user;
	
	private String nombre;
	private String descripcion;

}

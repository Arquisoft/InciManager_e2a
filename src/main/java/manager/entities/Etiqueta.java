package manager.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Etiqueta {
	
	@Id @GeneratedValue Long id;
	
	@ManyToOne
	private Incidencia incidencia;
	
	private String nombre;
	
	public Etiqueta() {
		
	}

	public Incidencia getIncidencia() {
		return incidencia;
	}

	public void setIncidencia(Incidencia incidencia) {
		this.incidencia = incidencia;
	}
	
	

}

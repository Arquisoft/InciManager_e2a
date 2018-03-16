package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints=@UniqueConstraint(columnNames= {
		"incidencia", "clave"
}))
public class Campo {
	
	@Id @GeneratedValue Long id;
	
	@ManyToOne
	private Incidencia incidencia;
	
	private String clave;
	private String valor;
	
	public Campo() {
		
	}
	
	public Campo(String clave, String valor) {
		this.clave = clave;
		this.valor = valor;
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Incidencia getIncidencia() {
		return incidencia;
	}
	
	public void setIncidencia(Incidencia incidencia) {
		this.incidencia = incidencia;
	}
	
	public String getClave() {
		return clave;
	}
	
	public void setClave(String clave) {
		this.clave = clave;
	}
	
	public String getValor() {
		return valor;
	}
	
	public void setValor(String valor) {
		this.valor = valor;
	}
	
	

}

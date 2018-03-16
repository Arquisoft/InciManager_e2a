package entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Usuario {
	
	@Id @GeneratedValue Long id;
	
	//Propiedades del usuario
	@Column(unique=true)
	private String username;
	private String password;
	
	@OneToMany(mappedBy="user")
	Set<Incidencia> incidencias = new HashSet<Incidencia>();
	
	public Usuario(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public Usuario() {
		
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Incidencia> getIncidencias() {
		return incidencias;
	}

	public void setIncidencias(Set<Incidencia> incidencias) {
		this.incidencias = incidencias;
	}
	
	

}

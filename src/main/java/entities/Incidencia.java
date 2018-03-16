package entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import enumeradoIncidencia.EstadoIncidencia;

@Entity
public class Incidencia {
	
	@Id @GeneratedValue Long id;
	
	@ManyToOne
	private Usuario user;
	
	private String nombre;
	private String descripcion;
	
	private String localizacion;
	
	private Set<String> etiquetas = new HashSet<String>();
	
	@OneToMany(mappedBy="incidencia")
	private Set<Campo> campos = new HashSet<Campo>(); //propiedad/valor
	
	@Enumerated(EnumType.STRING)
	private EstadoIncidencia estado;
	
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date caducidad;
	
	private String entidadAsignada;
	private String comentarioOperario;
	
	public Incidencia() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getLocalizacion() {
		return localizacion;
	}

	public void setLocalizacion(String localizacion) {
		this.localizacion = localizacion;
	}

	public Set<String> getEtiquetas() {
		return etiquetas;
	}

	public void setEtiquetas(Set<String> etiquetas) {
		this.etiquetas = etiquetas;
	}

	public Set<Campo> getCampos() {
		return campos;
	}

	public void setCampos(Set<Campo> campos) {
		this.campos = campos;
	}

	public EstadoIncidencia getEstado() {
		return estado;
	}

	public void setEstado(EstadoIncidencia estado) {
		this.estado = estado;
	}

	public Date getCaducidad() {
		return caducidad;
	}

	public void setCaducidad(Date caducidad) {
		this.caducidad = caducidad;
	}

	public String getEntidadAsignada() {
		return entidadAsignada;
	}

	public void setEntidadAsignada(String entidadAsignada) {
		this.entidadAsignada = entidadAsignada;
	}

	public String getComentarioOperario() {
		return comentarioOperario;
	}

	public void setComentarioOperario(String comentarioOperario) {
		this.comentarioOperario = comentarioOperario;
	}
	
	

}

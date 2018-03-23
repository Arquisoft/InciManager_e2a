package manager.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import manager.enumeradoIncidencia.EstadoIncidencia;

@Entity
public class Incidencia {

	@Id
	@GeneratedValue
	Long id;

	@ManyToOne
	private User user;

	private String nombre;
	private String descripcion;

	@OneToOne
	private Location localizacion;

	@OneToMany(mappedBy = "incidencia")
	private Set<Etiqueta> etiquetas = new HashSet<Etiqueta>();

	@OneToMany(mappedBy = "incidencia")
	private Set<Campo> campos = new HashSet<Campo>();

	@Enumerated(EnumType.STRING)
	private EstadoIncidencia estado;

	@Temporal(value = TemporalType.TIMESTAMP)
	private Date fecha;

	private String entidadAsignada;
	private String comentarioOperario;

	public Incidencia() {
	}

	public Incidencia(User user, String nombre, String descripcion, Location localizacion, Set<Etiqueta> etiquetas,
			Set<Campo> campos) {
		super();
		this.user = user;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.localizacion = localizacion;
		this.etiquetas = etiquetas;
		this.campos = campos;
		this.estado = EstadoIncidencia.ABIERTA;
		fecha = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
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

	public Location getLocalizacion() {
		return localizacion;
	}

	public void setLocalizacion(Location localizacion) {
		this.localizacion = localizacion;
	}

	public Set<Etiqueta> getEtiquetas() {
		return etiquetas;
	}

	public void setEtiquetas(Set<Etiqueta> etiquetas) {
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
		return fecha;
	}

	public void setCaducidad(Date caducidad) {
		this.fecha = caducidad;
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
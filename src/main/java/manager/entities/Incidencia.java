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
	private Agent user;

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

	public Incidencia(String nombre, String descripcion, Location localizacion, Set<Etiqueta> etiquetas,
			Set<Campo> campos) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.localizacion = localizacion;
		this.etiquetas = etiquetas;
		this.campos = campos;
		this.estado = EstadoIncidencia.ABIERTA;
		fecha = new Date();
	}

	@Override
	public String toString() {
		StringBuilder kafka = new StringBuilder();
		kafka.append(user.getUsername() + "@");
		kafka.append(nombre + "@");
		kafka.append(descripcion + "@");
		kafka.append(localizacion.toString() + "@");
		String tmp = "";
		for (Etiqueta e : etiquetas)
			tmp += e.toString() + "$";
		kafka.append(tmp.substring(0, tmp.length() - 1) + "@");
		tmp = "";
		for (Campo c : campos)
			tmp += c.toString() + "$";
		kafka.append(tmp.substring(0, tmp.length() - 1) + "@");
		kafka.append(fecha.getTime());
		return kafka.toString();
	}

	public Long getId() {
		return id;
	}

	public Incidencia setId(Long id) {
		this.id = id;
		return this;
	}

	public Agent getUser() {
		return user;
	}

	public Incidencia setUser(Agent user) {
		this.user = user;
		return this;
	}

	public String getNombre() {
		return nombre;
	}

	public Incidencia setNombre(String nombre) {
		this.nombre = nombre;
		return this;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public Incidencia setDescripcion(String descripcion) {
		this.descripcion = descripcion;
		return this;
	}

	public Location getLocalizacion() {
		return localizacion;
	}

	public Incidencia setLocalizacion(Location localizacion) {
		this.localizacion = localizacion;
		return this;
	}

	public Set<Etiqueta> getEtiquetas() {
		return etiquetas;
	}

	public Incidencia setEtiquetas(Set<Etiqueta> etiquetas) {
		this.etiquetas = etiquetas;
		return this;
	}

	public Set<Campo> getCampos() {
		return campos;
	}

	public Incidencia setCampos(Set<Campo> campos) {
		this.campos = campos;
		return this;
	}

	public EstadoIncidencia getEstado() {
		return estado;
	}

	public Incidencia setEstado(EstadoIncidencia estado) {
		this.estado = estado;
		return this;
	}

	public Date getFecha() {
		return fecha;
	}

	public Incidencia setFecha(Date fecha) {
		this.fecha = fecha;
		return this;
	}

	public String getEntidadAsignada() {
		return entidadAsignada;
	}

	public Incidencia setEntidadAsignada(String entidadAsignada) {
		this.entidadAsignada = entidadAsignada;
		return this;
	}

	public String getComentarioOperario() {
		return comentarioOperario;
	}

	public Incidencia setComentarioOperario(String comentarioOperario) {
		this.comentarioOperario = comentarioOperario;
		return this;
	}
}
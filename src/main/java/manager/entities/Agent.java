package manager.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "agent")
public class Agent {

	@Id
	@GeneratedValue
	Long id;

	// Propiedades del usuario
	@Column(unique = true)
	private String nombreUsuario;

	@Column(name = "contrasena")
	private String password;

	@NotNull
	private String kind;

	@NotNull
	private Long kindCode;

	@NotNull
	@Column(unique = true)
	private String dni;

	@NotNull
	private String nombre;

	@NotNull
	private String apellidos;

	@NotNull
	private String email;

	@OneToMany(mappedBy = "user")
	Set<Incidencia> incidencias = new HashSet<Incidencia>();

	public Agent(String username, String password) {
		this(username);
		this.password = password;
	}

	public Agent(String username) {
		this.nombreUsuario = username;
	}

	public Agent() {

	}

	public Agent(String contrasena, String nombreUsuario, String kind, long kindCode, String dni, String nombre,
			String apellidos, String email) {
		this(nombreUsuario, contrasena);
		this.kind = kind;
		this.kindCode = kindCode;
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
	}

	public String getUsername() {
		return nombreUsuario;
	}

	public void setUsername(String username) {
		this.nombreUsuario = username;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public Long getKindCode() {
		return kindCode;
	}

	public void setKindCode(Long kindCode) {
		this.kindCode = kindCode;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Agent [nombreUsuario=" + nombreUsuario + ", password=" + password + ", kind=" + kind + ", kindCode="
				+ kindCode + ", dni=" + dni + ", nombre=" + nombre + ", apellidos=" + apellidos + ", email=" + email
				+ "]";
	}
	
	

}

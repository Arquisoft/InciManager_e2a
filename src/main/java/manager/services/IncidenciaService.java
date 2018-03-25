package manager.services;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import manager.entities.Agent;
import manager.entities.Campo;
import manager.entities.Etiqueta;
import manager.entities.Incidencia;
import manager.entities.IncidenciaMin;
import manager.entities.Location;
import manager.kafka.KafkaProducer;
import manager.repository.AgentsRepository;
import manager.repository.IncidenciasRepository;

@Service
public class IncidenciaService {

	private Agent agent;

	@Autowired
	private IncidenciasRepository incidenciasRepository;

	@Autowired
	private AgentsRepository agentsRepository;

	@Autowired
	private KafkaProducer kafkaProducer;

	public void addIncidencia(IncidenciaMin incidencia) {
		Incidencia inc = new Incidencia();
		Set<Etiqueta> etiquetas = cogerEtiquetas(incidencia.getEtiqueta(), inc);
		Set<Campo> campos = cogerCampos(incidencia.getCampo(), inc);
		Location location = new Location(incidencia.getLatitud(), incidencia.getLongitud()).setIncidencia(inc);
		Agent a = agentsRepository.findAgent(agent.getUsername(), agent.getPassword(), agent.getKind());
		inc.setNombre(incidencia.getNombre()).setDescripcion(incidencia.getDescripcion()).setLocalizacion(location)
				.setEtiquetas(etiquetas).setCampos(campos).setAgent(a).setEstado("ABIERTA").setFecha(new Date());
		a.getIncidencias().add(inc);
		agentsRepository.save(a);
//		Incidencia fin = incidenciasRepository.sacarIdDeIncidencia(inc.getAgent(), inc.getNombre(),
//				inc.getDescripcion(), inc.getLocalizacion());
		String s = inc.toString();
		kafkaProducer.send("incidencias", s);
		// incidenciasRepository.save(inc);
	}

	public Set<Campo> cogerCampos(String campo, Incidencia incidencia) {
		Set<Campo> c = new HashSet<Campo>();
		if (campo.equals(""))
			return c;
		String[] campos = campo.split(",");
		for (String s : campos)
			c.add(new Campo().setClave(s.split(":")[0]).setValor(s.split(":")[1]).setIncidencia(incidencia));
		return c;
	}

	public Set<Etiqueta> cogerEtiquetas(String etiqueta, Incidencia incidencia) {
		Set<Etiqueta> e = new HashSet<Etiqueta>();
		if (etiqueta.equals("")) {
			return e;
		}
		String[] etiquetas = etiqueta.split(",");
		for (String s : etiquetas)
			e.add(new Etiqueta().setNombre(s).setIncidencia(incidencia));
		return e;
	}

	public List<Incidencia> getIncidencias(Agent agent) {
		List<Incidencia> incidencias = incidenciasRepository.incidenciasAgente(agent.getId());
		return incidencias;
	}

	/**
	 * Devuelve la incidencia por id
	 * 
	 * @param id
	 * @return
	 */
	public Incidencia getIncidencia(Long id) {
		return incidenciasRepository.findOne(id);
	}

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}
}
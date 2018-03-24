package manager.dbManagement.Impl;

import org.springframework.stereotype.Repository;

import manager.dbManagement.AgentDAO;
import manager.entities.Agent;
import manager.entities.Incidencia;

import java.util.HashSet;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 * @since 0.0.1
 */
@Repository
@Transactional
public class AgentDAODummy implements AgentDAO {
	private static Agent dummyAgent;

	@PersistenceContext
	private EntityManager entityManager;

	static {
		dummyAgent = new Agent("pass", "dummy", "Person", 1, "123456", "Clara", "Oswald", "clara@tardis.co.uk");
	}

	@SuppressWarnings("unchecked")
	@Override
	public Agent getAgent(String login, String password, String kind) {
		List<Agent> agent = entityManager
				.createQuery("from Agent where nombreUsuario = ?1 and contrasena = ?2 and kind = ?3")
				.setParameter(1, login).setParameter(2, password).setParameter(3, kind).getResultList();
		if (agent.isEmpty())
			return null;
		else {
			Long id = agent.get(0).getId();
			List<Incidencia> inci = entityManager
					.createQuery("from Incidencia where id_user = ?1")
					.setParameter(1, id).getResultList();
			agent.get(0).setIncidencias(new HashSet<Incidencia>(inci));
			return agent.get(0);
		}
		
	}

	@Override
	public Agent updateInfo(Agent toUpdate) {
		entityManager.merge(toUpdate);
		dummyAgent = toUpdate;
		return dummyAgent;
	}

	
	
	
}
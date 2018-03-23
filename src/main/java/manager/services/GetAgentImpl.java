package manager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import manager.agents.GetAgent;
import manager.dbManagement.AgentDAO;
import manager.entities.Agent;
import manager.util.AgentMin;



@Service
public class GetAgentImpl implements GetAgent{

	 @Autowired
	 private AgentDAO citizenDAO;
	
	@Override
	public AgentMin getAgentsInfo(String login, String password, String kind) {
		Agent c = citizenDAO.getAgent(login, password, kind);
	       if(c != null){
	    	   return new AgentMin(c.getNombre(), c.getApellidos(),  c.getId(), c.getEmail(), c.getKind(), c.getKindCode());
	       }
	       return null;
	}

}

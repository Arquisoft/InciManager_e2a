package manager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import manager.agents.ChangeInfo;
import manager.dbManagement.AgentDAO;
import manager.entities.Agent;



@Service
public class ChangeInfoImpl implements ChangeInfo{
	
	@Autowired
	 private AgentDAO citizenDAO;

	@Override
	public Agent changeInfo(Agent updatedData) {
		return citizenDAO.updateInfo(updatedData);
	}

}

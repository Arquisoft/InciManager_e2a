package manager.agents;

import manager.util.AgentMin;

public interface GetAgent {
	
	AgentMin getAgentsInfo(String login, String password, String kind);

}

package manager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import manager.agents.GetAgent;
import manager.dbManagement.AgentDAO;
import manager.entities.Agent;

@Controller
public abstract class AbstractController {

	@Autowired
	protected GetAgent getAgent;

	@Autowired
	protected AgentDAO ad;

	protected Agent agent;

}
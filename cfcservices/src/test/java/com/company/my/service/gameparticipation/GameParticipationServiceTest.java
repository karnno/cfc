package com.company.my.service.gameparticipation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/cfcservices-context-test.xml",
		"/HibernateWithSpringTest-context.xml" })
public class GameParticipationServiceTest {

	//TODO add a mock DAO for the gameParticipationDao
	//TODO when DAO saves or retrieves, make sure it's fake.
	@Autowired
	GameParticipationService gpService;
	
	@Test
	public void runTest() {
		System.out.println("run a test");
	}
}

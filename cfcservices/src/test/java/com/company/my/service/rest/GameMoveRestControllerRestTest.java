package com.company.my.service.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.company.my.service.wrapper.GameMoveWrapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/cfcservices-context-test.xml",
		"/HibernateWithSpringTest-context.xml" })
public class GameMoveRestControllerRestTest {
	
	@Test
	public void testSaveGameMove() {
		
	}
	
	private GameMoveWrapper createFakeGameMove() {
		GameMoveWrapper toReturn = new GameMoveWrapper();
		
		return toReturn;	
	}
}

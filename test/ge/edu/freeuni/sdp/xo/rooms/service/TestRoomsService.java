package ge.edu.freeuni.sdp.xo.rooms.service;


import javax.ws.rs.core.Application;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

public class TestRoomsService extends JerseyTest{

	@Override
	protected Application configure() {
		return new ResourceConfig(PingService.class);
	}

	
	@Test
	public void testGetRepository() {
		
	}

	@Test
	public void testGetAllRooms() {
		
	}

	@Test
	public void testGetConcreteRoom() {
		
	}

	@Test
	public void testJoinRoom() {
		
	}

	@Test
	public void testLeaveRoom() {
		
	}

}

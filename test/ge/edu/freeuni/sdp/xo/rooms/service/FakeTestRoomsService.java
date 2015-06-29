package ge.edu.freeuni.sdp.xo.rooms.service;

import static org.junit.Assert.*;
import ge.edu.freeuni.sdp.xo.rooms.data.Room;
import ge.edu.freeuni.sdp.xo.rooms.mock.RoomsServiceMock;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

public class FakeTestRoomsService extends JerseyTest{

	@Override
	protected Application configure() {
		return new ResourceConfig(RoomsServiceMock.class);
	}


	@Test
	public void testGetAllRooms() {
		Response response = target("/").request().get(Response.class);
		List<Room> actual = response.readEntity(new GenericType<List<Room>>(){});
		
		List<Room> expected = new ArrayList<Room>();
		expected.add(new Room("3333333333", "1", "2"));
		expected.add(new Room("2222222222", "1", null));
		expected.add(new Room("1111111111", null, null));
		
		assertTrue(compareLists(actual, expected));
    }
	

	@Test
	public void testGetConcreteRoomExistent() {
		Response response = target("/2222222222").request().get(Response.class);
		Room actual = response.readEntity(Room.class);
		
		Room expected = new Room("2222222222", "1", null);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testGetConcreteRoomNotExistent(){
		Response response = target("/-1").request().get(Response.class);
		assertEquals(response.getStatus(), Status.NOT_FOUND.getStatusCode());
	}

	@Test
	public void testJoinRoomFull() {
		Response response = target("/3333333333").request().post(null, Response.class);
		
		assertEquals(Status.CONFLICT.getStatusCode(), response.getStatus());		
	}
	
	@Test
	public void testJoinRoomEmpty() {
		Response response = target("/1111111111").request().post(null, Response.class);
		
		assertEquals(Status.CREATED.getStatusCode(), response.getStatus());	
		
		Room actual = response.readEntity(Room.class);
		
		Room expected = new Room("1111111111", "1", null);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testJoinRoomNotExistent() {
		Response response = target("/-1").request().post(null, Response.class);
		
		assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());	
		
	}

	@Test
	public void testLeaveRoomJoined() {
		Response response = target("/2222222222/1").request().delete(Response.class);
		
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		
		
		response = target("/2222222222").request().get(Response.class);
		
		Room actual = response.readEntity(Room.class);
		
		Room expected = new Room("2222222222", null, null);
		
		System.err.println(actual.getId() + " -> " + expected.getId());
		System.err.println(actual.getx_user() + " -> " + expected.getx_user());
		System.err.println(actual.geto_user() + " -> " + expected.geto_user());
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testLeaveRoomNotJoined() {
		Response response = target("2222222222/2").request().delete(Response.class);
		
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		
		response = target("/2222222222").request().get(Response.class);
		
		Room actual = response.readEntity(Room.class);
		
		Room expected = new Room("2222222222", null, null);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testLeaveRoomNotExistent() {
		Response response = target("-2/2").request().delete(Response.class);
		
		assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());
	}
	
	
	private boolean compareLists(List<Room> list1, List<Room> list2) {
		if(list1 == null && list2 == null) return true;
        if(list1 != null && list2 !=null) {
        	if(list1.size() == list2.size()){
        		for(Room li1Long : list1) {
        			boolean isEqual = false;
                    for(Room li2Long : list2) {
                        if(li1Long.equals(li2Long)){
                        	isEqual = true;
                        	break;
                        }
                    }
                    if(!isEqual)return false;
                }
        	}else{
        		return false;
        	}
        }
        else{
            return false;
        }
        return true; 
    }

}

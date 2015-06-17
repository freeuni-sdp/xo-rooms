package ge.edu.freeuni.sdp.xo.rooms.service;

import java.util.*;

import ge.edu.freeuni.sdp.xo.rooms.data.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@Path("")
@Consumes( { MediaType.APPLICATION_JSON})
@Produces( { MediaType.APPLICATION_JSON})
public class RoomsService {
	
	public Repository getRepository(){
		return FakeRepositoryFactory.createInMemoryRepository();
	}
	
	@GET
	public List<Room> getAllRooms(@QueryParam("token") String token){
		final ArrayList<Room> result = new ArrayList<Room>();
		for (Room room : getRepository().getAll())
			result.add(room);
		return result;
	}
	
	
	@GET
	@Path("{room_id}")
	public Room getConcreteRoom(@PathParam("room_id") int id, @QueryParam("token") String token){
		return getRepository().find(id);
	}
}

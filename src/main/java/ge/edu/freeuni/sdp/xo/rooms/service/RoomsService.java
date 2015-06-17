package ge.edu.freeuni.sdp.xo.rooms.service;

import java.util.*;

import ge.edu.freeuni.sdp.xo.rooms.data.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;


@Path("")
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
	public Response getConcreteRoom(@PathParam("room_id") int id, @QueryParam("token") String token){
		Room room = getRepository().find(id);
		
		if(room == null){
			return Response.status(Status.NOT_FOUND).build();
		}
		
		return Response.ok(room).build();
	}
	
	@POST
	@Path("{room_id}")
	public Response joinRoom(@PathParam("room_id") int id, @QueryParam("token") String token){
		Room room = getRepository().find(id);
		
		if(room == null){
			return Response.status(Status.NOT_FOUND).build();
		}
		
		if(room.getx_user() == null){
			int x_user = 1; //TODO get real userID from auth service
			room.setx_user(x_user);
			return Response.ok(room).build();
		}else if(room.geto_user() == null){
			int o_user = 2; //TODO get real userID from auth service
			room.seto_user(o_user);
			return Response.ok(room).build();
		}else{
			return Response.status(Status.CONFLICT).build();
		}
	}
	
	@Path("{room_id}/{user_id}")
	@DELETE
	public Response leaveRoom(
			@PathParam("room_id") int roomId, 
			@PathParam("user_id") int userId, 
			@QueryParam("token") String token){
		
		Room room = getRepository().find(roomId);
		if(room == null)
			return Response.status(Status.NOT_FOUND).build();
		if(room.getx_user() == userId){
			room.setx_user(null);
			return Response.ok().build();
		}else if(room.geto_user() == userId){
			room.seto_user(null);
			return Response.ok().build();
		}else
			return Response.status(Status.NOT_FOUND).build();
	}
}
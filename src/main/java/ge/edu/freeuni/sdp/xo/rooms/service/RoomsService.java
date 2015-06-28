package ge.edu.freeuni.sdp.xo.rooms.service;

import java.net.URI;
import java.util.*;

import ge.edu.freeuni.sdp.xo.rooms.data.*;

import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.client.ClientConfig;




@Path("")
@Produces( { MediaType.APPLICATION_JSON})
public class RoomsService {
	
	@Context
	UriInfo uriInfo;

	
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
			
			String x_user = getIdFromToken(token);
			room.setx_user(x_user);
			final URI uri = uriInfo.getAbsolutePathBuilder().path(x_user).build();
			return Response.created(uri).entity(room).build();
		}else if(room.geto_user() == null){
			String o_user = getIdFromToken(token);
			room.seto_user(o_user);
			URI uri = uriInfo.getAbsolutePathBuilder().path(o_user).build();
			return Response.created(uri).entity(room).build();
		}else{
			return Response.status(Status.CONFLICT).build();
		}
	}
	
	@Path("{room_id}/{user_id}")
	@DELETE
	public Response leaveRoom(
			@PathParam("room_id") int roomId, 
			@PathParam("user_id") String userId, 
			@QueryParam("token") String token){
		
		Room room = getRepository().find(roomId);
		if(room == null)
			return Response.status(Status.NOT_FOUND).build();
		if(room.getx_user() != null &&  room.getx_user().equals(userId)){
			room.setx_user(null);
			return Response.ok().build();
		}else if(room.geto_user() != null && room.geto_user().equals(userId)){
			room.seto_user(null);
			return Response.ok().build();
		}else
			return Response.ok().build();
	}
	
	
	protected String getIdFromToken(String token){
        Client client = ClientBuilder.newClient(new ClientConfig());
        UserName response = client.target("").request().get(UserName.class);
        return response.username;
	}
	
	protected Repository getRepository(){
		return RepositoryFactory.createInMemoryRepository();
	}
}
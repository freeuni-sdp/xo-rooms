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
import org.glassfish.jersey.client.ClientResponse;

import com.microsoft.azure.storage.StorageException;

@Path("")
@Produces({ MediaType.APPLICATION_JSON })
public class RoomsService {

	private static final String LOGIN_SERVICE = "http://xo-login.herokuapp.com/";

	@Context
	UriInfo uriInfo;

	@GET
	public Response getAllRooms(@QueryParam("token") String token)
			throws StorageException {
		if (isTokenValid(token)) {
			final ArrayList<Room> result = new ArrayList<Room>();
			for (RoomEntity roomEntity : getRepository().getAll())
				result.add(roomEntity.getRoom());
			return Response.ok(result).build();
		} else
			return Response.status(Status.FORBIDDEN).build();

	}

	@GET
	@Path("{room_id}")
	public Response getConcreteRoom(@PathParam("room_id") String id,
			@QueryParam("token") String token) throws StorageException {
		if (isTokenValid(token)) {
			RoomEntity roomEntity = getRepository().find(id);

			if (roomEntity == null) {
				return Response.status(Status.NOT_FOUND).build();
			}

			return Response.ok(roomEntity.getRoom()).build();
		} else
			return Response.status(Status.FORBIDDEN).build();
	}

	@POST
	@Path("{room_id}")
	public Response joinRoom(@PathParam("room_id") String id,
			@QueryParam("token") String token) throws StorageException {
		if (isTokenValid(token)) {
			RoomEntity roomEntity = getRepository().find(id);

			if (roomEntity == null) {
				return Response.status(Status.NOT_FOUND).build();
			}

			if (roomEntity.getx_user() == null) {

				String x_user = getIdFromToken(token);
				roomEntity.setx_user(x_user);
				getRepository().insertOrUpdate(roomEntity);
				URI uri = uriInfo.getAbsolutePathBuilder().path(x_user).build();
				return Response.created(uri).entity(roomEntity.getRoom())
						.build();
			} else if (roomEntity.geto_user() == null) {
				String o_user = getIdFromToken(token);
				roomEntity.seto_user(o_user);
				getRepository().insertOrUpdate(roomEntity);
				URI uri = uriInfo.getAbsolutePathBuilder().path(o_user).build();
				return Response.created(uri).entity(roomEntity.getRoom())
						.build();
			} else {
				return Response.status(Status.CONFLICT).build();
			}
		} else
			return Response.status(Status.FORBIDDEN).build();
	}

	@Path("{room_id}/{user_id}")
	@DELETE
	public Response leaveRoom(@PathParam("room_id") String roomId,
			@PathParam("user_id") String userId,
			@QueryParam("token") String token) throws StorageException {
		if (isTokenValid(token)) {
			RoomEntity roomEntity = getRepository().find(roomId);
			if (roomEntity == null)
				return Response.status(Status.NOT_FOUND).build();

			if (roomEntity.getx_user() != null
					&& roomEntity.getx_user().equals(userId)) {
				roomEntity.setx_user(null);
				getRepository().insertOrUpdate(roomEntity);
				return Response.ok().build();
			} else if (roomEntity.geto_user() != null
					&& roomEntity.geto_user().equals(userId)) {
				roomEntity.seto_user(null);
				getRepository().insertOrUpdate(roomEntity);
				return Response.ok().build();
			} else
				return Response.ok().build();
		} else
			return Response.status(Status.FORBIDDEN).build();
	}

	protected String getIdFromToken(String token) {
		if (token == null)
			return null;

		Client client = ClientBuilder.newClient(new ClientConfig());
		ClientResponse response = client.target(LOGIN_SERVICE + "?token=" + token).request(MediaType.APPLICATION_JSON_TYPE)
				.get(ClientResponse.class);
		if(response.getStatus() != Status.OK.getStatusCode())
			return null;
		
		UserName username = response.readEntity(UserName.class);
		
		return username.username;
	}

	protected Repository getRepository() {
		return RepositoryFactory.createInMemoryRepository();
	}

	protected boolean isTokenValid(String token) {
		return getIdFromToken(token) != null;
	}
}
package ge.edu.freeuni.sdp.xo.rooms.mock;

import com.microsoft.azure.storage.StorageException;

import ge.edu.freeuni.sdp.xo.rooms.data.RepositoryFactory;
import ge.edu.freeuni.sdp.xo.rooms.data.Repository;
import ge.edu.freeuni.sdp.xo.rooms.service.RoomsService;

public class RoomsServiceMock extends RoomsService {
	
	@Override
	protected Repository getRepository() throws StorageException{
		return RepositoryFactory.createRepository();
	}

	@Override
	protected String getIdFromToken(String token){
		return "1";
	}
	
	
	@Override
	protected boolean isTokenValid(String token){
		return true;
	}
}

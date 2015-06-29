package ge.edu.freeuni.sdp.xo.rooms.mock;

import ge.edu.freeuni.sdp.xo.rooms.data.InMemoryRepositoryFactory;
import ge.edu.freeuni.sdp.xo.rooms.data.Repository;
import ge.edu.freeuni.sdp.xo.rooms.service.RoomsService;

public class RoomsServiceMock extends RoomsService {
	
	@Override
	protected Repository getRepository(){
		return InMemoryRepositoryFactory.createInMemoryRepository();
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

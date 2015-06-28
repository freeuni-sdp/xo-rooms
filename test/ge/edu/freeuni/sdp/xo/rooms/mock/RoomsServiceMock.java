package ge.edu.freeuni.sdp.xo.rooms.mock;

import ge.edu.freeuni.sdp.xo.rooms.data.RepositoryFactory;
import ge.edu.freeuni.sdp.xo.rooms.data.Repository;
import ge.edu.freeuni.sdp.xo.rooms.service.RoomsService;

public class RoomsServiceMock extends RoomsService {
	
	@Override
	protected Repository getRepository(){
		return RepositoryFactory.createInMemoryRepository();
	}

	@Override
	protected String getIdFromToken(String token){
		return "1";
	}
	
}

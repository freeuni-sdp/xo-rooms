package ge.edu.freeuni.sdp.xo.rooms.service;

import ge.edu.freeuni.sdp.xo.rooms.data.FakeRepositoryFactory;
import ge.edu.freeuni.sdp.xo.rooms.data.Repository;

public class RoomsServiceMock extends RoomsService {
	
	@Override
	public Repository getRepository(){
		return FakeRepositoryFactory.createInMemoryRepository();
	}

}

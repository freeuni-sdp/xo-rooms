package ge.edu.freeuni.sdp.xo.rooms.data;

import com.microsoft.azure.storage.StorageException;

public class RepositoryFactory {
	private static Repository repo;
	
	public static Repository createRepository(){
		if(repo == null){
			repo = InMemoryRepository.getInstance();
		
			Room empty = new Room("1111111111", null, null);
			Room one = new Room(  "2222222222", "1", null);
			Room full = new Room( "3333333333", "1", "2");
			
			try {
				repo.insertOrUpdate(RoomEntity.fromRoom(full));
				repo.insertOrUpdate(RoomEntity.fromRoom(one));
				repo.insertOrUpdate(RoomEntity.fromRoom(empty));
			} catch (StorageException e) {
				e.printStackTrace();
			}
		}
		
		return repo;
	}
}

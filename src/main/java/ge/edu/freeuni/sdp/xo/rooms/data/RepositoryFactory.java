package ge.edu.freeuni.sdp.xo.rooms.data;

import com.microsoft.azure.storage.StorageException;

public class RepositoryFactory {
	private static Repository repo;
	
	public static Repository createInMemoryRepository(){
		if(repo == null){
			repo = InMemoryRepository.getInstance();
		
			Room empty = new Room(1, null, null);
			Room one = new Room(2, "1", null);
			Room full = new Room(3, "1", "2");
			
			try {
				repo.insertOrUpdate(full);
				repo.insertOrUpdate(one);
				repo.insertOrUpdate(empty);
			} catch (StorageException e) {
				//Will never happen
			}
		}
		
		return repo;
	}
}

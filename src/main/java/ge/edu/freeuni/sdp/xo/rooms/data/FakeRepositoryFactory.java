package ge.edu.freeuni.sdp.xo.rooms.data;

public class FakeRepositoryFactory {
	private static Repository repo;
	
	public static Repository createInMemoryRepository(){
		if(repo == null){
			repo = InMemoryRepository.getInstance();
		
			Room empty = new Room(1, -1, -1);
			Room one = new Room(2, 1, -1);
			Room full = new Room(3, 1, 2);
			
			repo.insertOrUpdate(full);
			repo.insertOrUpdate(one);
			repo.insertOrUpdate(empty);
		}
		
		return repo;
	}
}

package ge.edu.freeuni.sdp.xo.rooms.data;

import java.util.HashMap;
import java.util.Map;

public class InMemoryRepository implements Repository {
	//id -> room
	private Map<Integer, Room> rooms;
	private static InMemoryRepository instance;
	
	public static InMemoryRepository getInstance(){
		if(instance == null)
			instance = new InMemoryRepository(new HashMap<Integer, Room>());
		return instance;
	}
	
	private InMemoryRepository(Map<Integer, Room> rooms) {
		this.rooms = rooms;
	}
	
	
	@Override
	public void insertOrUpdate(Room room) {
		rooms.put(room.getId(), room);
	}

	@Override
	public Room delete(int id) {
		return rooms.remove(id);
	}

	@Override
	public Room find(int id) {
		return rooms.get(id);
	}

	@Override
	public Iterable<Room> getAll() {
		return rooms.values();
	}

}

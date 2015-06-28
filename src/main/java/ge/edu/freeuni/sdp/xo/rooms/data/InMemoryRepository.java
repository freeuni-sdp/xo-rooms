package ge.edu.freeuni.sdp.xo.rooms.data;

import java.util.HashMap;
import java.util.Map;

public class InMemoryRepository implements Repository {
	//id -> room
	private Map<String, RoomEntity> rooms;
	private static InMemoryRepository instance;
	
	public static InMemoryRepository getInstance(){
		if(instance == null)
			instance = new InMemoryRepository(new HashMap<String, RoomEntity>());
		return instance;
	}
	
	private InMemoryRepository(Map<String, RoomEntity> rooms) {
		this.rooms = rooms;
	}
	
	
	@Override
	public void insertOrUpdate(RoomEntity room) {
		rooms.put(room.getRoom().getId(), room);
	}

	@Override
	public RoomEntity delete(String id) {
		return rooms.remove(id);
	}

	@Override
	public RoomEntity find(String id) {
		return rooms.get(id);
	}

	@Override
	public Iterable<RoomEntity> getAll() {
		return rooms.values();
	}

}

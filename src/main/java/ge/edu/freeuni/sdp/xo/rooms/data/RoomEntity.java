package ge.edu.freeuni.sdp.xo.rooms.data;

import com.microsoft.azure.storage.table.TableServiceEntity;

public class RoomEntity extends TableServiceEntity {
	private String x_user;
	private String o_user;
	
	public RoomEntity() {
	}

	private RoomEntity(Room room) {
		RoomEntityId id = new RoomEntityId(room.getId());
		this.partitionKey = id.getPartitionKey();
		this.rowKey = id.getRowKey();
		this.x_user = room.getx_user();
		this.o_user = room.geto_user();

	}
	
	public static RoomEntity fromRoom(Room room) {
		return new RoomEntity(room);
	}
	
	public Room getRoom() {
		String id = getEntityId().getId();
		Room room = new Room();
		room.setId(id);
		room.setx_user(this.getx_user());
		room.seto_user(this.geto_user());
		return room;
	}
	
	
	
	private RoomEntityId getEntityId() {
		return new RoomEntityId(this.partitionKey, this.rowKey);
	}
	
	public String getx_user() {
		return x_user;
	}
	
	public String geto_user(){
		return o_user;
	}
	
	public void setx_user(String id) {
		this.x_user = id;
	}
	
	public void seto_user(String id) {
		this.o_user = id;
	}
}

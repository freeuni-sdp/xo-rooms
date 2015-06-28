package ge.edu.freeuni.sdp.xo.rooms.data;

public class RoomEntityId {
	private String partitionKey;
	private String rowKey;

	public RoomEntityId(String partitionKey, String rowKey) {
		this.partitionKey = partitionKey;
		this.rowKey  = rowKey;
	}
	
	public RoomEntityId(String id) {
		final int cutIndex = 3;
		this.partitionKey = id.substring(0, 3);
		this.rowKey = id.substring(cutIndex, id.length());
	}
	
	public String getId() {
		return this.partitionKey.concat(this.rowKey);
	}

	public String getPartitionKey() {
		return partitionKey;
	}

	public String getRowKey() {
		return rowKey;
	}
}

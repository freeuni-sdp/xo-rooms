package ge.edu.freeuni.sdp.xo.rooms.data;

import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.table.CloudTable;
import com.microsoft.azure.storage.table.TableOperation;
import com.microsoft.azure.storage.table.TableQuery;

public class CloudRepository implements Repository{
	
	private CloudTable table;
	
	public CloudRepository(CloudTable table) {
		this.table = table;
	}

	@Override
	public void insertOrUpdate(RoomEntity room) throws StorageException {
		TableOperation operation = TableOperation.insertOrReplace(room);
		table.execute(operation);
	}

	@Override
	public RoomEntity delete(String id) throws StorageException {
		RoomEntity room = find(id);
		if (room == null) return null;
		TableOperation operation = TableOperation.delete(room);
		table.execute(operation);
		return room;
	}

	@Override
	public RoomEntity find(String id) throws StorageException {
		RoomEntityId tskId = new RoomEntityId(id);
		TableOperation operation = 
				TableOperation.retrieve(
					tskId.getPartitionKey(), 
					tskId.getRowKey(), 
					RoomEntity.class);
		return table.execute(operation).getResultAsType();
	}

	@Override
	public Iterable<RoomEntity> getAll() throws StorageException {
		TableQuery<RoomEntity> query =
			       TableQuery.from(RoomEntity.class);
		 return table.execute(query);
	}

}

package ge.edu.freeuni.sdp.xo.rooms.data;

import com.microsoft.azure.storage.StorageException;

public class CloudRepository implements Repository{

	@Override
	public void insertOrUpdate(RoomEntity room) throws StorageException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public RoomEntity delete(String id) throws StorageException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RoomEntity find(String id) throws StorageException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<RoomEntity> getAll() throws StorageException {
		// TODO Auto-generated method stub
		return null;
	}

}

package ge.edu.freeuni.sdp.xo.rooms.data;

import com.microsoft.azure.storage.StorageException;

public interface Repository {
	public abstract void insertOrUpdate(RoomEntity room) throws StorageException;

	public abstract RoomEntity delete(String id) throws StorageException;

	public abstract RoomEntity find(String id) throws StorageException;

	public abstract Iterable<RoomEntity> getAll() throws StorageException;
}

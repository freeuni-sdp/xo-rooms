package ge.edu.freeuni.sdp.xo.rooms.data;

import com.microsoft.azure.storage.StorageException;

public interface Repository {
	public abstract void insertOrUpdate(Room room) throws StorageException;

	public abstract Room delete(String id) throws StorageException;

	public abstract Room find(String id) throws StorageException;

	public abstract Iterable<Room> getAll() throws StorageException;
}

package ge.edu.freeuni.sdp.xo.rooms.data;

public interface Repository {
	public abstract void insertOrUpdate(Room room);

	public abstract Room delete(int id);

	public abstract Room find(int id);

	public abstract Iterable<Room> getAll();
}

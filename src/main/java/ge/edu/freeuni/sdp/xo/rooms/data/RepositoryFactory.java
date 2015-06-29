package ge.edu.freeuni.sdp.xo.rooms.data;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.table.CloudTable;
import com.microsoft.azure.storage.table.CloudTableClient;

public class RepositoryFactory {
	public static final int ROOMS_COUNT=10;
	
	public static Repository createRepository() throws StorageException{
		CloudRepository cloudRepository = new CloudRepository(getTable());
		for(int i=0; i<ROOMS_COUNT;i++){
			String roomId = ""+i+1;
			Room room = new Room(roomId,null,null);
			if(cloudRepository.find(roomId) == null)
				cloudRepository.insertOrUpdate(RoomEntity.fromRoom(room));
		}
		return cloudRepository;
	}
	
	private static CloudTable getTable() throws StorageException {

		final String storageConnectionString = "DefaultEndpointsProtocol=http;"
				+ "AccountName=freeunisdptodo;"
				+ "AccountKey=+UKHsHFQUWDjoHT1S7q4Ivc1phivLmXwWESvpcRCCJwhs1BnShkaFOOQs+BmI4XWtNnyg78S6ovbD2J5QCKxsQ==";

		CloudStorageAccount storageAccount;
		try {
			storageAccount = CloudStorageAccount.parse(storageConnectionString);
		} catch (InvalidKeyException | URISyntaxException e) {
			e.printStackTrace();
			return null;
		}

		CloudTableClient tableClient = storageAccount.createCloudTableClient();
		final String tableName = "rooms";
		CloudTable cloudTable;
		try {
			cloudTable = new CloudTable(tableName, tableClient);
		} catch (URISyntaxException e) {
			e.printStackTrace();
			return null;
		}
		cloudTable.createIfNotExists();
		return cloudTable;
	}
}

package ge.edu.freeuni.sdp.xo.rooms.data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Room {
	
	@XmlElement
	private int id;
	
	@XmlElement
	private int xUserId;
	
	@XmlElement
	private int oUserId;
	/**
	 * @param id
	 * @param xUserId
	 * @param oUserId
	 */
	public Room(int id, int xUserId, int oUserId) {
		this.id = id;
		this.xUserId = xUserId;
		this.oUserId = oUserId;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the xUserId
	 */
	public int getxUserId() {
		return xUserId;
	}
	/**
	 * @param xUserId the xUserId to set
	 */
	public void setxUserId(int xUserId) {
		this.xUserId = xUserId;
	}
	/**
	 * @return the oUserId
	 */
	public int getoUserId() {
		return oUserId;
	}
	/**
	 * @param oUserId the oUserId to set
	 */
	public void setoUserId(int oUserId) {
		this.oUserId = oUserId;
	}
	
	
}

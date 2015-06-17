package ge.edu.freeuni.sdp.xo.rooms.data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Room {
	
	@XmlElement
	private int id;
	
	@XmlElement(nillable=true)
	private Integer x_user;
	
	@XmlElement(nillable=true)
	private Integer o_user;
	/**
	 * @param id
	 * @param x_user
	 * @param oUserId
	 */
	public Room(int id, Integer x_user, Integer o_user) {
		this.id = id;
		this.x_user = x_user;
		this.o_user = o_user;
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
	 * @return the x_user
	 */
	public int getxUserId() {
		return x_user;
	}
	/**
	 * @param x_user the x_user to set
	 */
	public void setxUserId(Integer x_user) {
		this.x_user = x_user;
	}
	/**
	 * @return the o_user
	 */
	public int getoUserId() {
		return o_user;
	}
	/**
	 * @param o_user the o_user to set
	 */
	public void setoUserId(Integer o_user) {
		this.o_user = o_user;
	}
	
	
}

package ge.edu.freeuni.sdp.xo.rooms.data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Room {
	
	private int id;
	
	
	private Integer x_user;
	private Integer o_user;
	/**
	 * @param id
	 * @param x_user
	 * @param o_user
	 */
	public Room(int id, Integer x_user, Integer o_user) {
		this.id = id;
		this.x_user = x_user;
		this.o_user = o_user;
	}
	/**
	 * @return the id
	 */
	@XmlElement
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
	@XmlElement(nillable=true)
	public int getx_user() {
		return x_user;
	}
	/**
	 * @param x_user the x_user to set
	 */
	public void setx_user(Integer x_user) {
		this.x_user = x_user;
	}
	/**
	 * @return the o_user
	 */
	@XmlElement(nillable=true)
	public int geto_user() {
		return o_user;
	}
	/**
	 * @param o_user the o_user to set
	 */
	public void seto_user(Integer o_user) {
		this.o_user = o_user;
	}
	
	
}

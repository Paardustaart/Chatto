package nl.max.util;

import java.util.EventObject;

public class ConnectEvent extends EventObject {

	private static final long serialVersionUID = 1L;
	
	private boolean isHost;
	private String address;
	private int port;
	private String name;

	public ConnectEvent(Object source, boolean host, String address, String name, int port) {
		super(source);
		this.isHost = host;
		this.address = address;
		this.port = port;
		this.name = name;
	}
	
	public boolean getIsHost() {
		return isHost;
	}
	
	public String getAddress() {
		return address;
	}
	
	public String getName() {
		return name;
	}
	
	public int getPort() {
		return port;
	}
	
	@Override
	public String toString() {
		return "Host: " + isHost + ", Address: " + address + ", Port: " + port + ", Name: " + name;
	}
}

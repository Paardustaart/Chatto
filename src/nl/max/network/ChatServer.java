package nl.max.network;

import java.io.IOException;
import java.util.HashMap;

import nl.max.network.Network.ChatMessage;
import nl.max.network.Network.RegisterName;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

public class ChatServer {
	
	
	private HashMap<Integer, String> names;
	private Server server;
	
	public ChatServer(int tcpPort, int udpPort) throws IOException {
		names = new HashMap<Integer, String>();
		server = new Server();
		Network.register(server);
		server.start();
		server.bind(tcpPort, udpPort);
		
		server.addListener(new Listener(){
			
			@Override
			public void connected(Connection connection) {
				
			}
			
			@Override
			public void received(Connection connection, Object object) {
				
				if(object instanceof RegisterName) {
					RegisterName registerName = (RegisterName) object;
					names.put(connection.getID(), registerName.name);
					
					ChatMessage chatMessage = new ChatMessage();
					chatMessage.body = registerName.name + " connected.";
					server.sendToAllTCP(chatMessage);
				}
				
				if(object instanceof ChatMessage) {
					if(names.containsKey(connection.getID())) {
						ChatMessage chatMessage = (ChatMessage) object;
						chatMessage.body = names.get(connection.getID()) + ": " + chatMessage.body;
						server.sendToAllTCP(chatMessage);
					}
					
				}
				
			}
			
			@Override
			public void disconnected(Connection connection) {
				ChatMessage chatMessage = new ChatMessage();
				chatMessage.body = names.get(connection.getID()) + " disconnected.";
				server.sendToAllTCP(chatMessage);
				names.remove(connection.getID());
			}
			
		});
	}
	
}

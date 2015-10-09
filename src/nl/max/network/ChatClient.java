package nl.max.network;

import java.io.IOException;

import nl.max.network.Network.ChatMessage;
import nl.max.network.Network.RegisterName;
import nl.max.util.MessageEvent;
import nl.max.util.ReceivedMessageListener;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class ChatClient {
	
	private Client client;
	
	private ReceivedMessageListener listener;
	
	public ChatClient(String host, int tcpPort, int udpPort) throws IOException {
		client = new Client();
		Network.register(client);
		client.start();
		client.connect(5000, host, tcpPort, udpPort);
		
		client.addListener(new Listener(){
			
			@Override
			public void connected(Connection connection) {
				
			}
			
			@Override
			public void received(Connection connection, Object object) {
				
				if(object instanceof ChatMessage) {
					ChatMessage chatMessage = (ChatMessage) object;
					fireEvent(new MessageEvent(this, chatMessage.body));
				}
				
			}
			
		});
	}
	
	public void registerName(String name) {
		RegisterName registerName = new RegisterName();
		registerName.name = name;
		client.sendTCP(registerName);
	}
	
	public void sendMessage(String body) {
		ChatMessage chatMessage = new ChatMessage();
		chatMessage.body = body;
		client.sendTCP(chatMessage);
	}
	
	public void setReceivedMessageListener(ReceivedMessageListener listener) {
		this.listener = listener;
	}
	
	private void fireEvent(MessageEvent event) {
		if(listener != null) {
			listener.receiveMessage(event);
		}
	}
	
}

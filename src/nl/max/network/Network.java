package nl.max.network;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;

// Contains shared classes that the server and client use.
public class Network {
	public static void register(EndPoint endPoint) {
		Kryo kryo = endPoint.getKryo();
		kryo.register(RegisterName.class);
		kryo.register(ChatMessage.class);
	}
	
	public static class RegisterName {
		public String name;
	}
	
	public static class ChatMessage {
		public String body;
	}
}

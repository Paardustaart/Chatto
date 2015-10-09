package nl.max.util;

import java.util.EventListener;

public interface ReceivedMessageListener extends EventListener {
	public void receiveMessage(MessageEvent event);
}

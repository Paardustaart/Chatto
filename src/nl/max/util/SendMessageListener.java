package nl.max.util;

import java.util.EventListener;

public interface SendMessageListener extends EventListener {
	public void sendMessageOccurred(MessageEvent event);
}

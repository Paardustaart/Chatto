package nl.max.util;

import java.util.EventListener;

public interface SetupConnectionListener extends EventListener {
	public void SetupConnectionOccurred(ConnectEvent event);
}

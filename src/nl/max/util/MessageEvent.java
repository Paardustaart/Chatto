package nl.max.util;

import java.util.EventObject;

public class MessageEvent extends EventObject {
	
	private static final long serialVersionUID = 1L;

	private String message;
	
	public MessageEvent(Object source, String message) {
		super(source);
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	
	@Override
	public String toString() {
		return "Message: " + message;
	}
}

package nl.max.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import nl.max.util.MessageEvent;
import nl.max.util.SendMessageListener;

public class BottomPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private SendMessageListener listener;
	private JTextField messageField;
	private JButton sendButton;
	
	public BottomPanel() {
		this.messageField = new JTextField();
		this.sendButton = new JButton("Send");
		this.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		
		gc.weightx = 90;
		gc.weighty = 0;
		
		gc.gridx = 0;
		gc.gridy = 0;
		gc.fill = GridBagConstraints.BOTH;
		this.add(messageField, gc);
		
		gc.weightx = 10;
		gc.weighty = 0;
		
		gc.gridx = 1;
		gc.gridy = 0;
		this.add(sendButton, gc);
		
		sendButton.addActionListener((e) -> fireEvent(new MessageEvent(e, messageField.getText())));
		messageField.addActionListener((e) -> fireEvent(new MessageEvent(e, messageField.getText())));
	}
	
	public void setSendMessageListener(SendMessageListener listener) {
		this.listener = listener;
	}
	
	private void fireEvent(MessageEvent event) {
		if(listener != null) {
			listener.sendMessageOccurred(event);
			messageField.setText("");
		}
	}

}

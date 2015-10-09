package nl.max.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import nl.max.network.ChatClient;
import nl.max.network.ChatServer;

public class MainPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private TopPanel topPanel;
	private MiddlePanel middlePanel;
	private BottomPanel bottomPanel;
	
	private ChatServer server;
	private ChatClient client;
	
	public MainPanel(int width, int height) {
		topPanel = new TopPanel();
		middlePanel = new MiddlePanel();
		bottomPanel = new BottomPanel();
		
		this.setPreferredSize(new Dimension(width, height));
		this.setLayout(new BorderLayout());
		
		this.add(topPanel, BorderLayout.NORTH);
		this.add(middlePanel, BorderLayout.CENTER);
		this.add(bottomPanel, BorderLayout.SOUTH);
		
		topPanel.setSetupConnectionListener((event) -> {
			try {
				if(event.getIsHost() && (server == null && client == null)) {
					setupServer(event.getPort(), event.getPort() + 1);
					setupClient("localhost", event.getPort(), event.getPort() + 1, event.getName());
				} else if(client == null) {
					setupClient(event.getAddress(), event.getPort(), event.getPort() + 1, event.getName());
				}
			} catch(Exception e) {
				
			}
		});
		
		bottomPanel.setSendMessageListener((event) -> {
			client.sendMessage(event.getMessage());
		});
	}
	
	private void setupServer(int tcpPort, int udpPort) {
		try {
			server = new ChatServer(tcpPort, udpPort);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Could not create server.");
		}
	}
	
	private void setupClient(String address, int tcpPort, int udpPort, String name) {
		try {
			client = new ChatClient(address, tcpPort, udpPort);
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Could not create client.");
		} finally {
			client.registerName(name);
		}
		
		client.setReceivedMessageListener((event) -> {
			middlePanel.appendText(event.getMessage());
		});
	}

}

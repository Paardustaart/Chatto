package nl.max.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import nl.max.util.ConnectEvent;
import nl.max.util.SetupConnectionListener;

public class TopPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private JLabel checkBoxLabel;
	private JLabel hostLabel;
	private JLabel nameLabel;
	private JLabel tcpPortLabel;
	private JCheckBox hostCheckBox;
	private JTextField hostTextField;
	private JTextField nameTextField;
	private JTextField tcpPortTextField;
	private JButton connectButton;
	
	private SetupConnectionListener listener;
	
	public TopPanel() {
		this.checkBoxLabel = new JLabel("Host: ");
		this.hostLabel = new JLabel("Address: ");
		this.nameLabel = new JLabel("Name: ");
		this.tcpPortLabel = new JLabel("TCP port: ");
		this.hostCheckBox = new JCheckBox();
		this.hostTextField = new JTextField(8);
		this.nameTextField = new JTextField(8);
		this.tcpPortTextField = new JTextField(4);
		this.connectButton = new JButton("Connect");
		this.setBorder(BorderFactory.createTitledBorder(""));
		this.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		
		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx = 0;
		gc.gridy = 0;
		gc.anchor = GridBagConstraints.LINE_END;
		this.add(checkBoxLabel, gc);
		
		gc.gridx = 1;
		gc.gridy = 0;
		gc.anchor = GridBagConstraints.LINE_START;
		this.add(hostCheckBox, gc);
		
		gc.gridx = 2;
		gc.gridy = 0;
		gc.anchor = GridBagConstraints.LINE_END;
		this.add(hostLabel, gc);
		
		gc.gridx = 3;
		gc.gridy = 0;
		gc.anchor = GridBagConstraints.LINE_START;
		this.add(hostTextField, gc);
		
		gc.gridx = 4;
		gc.gridy = 0;
		gc.anchor = GridBagConstraints.LINE_END;
		this.add(tcpPortLabel, gc);
		
		
		gc.gridx = 5;
		gc.gridy = 0;
		gc.anchor = GridBagConstraints.LINE_START;
		this.add(tcpPortTextField, gc);
		
		gc.gridx = 6;
		gc.gridy = 0;
		gc.anchor = GridBagConstraints.LINE_END;
		this.add(nameLabel, gc);
		
		
		gc.gridx = 7;
		gc.gridy = 0;
		gc.anchor = GridBagConstraints.LINE_START;
		this.add(nameTextField, gc);
		
		gc.gridx = 8;
		gc.gridy = 0;
		gc.anchor = GridBagConstraints.LINE_END;
		this.add(connectButton, gc);
		
		connectButton.addActionListener((e) -> fireEvent(new ConnectEvent(e, hostCheckBox.isSelected(), hostTextField.getText(), nameTextField.getText(), Integer.parseInt(tcpPortTextField.getText()))));
		
		hostCheckBox.addActionListener((e) -> handleAddressField(hostCheckBox.isSelected()));
	}
	
	public void setSetupConnectionListener(SetupConnectionListener listener) {
		this.listener = listener;
	}
	
	private void handleAddressField(boolean checked) {
		if(checked) {
			hostTextField.setEnabled(false);
		} else {
			hostTextField.setEnabled(true);
		}
	}
	
	private void fireEvent(ConnectEvent event) {
		if(listener != null) {
			listener.SetupConnectionOccurred(event);
		}
	}
}

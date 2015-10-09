package nl.max.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class MiddlePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JTextArea textArea;
	
	public MiddlePanel() {
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setFocusable(false);
		textArea.setBorder(BorderFactory.createTitledBorder(""));
		this.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		
		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx = 0;
		gc.gridy = 0;
		gc.fill = GridBagConstraints.BOTH;
		this.add(textArea, gc);
	}
	
	public void appendText(String text) {
		textArea.append(text + "\n");
	}

}

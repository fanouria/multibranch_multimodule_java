package gr.codelearn.core.showcase.swing.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ErrorMessageGUI extends JFrame {
	// Declare all components as attributes of your class
	private JPanel contentPanel;
	private JPanel mainPanel;

	private JButton bt1;

	private JLabel lb1;

	public ErrorMessageGUI(String errorMessage) {
		// Initializes a GUI for adding a customer

		// Created from the parent class (JFrame)
		contentPanel = (JPanel) this.getContentPane();

		// Creating the panel that will later be added in a layout manager
		mainPanel = new JPanel();

		// Setting title for window
		this.setTitle("Error");

		// Creating button and setting its text and listener (through an anonymous class)
		bt1 = new JButton("OK");
		bt1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});

		// Setting labels for input fields
		lb1 = new JLabel(errorMessage);
		// Centers the text inside the grid cell
		lb1.setHorizontalAlignment(JLabel.CENTER);

		// Setting GridLayout to main panel
		mainPanel.setLayout(new GridLayout(2, 1));
		// Adding "elements" to main panel
		// Note: Addition ordering matters
		mainPanel.add(lb1); // 1,1
		mainPanel.add(bt1); // 2,1

		// Adding all panels to the content panel
		// Notice the border that each panel is added in
		contentPanel.add(mainPanel, BorderLayout.CENTER);

		// Setting the X button (from the OS window) to hide this window
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		// Finally, fit to current window
		this.pack();
	}

}

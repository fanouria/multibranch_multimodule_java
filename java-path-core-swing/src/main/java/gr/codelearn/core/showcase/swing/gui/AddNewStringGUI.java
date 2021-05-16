package gr.codelearn.core.showcase.swing.gui;

import gr.codelearn.core.showcase.swing.service.CustomerService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddNewStringGUI extends JFrame {

	// Declare all components as attributes of your class
	private JPanel contentPanel;
	private JPanel mainPanel;
	private JPanel buttonsPanel;

	private JButton bt1;
	private JButton bt2;

	private JTextField tf1;

	private JLabel lb1;

	CustomerService customerService;
	DefaultListModel<String> listModel;

	public AddNewStringGUI(CustomerService customerService, DefaultListModel<String> listModel, String[] messages) {
		// Initializes a GUI for adding a string

		this.customerService = customerService;
		this.listModel = listModel;

		// Created from the parent class (JFrame)
		contentPanel = (JPanel) this.getContentPane();

		// Creating the panel that will later be added in a layout manager
		mainPanel = new JPanel();
		buttonsPanel = new JPanel();

		// Setting title for window
		this.setTitle("Add New One");

		// Creating button and setting its text and listener (through an anonymous class)
		bt1 = new JButton("Close");
		bt1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});

		// Creating button and setting its text and listener (through an anonymous class)
		bt2 = new JButton("Add");
		bt2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addCustomerToList(messages);
			}
		});

		// Setting input fields
		tf1 = new JTextField(20);

		// Setting labels for input fields
		lb1 = new JLabel(messages[2]);

		// Setting GridLayout to main panel
		// Default grid constructor which sets one column per component, in a single row
		mainPanel.setLayout(new GridLayout());
		// Adding "elements" to main panel
		// Note: Addition ordering matters
		mainPanel.add(lb1); // 1,1
		mainPanel.add(tf1); // 1,2

		buttonsPanel.add(bt1);
		buttonsPanel.add(bt2);

		// Adding all panels to the content panel
		// Notice the border that each panel is added in
		contentPanel.add(mainPanel, BorderLayout.CENTER);
		contentPanel.add(buttonsPanel, BorderLayout.SOUTH);

		// Setting the X button (from the OS window) to hide this window
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		// Finally, fit to current window
		this.pack();
	}

	private void addCustomerToList(String[] messages) {
		// Adds a customer to the list through the service and in the GUI list
		String customerName = tf1.getText();
		boolean isAdded = customerService.addCustomer(customerName);
		if (!isAdded) {
			ErrorMessageGUI errorMessageGUI = new ErrorMessageGUI(messages[3]);
			errorMessageGUI.setVisible(true);
		} else {
			listModel.addElement(customerName);
		}
	}

}

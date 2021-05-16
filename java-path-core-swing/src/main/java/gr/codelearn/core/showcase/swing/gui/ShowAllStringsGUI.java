package gr.codelearn.core.showcase.swing.gui;

import gr.codelearn.core.showcase.swing.service.CustomerService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowAllStringsGUI extends JFrame {

	// Declare all components as attributes of your class
	private JPanel contentPanel;
	private JPanel buttonsPanel;
	private JButton bt1;
	private JButton bt2;
	// List that "manages" which strings are shown to the user
	private DefaultListModel<String> listModel;
	//Actual list component
	private JList list;

	private CustomerService customerService;

	public ShowAllStringsGUI(CustomerService customerService, String[] messages) {
		// Initializes a service and a GUI list for adding a string
		listModel = new DefaultListModel<>();
		this.customerService = customerService;
		// Adds each string to the gui list
		for (String customer : customerService.getCustomerList()) {
			listModel.addElement(customer);
		}
		// Created from the parent class (JFrame)
		contentPanel = (JPanel) this.getContentPane();

		// Creating the panel that will later be added in a layout manager
		buttonsPanel = new JPanel();

		// Setting title for window
		this.setTitle(messages[0]);

		// Creating button and setting its text and listener (through an anonymous class)
		bt1 = new JButton("Exit");
		bt1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		// Creating button and setting its text and listener (through an anonymous class)
		bt2 = new JButton(messages[1]);
		bt2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AddNewStringGUI addNewStringGUI = new AddNewStringGUI(ShowAllStringsGUI.this.customerService, listModel,
																	  messages);
				addNewStringGUI.setVisible(true);
			}
		});

		// Adds the buttons to the button panel
		buttonsPanel.add(bt1);
		buttonsPanel.add(bt2);

		// Preparing list
		list = new JList<>(listModel);

		// Centralizing list
		DefaultListCellRenderer renderer = (DefaultListCellRenderer) list.getCellRenderer();
		renderer.setHorizontalAlignment(SwingConstants.CENTER);
		// Magic number (cannot be changed from anywhere else and decreases maintainability)
		list.setVisibleRowCount(10);
		JScrollPane listScrollPane = new JScrollPane(list);

		// Adding all panels to the content panel
		// Notice the border that each panel is added in
		contentPanel.add(listScrollPane, BorderLayout.CENTER);
		contentPanel.add(buttonsPanel, BorderLayout.SOUTH);

		// Setting the exit button to stop the application
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Finally, fit to current window automatically
		this.pack();
	}
}

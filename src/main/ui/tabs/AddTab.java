package ui.tabs;

import model.TrailLog;
import ui.ButtonNames;
import ui.TrailLogGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddTab extends Tab {

    private JTextField nameField;
    private JTextField locationField;
    private JTextField distanceField;
    private JButton addButton;

    // EFFECTS: constructs the Add tab for adding trails
    public AddTab(TrailLogGUI controller) {
        super(controller);
        setLayout(new GridLayout(5, 1));

        // Place input fields and button
        placeForm();
        placeAddButton();
    }

    // MODIFIES: this
    // EFFECTS: places input fields for name, location, and distance
    private void placeForm() {
        JLabel nameLabel = new JLabel("Enter the name of the trail:");
        nameField = new JTextField();

        JLabel locationLabel = new JLabel("Enter the location of the trail:");
        locationField = new JTextField();

        JLabel distanceLabel = new JLabel("Enter the distance of the trail (in km):");
        distanceField = new JTextField();

        // Add components to the tab
        this.add(nameLabel);
        this.add(nameField);
        this.add(locationLabel);
        this.add(locationField);
        this.add(distanceLabel);
        this.add(distanceField);
    }

    // MODIFIES: this
    // EFFECTS: places the "Add Trail" button and sets up its functionality
    private void placeAddButton() {
        addButton = new JButton(ButtonNames.ADD.getValue());
        this.add(addButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Read user inputs
                    String name = nameField.getText();
                    String location = locationField.getText();
                    double distance = Double.parseDouble(distanceField.getText());

                    // Add the trail to the trail log
                    TrailLog trailLog = getController().getTrailLog();
                    trailLog.logAdder(name, location, distance);

                    // Notify user
                    JOptionPane.showMessageDialog(AddTab.this, "Trail added successfully!");

                    // Clear the input fields
                    nameField.setText("");
                    locationField.setText("");
                    distanceField.setText("");

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(AddTab.this, 
                        "Please enter a valid distance!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}

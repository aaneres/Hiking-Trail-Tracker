package ui.tabs;

import model.TrailLog;
import ui.ButtonNames;
import ui.TrailLogGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// A tab for adding a new trail to the Trail Log
public class AddTab extends Tab {

    private JTextField nameField;
    private JTextField locationField;
    private JTextField distanceField;
    private JButton addButton;

    // EFFECTS: constructs the Add tab for adding trails
    public AddTab(TrailLogGUI controller) {
        super(controller);
        setLayout(new GridLayout(5, 1));
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
                    String name = nameField.getText();
                    String location = locationField.getText();
                    double distance = Double.parseDouble(distanceField.getText());
                    TrailLog trailLog = getController().getTrailLog();

                    trailLog.logAdder(name, location, distance);
                    JOptionPane.showMessageDialog(AddTab.this, "Trail added successfully!");
                    getController().refreshChartTab();

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

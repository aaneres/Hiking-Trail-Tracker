package ui.tabs;

import ui.ButtonNames;
import ui.TrailLogGUI;

import javax.swing.*;

// A tab for changing the completion of a trail
public class ChangeTab extends Tab {

    private String currentTrailName; // Holds the current trail being updated

    // EFFECTS: constructs the Change tab for changing completion of trails
    public ChangeTab(TrailLogGUI controller) {
        super(controller);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    
        JLabel instruction = new JLabel("Enter the name of the trail to change its completion status:");
        JTextField trailNameField = createTrailNameField();
        JTextField dateField = createDateField();
        JLabel resultLabel = new JLabel();
        JButton changeButton = createChangeButton(trailNameField, dateField, resultLabel);
    
        dateField.addActionListener(e -> handleDateFieldInput(dateField, resultLabel));

        add(instruction);
        add(trailNameField);
        add(dateField);
        add(changeButton);
        add(resultLabel);
    }
    
    // EFFECTS: creates a new Trail Name Field
    private JTextField createTrailNameField() {
        return new JTextField(15);
    }
    
    // EFFECTS: creates a field for the date that is initially not visible
    private JTextField createDateField() {
        JTextField dateField = new JTextField(15);
        dateField.setVisible(false);
        return dateField;
    }
    
    // EFFECTS: creates a change button that deals with the action of being pushed
    private JButton createChangeButton(JTextField trailNameField, JTextField dateField, JLabel resultLabel) {
        JButton changeButton = new JButton(ButtonNames.CHANGE.getValue());
        changeButton.addActionListener(e -> handleTrailChange(trailNameField, dateField, resultLabel));
        return changeButton;
    }
    
    // MODIFIES: this
    // EFFECTS: handles the changing of the completion status
    private void handleTrailChange(JTextField trailNameField, JTextField dateField, JLabel resultLabel) {
        String trailName = trailNameField.getText();
        Boolean result = getController().getTrailLog().logCompletionChanger(trailName);
    
        if (result == null) {
            resultLabel.setText("Trail not found!");
            dateField.setVisible(false);
        } else if (result) {
            currentTrailName = trailName; 
            resultLabel.setText("Trail marked as completed. Enter the completion date and hit ENTER:");
            dateField.setVisible(true);
            dateField.setText(""); 
        } else {
            resultLabel.setText("Trail marked as not complete!");
            dateField.setVisible(false);
        }
    }
    
    // EFFECTS: handles the completion date input
    private void handleDateFieldInput(JTextField dateField, JLabel resultLabel) {
        String date = dateField.getText();
        if (currentTrailName != null && !date.isEmpty()) {
            getController().getTrailLog().logCompletionDate(currentTrailName, date);
            resultLabel.setText("Trail marked as completed and date recorded!");
            getController().refreshChartTab();
            dateField.setVisible(false);
        }
    }
}



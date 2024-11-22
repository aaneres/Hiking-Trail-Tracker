package ui.tabs;

import ui.ButtonNames;
import ui.TrailLogGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeTab extends Tab {
    public ChangeTab(TrailLogGUI controller) {
        super(controller);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel instruction = new JLabel("Enter the name of the trail to change its completion status:");
        JTextField trailNameField = new JTextField(15);
        JTextField dateField = new JTextField(15);
        dateField.setVisible(false); // Initially hidden
        JLabel resultLabel = new JLabel();
        JButton changeButton = new JButton(ButtonNames.CHANGE.getValue());

        changeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String trailName = trailNameField.getText();
                Boolean result = getController().getTrailLog().logCompletionChanger(trailName);

                if (result == null) {
                    resultLabel.setText("Trail not found!");
                } else if (result) {
                    dateField.setVisible(true);
                    dateField.setText("Enter the completion date (YYYY-MM-DD):");

                    dateField.addActionListener(event -> {
                        String date = dateField.getText();
                        getController().getTrailLog().logCompletionDate(trailName, date);
                        resultLabel.setText("Trail marked as completed and date recorded!");
                    });
                } else {
                    resultLabel.setText("Trail marked as not complete!");
                }
            }
        });

        add(instruction);
        add(trailNameField);
        add(dateField);
        add(changeButton);
        add(resultLabel);
    }
}


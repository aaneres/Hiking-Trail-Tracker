package ui.tabs;

import ui.ButtonNames;
import ui.TrailLogGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveTab extends Tab {
    public RemoveTab(TrailLogGUI controller) {
        super(controller);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel instruction = new JLabel("Enter the name of the trail to remove:");
        JTextField trailNameField = new JTextField(15);
        JButton removeButton = new JButton(ButtonNames.REMOVE.getValue());
        JLabel resultLabel = new JLabel();

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String trailName = trailNameField.getText();
                if (getController().getTrailLog().logRemover(trailName)) {
                    resultLabel.setText("Trail successfully removed!");
                } else {
                    resultLabel.setText("Trail not found ):");
                }
            }
        });

        add(instruction);
        add(trailNameField);
        add(removeButton);
        add(resultLabel);
    }
}
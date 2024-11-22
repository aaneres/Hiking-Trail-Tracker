package ui.tabs;

import model.Trail;
import ui.TrailLogGUI;

import javax.swing.*;
import java.awt.*;

// A tab for viewing the trails currently added to Trail Log
public class ViewTab extends Tab {

    private JTextArea trailsTextArea;

    // MODIFIES: this
    // EFFECTS: Constructs a tab where you can view all the trails in your log
    public ViewTab(TrailLogGUI controller) {
        super(controller);
        setLayout(new BorderLayout());

        JLabel headerLabel = new JLabel("Trails in your log:", JLabel.CENTER);
        trailsTextArea = new JTextArea();
        trailsTextArea.setEditable(false);
        JButton refreshButton = new JButton("Refresh");

        refreshButton.addActionListener(e -> displayTrails());

        add(headerLabel, BorderLayout.NORTH);
        add(new JScrollPane(trailsTextArea), BorderLayout.CENTER);
        add(refreshButton, BorderLayout.SOUTH);
    }

    // MODIFIES: this
    // EFFECTS: Prints all the trails in the log to the console
    private void displayTrails() {
        StringBuilder sb = new StringBuilder();
        if (getController().getTrailLog().getTrailList().isEmpty()) {
            sb.append("No trails in the log.");
        } else {
            for (Trail trail : getController().getTrailLog().getTrailList()) {
                sb.append("Trail Name: ").append(trail.getName()).append("\n")
                        .append("Location: ").append(trail.getLocation()).append("\n")
                        .append("Distance: ").append(trail.getDistance()).append(" km\n")
                        .append("Completed: ").append(trail.getCompletionStatus() ? "yes" : "no").append("\n")
                        .append("Date Completed: ").append(trail.getDateCompleted()).append("\n\n");
            }
        }
        trailsTextArea.setText(sb.toString());
    }
}

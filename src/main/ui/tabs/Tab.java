package ui.tabs;

import ui.TrailLogGUI;

import javax.swing.*;
import java.awt.*;

// Abstract class for Tabs
public abstract class Tab extends JPanel {

    private final TrailLogGUI controller;

    //REQUIRES: TrailLogGUI controller that holds this tab
    public Tab(TrailLogGUI controller) {
        this.controller = controller;
    }

    //EFFECTS: creates and returns row with button included
    public JPanel formatButtonRow(JButton b) {
        JPanel p = new JPanel();
        p.setLayout(new FlowLayout());
        p.add(b);

        return p;
    }

    //EFFECTS: returns the TrailLogGUI controller for this tab
    public TrailLogGUI getController() {
        return controller;
    }

}

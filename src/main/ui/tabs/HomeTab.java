package ui.tabs;

import ui.ButtonNames;
import ui.TrailLogGUI;

import javax.swing.*;

import persistence.JsonReader;
import persistence.JsonWriter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

// 
public class HomeTab extends Tab {
    private static final String INIT_GREETING = "Welcome to your Trail Log!";
    private JLabel greeting;
    private static final String JSON_STORE = "./data/traillog.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: constructs a home tab for console with buttons and a greeting
    public HomeTab(TrailLogGUI controller) {
        super(controller);

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        setLayout(new GridLayout(3, 1));

        placeGreeting();
        placeMainButtons();
        placeSaveButtons();
    }

    // EFFECTS: creates greeting at top of console
    private void placeGreeting() {
        greeting = new JLabel(INIT_GREETING, JLabel.CENTER);
        greeting.setSize(WIDTH, HEIGHT / 3);
        this.add(greeting);
    }

    // EFFECTS: creates Add, Remove, Change, and View buttons
    private void placeMainButtons() {
        JButton addButton = new JButton(ButtonNames.ADD.getValue());
        JButton removeButton = new JButton(ButtonNames.REMOVE.getValue());
        JButton changeButton = new JButton(ButtonNames.CHANGE.getValue());
        JButton viewButton = new JButton(ButtonNames.VIEW.getValue());

        JPanel buttonRow = formatButtonRow(addButton);
        buttonRow.add(removeButton);
        buttonRow.add(changeButton);
        buttonRow.add(viewButton);
        buttonRow.setSize(WIDTH, HEIGHT / 6);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonPressed = e.getActionCommand();
                if (buttonPressed.equals(ButtonNames.ADD.getValue())) {
                    getController().getTabbedPane().setSelectedIndex(TrailLogGUI.ADD_TAB_INDEX);
                }
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonPressed = e.getActionCommand();
                if (buttonPressed.equals(ButtonNames.REMOVE.getValue())) {
                    getController().getTabbedPane().setSelectedIndex(TrailLogGUI.REMOVE_TAB_INDEX);
                }
            }
        });

        changeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonPressed = e.getActionCommand();
                if (buttonPressed.equals(ButtonNames.CHANGE.getValue())) {
                    getController().getTabbedPane().setSelectedIndex(TrailLogGUI.CHANGE_TAB_INDEX);
                }
            }
        });

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonPressed = e.getActionCommand();
                if (buttonPressed.equals(ButtonNames.VIEW.getValue())) {
                    getController().getTabbedPane().setSelectedIndex(TrailLogGUI.VIEW_TAB_INDEX);
                }
            }
        });

        this.add(buttonRow);
    }

    // EFFECTS: creates Save and Load buttons
    private void placeSaveButtons() {
        JButton b1 = new JButton(ButtonNames.SAVE.getValue());
        JButton b2 = new JButton(ButtonNames.LOAD.getValue());

        JPanel buttonRow = formatButtonRow(b1);
        buttonRow.add(b2);
        buttonRow.setSize(WIDTH, HEIGHT / 6);

        // Referenced from the JsonSerialization Demo
        // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
        // EFFECTS: saves the TrailLog to file, catches FileNotFoundException if
        // destination file cannot be written in
        b1.addActionListener(e -> {
            try {
                jsonWriter.open();
                jsonWriter.write(getController().getTrailLog());
                jsonWriter.close();
                greeting.setText("Saved your trail log to " + JSON_STORE);
            } catch (FileNotFoundException f) {
                greeting.setText("Unable to write to file: " + JSON_STORE);
            }
        });

        b2.addActionListener(e -> {
            try {
                getController().setTrailLog(jsonReader.read());
                greeting.setText("Loaded trail log from " + JSON_STORE);
            } catch (IOException f) {
                greeting.setText("Unable to read from file: " + JSON_STORE);
            }
        });

        this.add(buttonRow);
    }
}

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

// A home tab that where you can choose what to do with your trail log
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

    // MODIFIES: this
    // EFFECTS: creates greeting at top of console
    private void placeGreeting() {
        greeting = new JLabel(INIT_GREETING, JLabel.CENTER);
        greeting.setSize(WIDTH, HEIGHT / 3);
        this.add(greeting);
    }

    // MODIFIES: this
    // EFFECTS: creates Add, Remove, Change, and View buttons
    private void placeMainButtons() {
        JPanel buttonRow = formatButtonRow(createMainButton(ButtonNames.ADD));
        buttonRow.add(createMainButton(ButtonNames.REMOVE));
        buttonRow.add(createMainButton(ButtonNames.CHANGE));
        buttonRow.add(createMainButton(ButtonNames.VIEW));
        buttonRow.add(createMainButton(ButtonNames.CHART));
        buttonRow.setSize(WIDTH, HEIGHT / 6);
        this.add(buttonRow);
    }

    // EFFECTS: Creates a button with given value
    private JButton createMainButton(ButtonNames buttonName) {
        JButton button = new JButton(buttonName.getValue());
        button.addActionListener(e -> switchTab(buttonName));
        return button;
    }

    // EFFECTS: Handles each button being pressed and switches tab accordingly
    private void switchTab(ButtonNames buttonName) {
        int tabIndex;
        switch (buttonName) {
            case ADD:
                tabIndex = TrailLogGUI.ADD_TAB_INDEX;
                break;
            case REMOVE:
                tabIndex = TrailLogGUI.REMOVE_TAB_INDEX;
                break;
            case CHANGE:
                tabIndex = TrailLogGUI.CHANGE_TAB_INDEX;
                break;
            case VIEW:
                tabIndex = TrailLogGUI.VIEW_TAB_INDEX;
                break;
            case CHART:
                tabIndex = TrailLogGUI.CHART_TAB_INDEX;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + buttonName);
        }
        getController().getTabbedPane().setSelectedIndex(tabIndex);
    }

    // MODIFIES: this
    // EFFECTS: Places Save and Load buttons
    private void placeSaveButtons() {
        JPanel buttonRow = formatButtonRow(createSaveButton());
        buttonRow.add(createLoadButton());
        buttonRow.setSize(WIDTH, HEIGHT / 6);
        this.add(buttonRow);
    }

    // EFFECTS: creates a button that saves trails in trail log
    private JButton createSaveButton() {
        JButton saveButton = new JButton(ButtonNames.SAVE.getValue());
        saveButton.addActionListener(e -> saveTrailLog());
        return saveButton;
    }

    // EFFECTS: creates a button that loads trails from file
    private JButton createLoadButton() {
        JButton loadButton = new JButton(ButtonNames.LOAD.getValue());
        loadButton.addActionListener(e -> loadTrailLog());
        return loadButton;
    }

    // MODIFIES: this
    // EFFECTS: saves the trail log to the storage file
    private void saveTrailLog() {
        try {
            jsonWriter.open();
            jsonWriter.write(getController().getTrailLog());
            jsonWriter.close();
            greeting.setText("Saved your trail log to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            greeting.setText("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads the trail log from the storage file
    private void loadTrailLog() {
        try {
            getController().setTrailLog(jsonReader.read());
            greeting.setText("Loaded trail log from " + JSON_STORE);
            getController().refreshChartTab();
        } catch (IOException e) {
            greeting.setText("Unable to read from file: " + JSON_STORE);
        }
    }

}

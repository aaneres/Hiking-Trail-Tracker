package ui;

import javax.swing.*;

import model.TrailLog;
import ui.tabs.*;

public class TrailLogGUI extends JFrame {
    public static final int HOME_TAB_INDEX = 0;
    public static final int ADD_TAB_INDEX = 1;
    public static final int REMOVE_TAB_INDEX = 2;
    public static final int CHANGE_TAB_INDEX = 3;
    public static final int VIEW_TAB_INDEX = 4;

    public static final int WIDTH = 700;
    public static final int HEIGHT = 400;

    private JTabbedPane sidebar;
    private TrailLog trailLog;

    // MODIFIES: this
    // EFFECTS: creates TrailLogGUI, displays sidebar and tabs
    public TrailLogGUI() {
        super("TrailLog");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        trailLog = new TrailLog();

        sidebar = new JTabbedPane();
        sidebar.setTabPlacement(JTabbedPane.LEFT);

        loadTabs();
        add(sidebar);

        setVisible(true);
    }

    // EFFECTS: returns TrailLog object controlled by this UI
    public TrailLog getTrailLog() {
        return trailLog;
    }

    // EFFECTS: sets TrailLog object controlled by this UI
    public void setTrailLog(TrailLog trailLog) {
        this.trailLog = trailLog;
    }

    // MODIFIES: this
    // EFFECTS: adds home tab and save tab to this UI
    private void loadTabs() {
        JPanel homeTab = new HomeTab(this);
        JPanel addTab = new AddTab(this);

        sidebar.add(homeTab, HOME_TAB_INDEX);
        sidebar.setTitleAt(HOME_TAB_INDEX, "Home");
        sidebar.add(addTab, ADD_TAB_INDEX);
        sidebar.setTitleAt(ADD_TAB_INDEX, "Add");
        sidebar.add(new RemoveTab(this), REMOVE_TAB_INDEX);
        sidebar.setTitleAt(REMOVE_TAB_INDEX, "Remove");
        sidebar.add(new ChangeTab(this), CHANGE_TAB_INDEX);
        sidebar.setTitleAt(CHANGE_TAB_INDEX, "Change");
        sidebar.add(new ViewTab(this), VIEW_TAB_INDEX);
        sidebar.setTitleAt(VIEW_TAB_INDEX, "View");
    }

    // EFFECTS: returns sidebar of this UI
    public JTabbedPane getTabbedPane() {
        return sidebar;
    }
}

package ui;

import model.Trail;
import model.TrailLog;

import java.util.Scanner;

// User interface for representing a log of Trails 
public class TrailLogApp {
    private TrailLog trailLog;
    private Scanner input;

    // EFFECTS: runs the TrailLog application
    public TrailLogApp() {
        runTrailLog();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runTrailLog() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("a")) {
            addTrail();
        } else if (command.equals("r")) {
            removeTrail();
        } else if (command.equals("c")) {
            changeTrailCompletion();
        } else if (command.equals("v")) {
            viewTrails();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes the list of trails and scanner
    private void init() {
        trailLog = new TrailLog();
        input = new Scanner(System.in);
        input.useDelimiter("\r?\n|\r");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add new trail");
        System.out.println("\tr -> remove a trail");
        System.out.println("\tc -> change a trail's completion status");
        System.out.println("\tv -> view all trails");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: adds a user-specified Trail to the list of trails
    private void addTrail() {
        System.out.println("Enter the name of the trail:");
        String name = input.next();
        System.out.println("Enter the location of the trail:");
        String location = input.next();
        System.out.println("Enter the distance of the trail (in km):");
        double distance = input.nextDouble();

        trailLog.logAdder(name, location, distance);

        System.out.println("Trail added!");
    }

    // MODIFIES: this
    // EFFECTS: removes a user-specified Trail by name from the list of trails
    private void removeTrail() {
        System.out.println("Enter the name of the trail you want to remove:");
        String name = input.next();
        if (trailLog.logRemover(name)) {
            System.out.println("Trail was successfully removed!");
        } else {
            System.out.println("Trail was not found ):");
        }

    }

    // MODIFIES: this
    // EFFECTS: changes a chosen trail's completion status and date completed.
    // changes status to completed + sets date if trail is currently not completed
    // changes status to not complete + resets date if trail is currently completed
    private void changeTrailCompletion() {
        System.out.println("Enter the name of the trail you want to change the completion status of:");
        String name = input.next();

        Boolean result = trailLog.logCompletionChanger(name);
        if (result == null) {
            System.out.println("Trail not found!");
        } else if (result) {
            System.out.println("Marking trail as completed...");
            System.out.println("When did you complete this trail?");
            String date = input.next();
            trailLog.logCompletionDate(name, date);
            System.out.println("Trail successfully completed, date successfully recorded!");
        } else {
            System.out.println("Trail marked not complete!");
        }
    }

    // EFFECTS: displays all trails in the list of trails
    private void viewTrails() {
        if (trailLog.getTrailList().isEmpty()) {
            System.out.println("No trails in the log.");
        } else {
            System.out.println("Trails in your log:");
            for (Trail trail : trailLog.getTrailList()) {
                System.out.println("Trail Name: " + trail.getName());
                System.out.println("Location: " + trail.getLocation());
                System.out.println("Distance: " + trail.getDistance() + " km");
                System.out.println("Completed: " + (trail.getCompletionStatus() ? "yes" : "no"));
                System.out.println("Date Completed: " + trail.getDateCompleted());
                System.out.println();
            }
        }
    }
}

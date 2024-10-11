package model;

import java.util.ArrayList;

// Represents a log of trails that individual Trails are added to.
public class TrailLog {
    private ArrayList<Trail> trails;

    public ArrayList<Trail> getTrails() {
        return trails;
    }

    // MODIFIES: this
    // EFFECTS: adds a Trail to the TrailLog
    public void logAdder() {
        //stub
    }

    // MODIFIES: this
    // EFFECTS: looks for a Trail in the TrailLog by name 
    // returns true if trail is found and removed, false otherwise
    public boolean logRemover(Trail name) {
        //stub
        return false;
    }

    // MODIFIES: this
    // EFFECTS: changes a Trail in the TrailLog's completion status
    public void logCompletionChanger() {
        //stub
    }

    // MODIFIES: this
    // EFFECTS: changes a Trail in the TrailLog's completion status to complete
    public void logCompletor() {
        //stub
    }

    // MODIFIES: this
    // EFFECTS: sets a Trail in the TrailLog's completion date
    public void logDate() {
        //stub
    }

    // MODIFIES: this
    // EFFECTS: changes a Trail in the TrailLog's completion status to not complete
    public void logUncompletor() {
        //stub
    }

    // MODIFIES: this
    // EFFECTS: sets a Trail in the TrailLog's completion date to not complete
    public void logDateResetter() {
        //stub
    }
}

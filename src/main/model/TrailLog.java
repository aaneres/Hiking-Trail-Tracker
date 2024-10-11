package model;

import java.util.ArrayList;

// Represents a log of trails that individual Trails are added to.
public class TrailLog {
    private Trail currentTrail;
    private ArrayList<Trail> trailList; // List of trails

    public TrailLog() {
        this.trailList = new ArrayList<>();
    }

    public ArrayList<Trail> getTrailList() {
        return trailList;
    }

    // REQUIRES: a name and location that are not length zero; a non-zero/positive distance
    // MODIFIES: this
    // EFFECTS: adds a Trail to the TrailLog
    public void logAdder(String name, String location, double distance) {
        currentTrail = new Trail(name, location, distance);
        //stub
    }

    // REQUIRES: a name of non-zero length
    // MODIFIES: this
    // EFFECTS: looks for a Trail in the TrailLog by name (String)
    // returns true if trail is found and removed, false otherwise
    public boolean logRemover(String name) {
        //stub
        return false;
    }

    // REQUIRES: a name of non-zero length
    // MODIFIES: this
    // EFFECTS: changes a Trail in the TrailLog's completion status
    public void logCompletionChanger(String name) {
        //stub
    }
}

package model;

import java.util.ArrayList;

// Represents a log of trails that individual Trails are added to.
public class TrailLog {
    private Trail currentTrail;
    private ArrayList<Trail> trailList; // List of trails

    // EFFECTS: trailList is set to a new empty ArrayList.
    public TrailLog() {
        this.trailList = new ArrayList<>();
    }

    public ArrayList<Trail> getTrailList() {
        return trailList;
    }

    // EFFECTS: returns number of trails in this trailList
    public int numTrails() {
        return trailList.size();
    }

    // REQUIRES: a name and location that are not length zero; a non-zero/positive distance
    // MODIFIES: this
    // EFFECTS: adds a Trail to the TrailLog
    public void logAdder(String name, String location, double distance) {
        currentTrail = new Trail(name, location, distance);
        trailList.add(currentTrail);
    }

    // REQUIRES: a name and location that are not length zero; a non-zero/positive distance
    // MODIFIES: this
    // EFFECTS: adds a Trail to the TrailLog
    public void logAdder(String name, String location, double distance, boolean completed, String dateCompleted) {
        currentTrail = new Trail(name, location, distance, completed, dateCompleted);
        trailList.add(currentTrail);
    }

    // REQUIRES: a name of non-zero length
    // MODIFIES: this
    // EFFECTS: looks for a Trail in the TrailLog by name (String)
    // returns true if trail is found and removed, false otherwise
    public boolean logRemover(String name) {
        for (int i = 0; i < trailList.size(); i++) {
            if (trailList.get(i).getName().equals(name)) {
                trailList.remove(i);
                return true;
            }
        }
        return false;
    }

    // REQUIRES: a name of non-zero length
    // MODIFIES: this
    // EFFECTS: changes a Trail in the TrailLog's completion status; returns null if not found
    // returns true if trail is marked completed; returns false if marked not complete
    public Boolean logCompletionChanger(String name) {
        for (int i = 0; i < trailList.size(); i++) {
            if (trailList.get(i).getName().equals(name)) {
                if (!trailList.get(i).getCompletionStatus()) {
                    trailList.get(i).markCompleted();
                    return true;
                } else {
                    trailList.get(i).markNotCompleted();
                    trailList.get(i).resetDate();
                    return false;
                }
            }
        }
        return null;
    }

    // REQUIRES: a name of non-zero length, a date of non-zero length
    // MODIFIES: this
    // EFFECTS: changes a Trail in the TrailLog's completion date; returns null if not found
    public Boolean logCompletionDate(String name, String date) {
        for (int i = 0; i < trailList.size(); i++) {
            if (trailList.get(i).getName().equals(name)) {
                trailList.get(i).setDate(date);
                break;
            }
        }
        return null;
    }
}

package model;

import org.json.JSONObject;

import persistence.Writable;

// Represents a trail having a name, location, distance, completion and completion date
public class Trail implements Writable {
    private String name;            // trail name
    private String location;        // location of trail
    private double distance;        // distance in km
    private boolean completed;      // returns true if trail is completed
    private String dateCompleted;   // date trail was completed

    /*
     * REQUIRES: trailName and trailLocation have a non-zero length
     *           trailDistance is not zero or negative
     * EFFECTS: name is set to trailName; location is set to trailLocation; 
     *          distance is set to trailDistance; completed is set to false;
     *          dateCompleted is set to "not completed"
     */
    public Trail(String name, String location, double distance) {
        this.name = name;
        this.location = location;
        this.distance = distance;
        this.completed = false;
        this.dateCompleted = "not completed";
    }

    public Trail(String name, String location, double distance, boolean cd, String date) {
        this.name = name;
        this.location = location;
        this.distance = distance;
        this.completed = cd;
        this.dateCompleted = date;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public double getDistance() {
        return distance;
    }

    public Boolean getCompletionStatus() {
        return completed;
    }

    public String getDateCompleted() {
        return dateCompleted;
    }

    /*
     * MODIFIES: this
     * EFFECTS: sets completion status to completed
     */
    public boolean markCompleted() {
        this.completed = true;
        return this.completed;
    }

    /*
     * MODIFIES: this
     * EFFECTS: sets completion status to not completed
     */
    public boolean markNotCompleted() {
        this.completed = false;
        return this.completed;
    }

    /*
     * REQUIRES: date has a non-zero length
     * MODIFIES: this
     * EFFECTS: sets completion date to date given
     */
    public String setDate(String date) {
        this.dateCompleted = date;
        return dateCompleted;
    }

    /*
     * MODIFIES: this
     * EFFECTS: resets completion date to not completed
     */
    public String resetDate() {
        this.dateCompleted = "not completed";
        return dateCompleted;
    }

    // Referenced from the JsonSerialization Demo
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("location", location);
        json.put("distance", distance);
        json.put("completed", completed);
        json.put("dateCompleted", dateCompleted);
        return json;
    }
}

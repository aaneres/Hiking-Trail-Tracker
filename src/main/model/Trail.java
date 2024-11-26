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
     * REQUIRES: name and location have a non-zero length
     *           distance is not zero or negative
     * EFFECTS: this.name is set to name; this.location is set to location; 
     *          this.distance is set to distance; this.completed is set to false;
     *          this.dateCompleted is set to "not completed"
     */
    public Trail(String name, String location, double distance) {
        this.name = name;
        this.location = location;
        this.distance = distance;
        this.completed = false;
        this.dateCompleted = "not completed";
    }

    /*
     * REQUIRES: name and location have a non-zero length
     *           distance is not zero or negative
     * EFFECTS: this.name is set to name; this.location is set to location; 
     *          this.distance is set to distance; this.completed is set to cd;
     *          this.dateCompleted is set to date
     */
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
        EventLog.getInstance().logEvent(new Event("Trail set to completed"));
        return this.completed;
    }

    /*
     * MODIFIES: this
     * EFFECTS: sets completion status to not completed
     */
    public boolean markNotCompleted() {
        this.completed = false;
        EventLog.getInstance().logEvent(new Event("Trail set to not completed"));
        return this.completed;
    }

    /*
     * REQUIRES: date has a non-zero length
     * MODIFIES: this
     * EFFECTS: sets completion date to date given
     */
    public String setDate(String date) {
        this.dateCompleted = date;
        EventLog.getInstance().logEvent(new Event("Trail completion date set"));
        return dateCompleted;
    }

    /*
     * MODIFIES: this
     * EFFECTS: resets completion date to not completed
     */
    public String resetDate() {
        this.dateCompleted = "not completed";
        EventLog.getInstance().logEvent(new Event("Trail completion date reset"));
        return dateCompleted;
    }

    // Referenced from the JsonSerialization Demo
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: returns a new JSONObject with the Trail's information
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("location", location);
        json.put("distance", distance);
        json.put("completed", completed);
        json.put("dateCompleted", dateCompleted);
        EventLog.getInstance().logEvent(new Event("new JSONObject with Trail info created"));
        return json;
    }
}

package model;

// Represents a trail having a name, location, distance, completion and completion date
public class Trail {
    private String name;            // trail name
    private String location;        // location of trail
    private double distance;           // distance in km
    private boolean completed;      // whether trail was completed
    private String dateCompleted;   // date trail was completed

    /*
     * REQUIRES: trailName and trailLocation have a non-zero length
     *           trailDistance is not zero or negative
     * EFFECTS: name is set to trailName; location is set to trailLocation; 
     *          distance is set to trailDistance; completed is set to false;
     *          dateCompleted is set to "not completed!"
     */
    public Trail(String trailName, String trailLocation, double trailDistance) {
        this.name = trailName;
        this.location = trailLocation;
        this.distance = trailDistance;
        completed = false;
        dateCompleted = "not completed!";
    }

    /*
     * MODIFIES: this
     * EFFECTS: sets completion status to completed
     */
    public boolean markCompleted() {
        // stub
        return this.completed;
    }

    /*
     * MODIFIES: this
     * EFFECTS: sets completion status to not completed
     */
    public boolean markNotCompleted() {
        // stub
        return this.completed;
    }

    /*
     * REQUIRES: date has a non-zero length
     * MODIFIES: this
     * EFFECTS: sets completion date to date given
     */
    public String setDate(String date) {
        // stub
        return dateCompleted;
    }
}

package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import model.Trail;

// Referenced from the JsonSerialization Demo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonTest {
    protected void checkTrail(String name, String location, double distance, boolean cd, String date, Trail trail) {
        assertEquals(name, trail.getName());
        assertEquals(location, trail.getLocation());
        assertEquals(distance, trail.getDistance());
        assertEquals(cd, trail.getCompletionStatus());
        assertEquals(date, trail.getDateCompleted());
    }
}

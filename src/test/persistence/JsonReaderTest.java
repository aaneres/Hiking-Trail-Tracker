package persistence;

import org.junit.jupiter.api.Test;

import model.Trail;
import model.TrailLog;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Referenced from the JsonSerialization Demo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonReaderTest extends JsonTest {
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            TrailLog tl = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyTrailLog() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyTrailLog.json");
        try {
            TrailLog tl = reader.read();
            assertEquals(0, tl.numTrails());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralTrailLog() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralTrailLog.json");
        try {
            TrailLog tl = reader.read();
            List<Trail> trailList = tl.getTrailList();
            assertEquals(2, trailList.size());
            checkTrail("Grouse Grind", "Grouse", 1.9, false, "not completed", trailList.get(0));
            checkTrail("Brew Hut", "Mount Brew", 22.4, true, "Oct 21, 2023", trailList.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}

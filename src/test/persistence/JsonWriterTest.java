package persistence;

import org.junit.jupiter.api.Test;

import model.Trail;
import model.TrailLog;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Referenced from the JsonSerialization Demo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            TrailLog tl = new TrailLog();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            TrailLog tl = new TrailLog();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyTrailLog.json");
            writer.open();
            writer.write(tl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyTrailLog.json");
            tl = reader.read();
            assertEquals(0, tl.numTrails());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralTrailLog() {
        try {
            TrailLog tl = new TrailLog();
            tl.logAdder("Grouse Grind", "Grouse", 1.9, false, "not completed");
            tl.logAdder("Brew Hut", "Mount Brew", 22.4, true, "Oct 21, 2023");
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralTrailLog.json");
            writer.open();
            writer.write(tl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralTrailLog.json");
            tl = reader.read();
            List<Trail> trailList = tl.getTrailList();
            assertEquals(2, trailList.size());
            checkTrail("Grouse Grind", "Grouse", 1.9, false, "not completed", trailList.get(0));
            checkTrail("Brew Hut", "Mount Brew", 22.4, true, "Oct 21, 2023", trailList.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}

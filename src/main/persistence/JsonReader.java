package persistence;

import model.TrailLog;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Referenced from the JsonSerialization Demo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads traillog from file and returns it;
    // throws IOException if an error occurs reading data from file
    public TrailLog read() throws IOException {
        TrailLog tl = new TrailLog();
        return tl;
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        return source;
    }

    // EFFECTS: parses traillog from JSON object and returns it
    private TrailLog parseWorkRoom(JSONObject jsonObject) {
        TrailLog tl = new TrailLog();
        return tl;
    }

    // MODIFIES: wr
    // EFFECTS: parses trails from JSON object and adds them to traillog
    private void addTrails(TrailLog tl, JSONObject jsonObject) {

    }

    // MODIFIES: wr
    // EFFECTS: parses trail from JSON object and adds it to traillog
    private void addTrail(TrailLog tl, JSONObject jsonObject) {

    }
}

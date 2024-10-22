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

// Represents a reader that reads TrailLog from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads TrailLog from file and returns it;
    // throws IOException if an error occurs reading data from file
    public TrailLog read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseTrailLog(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses TrailLog from JSON object and returns it
    private TrailLog parseTrailLog(JSONObject jsonObject) {
        TrailLog tl = new TrailLog();
        addTrails(tl, jsonObject);
        return tl;
    }

    // MODIFIES: tl
    // EFFECTS: parses Trails from JSON object and adds them to TrailLog
    private void addTrails(TrailLog tl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("trailList");
        for (Object json : jsonArray) {
            JSONObject nextTrail = (JSONObject) json;
            addTrail(tl, nextTrail);
        }
    }

    // MODIFIES: tl
    // EFFECTS: parses Trail from JSON object and adds it to TrailLog
    private void addTrail(TrailLog tl, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String location = jsonObject.getString("location");
        double distance = jsonObject.getDouble("distance");
        Boolean completed = jsonObject.getBoolean("completed");
        String dateCompleted = jsonObject.getString("dateCompleted");
        tl.logAdder(name, location, distance, completed, dateCompleted);
    }
}

package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrailLogTest {
    private TrailLog testTrailLog;

    @BeforeEach
    void runBefore() {
        testTrailLog = new TrailLog();
    }

    @Test
    void testConstructor() {
        assertTrue(testTrailLog.getTrailList().isEmpty());
    }

    @Test
    void testLogAdder() {
        testTrailLog.logAdder("Plain of Six Glaciers", "Banff", 14.6);
        assertEquals(testTrailLog.getTrailList().get(0).getName(), "Plain of Six Glaciers");
        assertEquals(testTrailLog.getTrailList().get(0).getLocation(), "Banff");
        assertEquals(testTrailLog.getTrailList().get(0).getDistance(), 14.6);
        assertEquals(1, testTrailLog.getTrailList().size());
    }

    @Test
    void testLogAdderMultiple() {
        testTrailLog.logAdder("Plain of Six Glaciers", "Banff", 14.6);
        testTrailLog.logAdder("Stawamus Chief", "Squamish", 5.8);
        testTrailLog.logAdder("Half Dome", "Yosemite", 26.6);
        assertEquals(testTrailLog.getTrailList().get(0).getName(), "Plain of Six Glaciers");
        assertEquals(testTrailLog.getTrailList().get(0).getLocation(), "Banff");
        assertEquals(testTrailLog.getTrailList().get(0).getDistance(), 14.6);
        assertEquals(testTrailLog.getTrailList().get(1).getName(), "Stawamus Chief");
        assertEquals(testTrailLog.getTrailList().get(1).getLocation(), "Squamish");
        assertEquals(testTrailLog.getTrailList().get(1).getDistance(), 5.8);
        assertEquals(testTrailLog.getTrailList().get(2).getName(), "Half Dome");
        assertEquals(testTrailLog.getTrailList().get(2).getLocation(), "Yosemite");
        assertEquals(testTrailLog.getTrailList().get(2).getDistance(), 26.6);
        assertEquals(3, testTrailLog.getTrailList().size());
    }

    @Test
    void testLogRemover() {
        testTrailLog.logAdder("Plain of Six Glaciers", "Banff", 14.6);
        testTrailLog.logRemover("Plain of Six Glaciers");
        assertTrue(testTrailLog.getTrailList().isEmpty());
    }

    @Test
    void testLogRemoverMultiple() {
        testTrailLog.logAdder("Plain of Six Glaciers", "Banff", 14.6);
        testTrailLog.logAdder("Stawamus Chief", "Squamish", 5.8);
        testTrailLog.logAdder("Half Dome", "Yosemite", 26.6);
        testTrailLog.logRemover("Stawamus Chief");
        assertEquals(testTrailLog.getTrailList().get(0).getName(), "Plain of Six Glaciers");
        assertEquals(testTrailLog.getTrailList().get(0).getLocation(), "Banff");
        assertEquals(testTrailLog.getTrailList().get(0).getDistance(), 14.6);
        assertEquals(testTrailLog.getTrailList().get(1).getName(), "Half Dome");
        assertEquals(testTrailLog.getTrailList().get(1).getLocation(), "Yosemite");
        assertEquals(testTrailLog.getTrailList().get(1).getDistance(), 26.6);
        assertEquals(2, testTrailLog.getTrailList().size());

        testTrailLog.logRemover("Half Dome");
        assertEquals(testTrailLog.getTrailList().get(0).getName(), "Plain of Six Glaciers");
        assertEquals(testTrailLog.getTrailList().get(0).getLocation(), "Banff");
        assertEquals(testTrailLog.getTrailList().get(0).getDistance(), 14.6);
        assertEquals(1, testTrailLog.getTrailList().size());

        testTrailLog.logRemover("Plain of Six Glaciers");
        assertTrue(testTrailLog.getTrailList().isEmpty());
    }

    @Test
    void testLogRemoverFails() {
        testTrailLog.logAdder("Plain of Six Glaciers", "Banff", 14.6);
        testTrailLog.logAdder("Stawamus Chief", "Squamish", 5.8);
        testTrailLog.logAdder("Half Dome", "Yosemite", 26.6);
        testTrailLog.logRemover("Trail Not In List");
        assertEquals(3, testTrailLog.getTrailList().size());
    }

    @Test
    void testLogRemoverAndAdder() {
        testTrailLog.logAdder("Plain of Six Glaciers", "Banff", 14.6);
        testTrailLog.logAdder("Stawamus Chief", "Squamish", 5.8);
        testTrailLog.logAdder("Half Dome", "Yosemite", 26.6);
        testTrailLog.logRemover("Stawamus Chief");
        assertEquals(testTrailLog.getTrailList().get(0).getName(), "Plain of Six Glaciers");
        assertEquals(testTrailLog.getTrailList().get(0).getLocation(), "Banff");
        assertEquals(testTrailLog.getTrailList().get(0).getDistance(), 14.6);
        assertEquals(testTrailLog.getTrailList().get(1).getName(), "Half Dome");
        assertEquals(testTrailLog.getTrailList().get(1).getLocation(), "Yosemite");
        assertEquals(testTrailLog.getTrailList().get(1).getDistance(), 26.6);
        assertEquals(2, testTrailLog.getTrailList().size());

        testTrailLog.logAdder("Stawamus Chief", "Squamish", 5.8);
        assertEquals(testTrailLog.getTrailList().get(0).getName(), "Plain of Six Glaciers");
        assertEquals(testTrailLog.getTrailList().get(0).getLocation(), "Banff");
        assertEquals(testTrailLog.getTrailList().get(0).getDistance(), 14.6);
        assertEquals(testTrailLog.getTrailList().get(1).getName(), "Half Dome");
        assertEquals(testTrailLog.getTrailList().get(1).getLocation(), "Yosemite");
        assertEquals(testTrailLog.getTrailList().get(1).getDistance(), 26.6);
        assertEquals(testTrailLog.getTrailList().get(2).getName(), "Stawamus Chief");
        assertEquals(testTrailLog.getTrailList().get(2).getLocation(), "Squamish");
        assertEquals(testTrailLog.getTrailList().get(2).getDistance(), 5.8);
        assertEquals(3, testTrailLog.getTrailList().size());
    }

    @Test
    void testCompletionChanger() {
        testTrailLog.logAdder("Plain of Six Glaciers", "Banff", 14.6);
        testTrailLog.logCompletionChanger("Plain of Six Glaciers");
        assertTrue(testTrailLog.getTrailList().get(0).getCompletionStatus());
        testTrailLog.logCompletionChanger("Plain of Six Glaciers");
        assertFalse(testTrailLog.getTrailList().get(0).getCompletionStatus());
    }

    @Test
    void testCompletionChangerOnIndexTwo() {
        testTrailLog.logAdder("Plain of Six Glaciers", "Banff", 14.6);
        testTrailLog.logAdder("Stawamus Chief", "Squamish", 5.8);
        testTrailLog.logAdder("Half Dome", "Yosemite", 26.6);
        testTrailLog.logCompletionChanger("Half Dome");
        assertTrue(testTrailLog.getTrailList().get(2).getCompletionStatus());
        testTrailLog.logCompletionChanger("Half Dome");
        assertFalse(testTrailLog.getTrailList().get(2).getCompletionStatus());
    }

    @Test
    void testCompletionChangerFails() {
        testTrailLog.logAdder("Plain of Six Glaciers", "Banff", 14.6);
        testTrailLog.logAdder("Stawamus Chief", "Squamish", 5.8);
        testTrailLog.logAdder("Half Dome", "Yosemite", 26.6);
        assertNull(testTrailLog.logCompletionChanger("Not In List"));
    }

    @Test
    void testCompletionDate() {
        testTrailLog.logAdder("Plain of Six Glaciers", "Banff", 14.6);
        testTrailLog.logAdder("Stawamus Chief", "Squamish", 5.8);
        testTrailLog.logAdder("Half Dome", "Yosemite", 26.6);
        testTrailLog.logCompletionDate("Half Dome", "May 25");
        assertEquals("May 25", testTrailLog.getTrailList().get(2).getDateCompleted());
    }

    @Test
    void testCompletionDateFails() {
        testTrailLog.logAdder("Plain of Six Glaciers", "Banff", 14.6);
        testTrailLog.logAdder("Stawamus Chief", "Squamish", 5.8);
        testTrailLog.logAdder("Half Dome", "Yosemite", 26.6);
        assertNull(testTrailLog.logCompletionDate("Not In List", "Not Today"));
    }
}

package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrailTest {
    private Trail testTrail;
    
    @BeforeEach
    void runBefore() {
        testTrail = new Trail("Plain of Six Glaciers", "Banff", 14.6);
    }

    @Test
    void testConstructor() {
        assertEquals("Plain of Six Glaciers", testTrail.getName());
        assertEquals("Banff", testTrail.getLocation());
        assertEquals(14.6, testTrail.getDistance());
        assertFalse(testTrail.getCompletionStatus());
        assertEquals("not completed", testTrail.getDateCompleted());
    }

    @Test
    void testMarkCompleted() {
        testTrail.markCompleted();
        assertTrue(testTrail.getCompletionStatus());
    }

    @Test
    void testMultipleMarkCompleted() {
        testTrail.markCompleted();
        testTrail.markCompleted();
        testTrail.markCompleted();
        assertTrue(testTrail.getCompletionStatus());
    }

    @Test
    void testMarkNotCompleted() {
        testTrail.markNotCompleted();
        assertFalse(testTrail.getCompletionStatus());
    }

    @Test
    void testMultipleMarkNotCompleted() {
        testTrail.markNotCompleted();
        testTrail.markNotCompleted();
        testTrail.markNotCompleted();
        assertFalse(testTrail.getCompletionStatus());
    }

    @Test
    void testMarkCompletedThenMarkNotCompleted() {
        testTrail.markCompleted();
        testTrail.markNotCompleted();
        testTrail.markCompleted();
        testTrail.markCompleted();
        testTrail.markNotCompleted();
        testTrail.markCompleted();
        assertTrue(testTrail.getCompletionStatus());
    }

    @Test
    void testSetDate() {
        testTrail.setDate("May 25, 2024");
        assertEquals("May 25, 2024", testTrail.getDateCompleted());
    }

    @Test
    void testMultipleSetDate() {
        testTrail.setDate("June 17, 2024");
        testTrail.setDate("August 21, 2024");
        assertEquals("August 21, 2024", testTrail.getDateCompleted());
    }

    @Test
    void testResetDate() {
        testTrail.setDate("May 25, 2024");
        testTrail.resetDate();
        assertEquals("not completed", testTrail.getDateCompleted());
    }
}

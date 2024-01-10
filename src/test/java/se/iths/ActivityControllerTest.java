package se.iths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ActivityControllerTest {
    private static DatabaseAPI db;

    @BeforeAll
    public static void setup() {
        db = mock(DatabaseAPI.class);
    }

    @Test
    public void canAddActivity() {
        ActivityController activityController = new ActivityController(db);
        Activity activity = new Activity("1", 10.0, 60, new GregorianCalendar(2024, 1, 1));
        when(db.createRecord(activity.id, activity.distance, (int) activity.time.toSeconds(), LocalDate.ofInstant(activity.date.toInstant(), ZoneId.systemDefault()))).thenReturn(true);

        activityController.addActivity(activity);

        verify(db).createRecord(activity.id, activity.distance, (int) activity.time.toSeconds(), LocalDate.ofInstant(activity.date.toInstant(), ZoneId.systemDefault()));
    }

    @Test
    public void canReadRecordFromId() {
        ActivityController activityController = new ActivityController(db);
        Activity activity = new Activity("1", 10.0, 60, new GregorianCalendar(2024, 1, 1));

        when(db.readRecord(activity.id)).thenReturn(new ActivityRecord(activity.id, activity.distance, (int) activity.time.getSeconds(), activity.date));
        Activity activityFromDb = activityController.getActivityById(activity.id);
        
        verify(db).readRecord(activity.id);

        assertEquals("1", activityFromDb.id);
        assertEquals(10.0, activityFromDb.distance);
        assertEquals(60, activityFromDb.time.getSeconds());
        assertEquals(new GregorianCalendar(2024, 1, 1), activityFromDb.date);
    }
}

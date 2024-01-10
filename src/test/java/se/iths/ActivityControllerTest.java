package se.iths;

import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.Test;

public class ActivityControllerTest {
    @Test
    public void canAddActivity() {
        DatabaseAPI db = mock(DatabaseAPI.class);
        ActivityController activityController = new ActivityController(db);
        Activity activity = new Activity("1", 10.0, 60, new GregorianCalendar(2024, 1, 1));
        when(db.createRecord(activity.id, activity.distance, (int) activity.time.toSeconds(), LocalDate.ofInstant(activity.date.toInstant(), ZoneId.systemDefault()))).thenReturn(true);

        activityController.addActivity(activity);

        verify(db).createRecord(activity.id, activity.distance, (int) activity.time.toSeconds(), LocalDate.ofInstant(activity.date.toInstant(), ZoneId.systemDefault()));
    }
}

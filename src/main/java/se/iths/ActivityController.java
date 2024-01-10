package se.iths;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.GregorianCalendar;

public class ActivityController {
    DatabaseAPI db;
    public ActivityController(DatabaseAPI db) {
        this.db = db;
    }

    public void addActivity(Activity activity) {
        db.createRecord(activity.id, activity.distance, (int) activity.time.toSeconds(), LocalDate.ofInstant(activity.date.toInstant(), ZoneId.systemDefault()));
    }

    public Activity getActivityById(String id) {
        ActivityRecord activityRecord = (ActivityRecord) db.readRecord(id);
        return new Activity(activityRecord.id(), activityRecord.distance(), (int) activityRecord.time(), activityRecord.date());
    }
}

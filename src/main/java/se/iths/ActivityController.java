package se.iths;

import java.time.LocalDate;
import java.time.ZoneId;

public class ActivityController {
    DatabaseAPI db;
    public ActivityController(DatabaseAPI db) {
        this.db = db;
    }

    public void addActivity(Activity activity) {
        db.createRecord(activity.id, activity.distance, (int) activity.time.toSeconds(), LocalDate.ofInstant(activity.date.toInstant(), ZoneId.systemDefault()));
    }
}

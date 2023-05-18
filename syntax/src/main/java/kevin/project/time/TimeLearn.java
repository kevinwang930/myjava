package kevin.project.time;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;

public class TimeLearn {

    public void calenderLearn() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, 1, 1);
        System.out.println(calendar.getTime());
        System.out.println(calendar.get(Calendar.DAY_OF_WEEK));
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
        System.out.println(calendar.get(Calendar.DAY_OF_YEAR));

    }

    public void instantLearn() {
        Instant instant = Instant.now();
        System.out.println(instant);
        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.systemDefault());
        System.out.println(zonedDateTime.getYear());
        System.out.println(zonedDateTime.getZone());
        System.out.println(zonedDateTime);
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);



    }

    public static void main(String[] args) {
        TimeLearn timeLearn = new TimeLearn();
        timeLearn.calenderLearn();
        timeLearn.instantLearn();
    }
}

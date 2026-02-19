package kevin.project.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateLearn {


   void  dateHourLearn() {
       // Create a new Date object representing current date and time
       Date date = new Date();

       // Retrieve hour from the date (deprecated but direct method)
       int hour = date.getHours();

       System.out.println("Current date: " + date);
       System.out.println("Hour: " + hour);
   }
   
   
   void zonedDateTimeLearn() {
       // Example 1: Parse string with ISO format (includes zone)
       String isoDateString = "2024-01-15T10:00:00+01:00";
       ZonedDateTime zonedDateTime1 = ZonedDateTime.parse(isoDateString);
       System.out.println("Parsed ISO format: " + zonedDateTime1);
       System.out.println(zonedDateTime1.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH")));
       System.out.println(zonedDateTime1.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH").withZone(ZoneOffset.UTC)));

   }

   void simpleDateLearn() throws ParseException {
       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       Date date = sdf.parse("2025-01-01");
       System.out.println(date.toString());
   }

    public static void main(String[] args) throws ParseException {
        DateLearn dateLearn = new DateLearn();
        dateLearn.dateHourLearn();
        dateLearn.zonedDateTimeLearn();
        dateLearn.simpleDateLearn();

    }
}

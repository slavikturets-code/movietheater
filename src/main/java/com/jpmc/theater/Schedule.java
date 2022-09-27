package com.jpmc.theater;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.time.LocalTime;
import java.util.concurrent.TimeUnit;
import lombok.*;
import org.json.JSONException;
import org.json.JSONObject;


@Data
@AllArgsConstructor
public class Schedule {

    private LocalDateProvider provider = LocalDateProvider.singleton() ;

    private List<Showing> schedule;

    Schedule() {

        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1);
        Movie turningRed = new Movie("Turning Red", Duration.ofMinutes(85), 11, 0);
        Movie theBatMan = new Movie("The Batman", Duration.ofMinutes(95), 9, 0);
        schedule = List.of(
                new Showing(turningRed, 1, LocalDateTime.of(provider.currentDate(), LocalTime.of(9, 0))),
                new Showing(spiderMan, 2, LocalDateTime.of(provider.currentDate(), LocalTime.of(11, 0))),
                new Showing(theBatMan, 3, LocalDateTime.of(provider.currentDate(), LocalTime.of(12, 50))),
                new Showing(turningRed, 4, LocalDateTime.of(provider.currentDate(), LocalTime.of(14, 30))),
                new Showing(spiderMan, 5, LocalDateTime.of(provider.currentDate(), LocalTime.of(16, 10))),
                new Showing(theBatMan, 6, LocalDateTime.of(provider.currentDate(), LocalTime.of(17, 50))),
                new Showing(turningRed, 7, LocalDateTime.of(provider.currentDate(), LocalTime.of(19, 30))),
                new Showing(spiderMan, 8, LocalDateTime.of(provider.currentDate(), LocalTime.of(21, 10))),
                new Showing(theBatMan, 9, LocalDateTime.of(provider.currentDate(), LocalTime.of(23, 0)))
        );
    }


    public void printSchedule() {


        System.out.println(provider.currentDate());
        System.out.println("===================================================");

        schedule.forEach(s ->
                System.out.println(s.getSequenceOfTheDay() + ": " + s.getShowStartTime() + " " + s.getMovie().getTitle() + " " + humanReadableFormat(s.getMovie().getRunningTime()) + " $" + s.getMovieFee())
        );


        System.out.println("===================================================");

        schedule.forEach(s ->
                printJSONSchedule(s.getSequenceOfTheDay() , s.getShowStartTime() , s.getMovie().getTitle() ,humanReadableFormat(s.getMovie().getRunningTime()).substring(1,humanReadableFormat(s.getMovie().getRunningTime()).length()-1) ,s.getMovieFee())
        );
    }

    private String humanReadableFormat(Duration duration) {
        long hour = duration.toHours();
        long remainingMin = duration.toMinutes() - TimeUnit.HOURS.toMinutes(duration.toHours());

        return String.format("(%s hour%s %s minute%s)", hour, handlePlural(hour), remainingMin, handlePlural(remainingMin));
    }

    private String handlePlural(long value) {
        if (value == 1) {
            return "";
        }
        else {
            return "s";
        }
    }

   private void printJSONSchedule(int sequenceOfTheDay, LocalDateTime showStartTime, String movieTitle, String movieRunTime, double movieFee){

       String jsonString = "{\"Sequence of the day\": " + "\"" + sequenceOfTheDay + "\"" + "," +
                             "\"Show start time\": " + "\"" + showStartTime + "\"" + "," +
                             "\"Movie Title\": " + "\"" + movieTitle + "\"" + "," +
                             "\"Movie run time\": " + "\"" + movieRunTime + "\"" + "," +
                              "\"Movie fee\": "  +  "\"$"+ movieFee + "\"" + "}";

       try {
           JSONObject json = new JSONObject(jsonString);
           String prettyJsonString = json.toString();
           System.out.println(prettyJsonString);
       } catch (JSONException e) {
           e.printStackTrace();
       }

   }

}

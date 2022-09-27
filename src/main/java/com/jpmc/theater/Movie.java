package com.jpmc.theater;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    private static int MOVIE_CODE_SPECIAL = 1;

    private LocalDateProvider provider = LocalDateProvider.singleton() ;

    private String title;
    private String description;
    private Duration runningTime;
    private double ticketPrice;
    private int specialCode;

    public Movie(String title, Duration runningTime, double ticketPrice, int specialCode) {
        this.title = title;
        this.runningTime = runningTime;
        this.ticketPrice = ticketPrice;
        this.specialCode = specialCode;
    }

    public Movie(String title, String description, Duration runningTime, double ticketPrice, int specialCode) {
        this.title = title;
        this.description = description;
        this.runningTime = runningTime;
        this.ticketPrice = ticketPrice;
        this.specialCode = specialCode;
    }

    public double calculateTicketPrice(Showing showing) {
        return ticketPrice - getDiscount(showing.getSequenceOfTheDay(), showing.getShowStartTime());
    }

    private double getDiscount(int showSequence, LocalDateTime showStartTime) {

        double specialDiscount = 0;


        double sequenceDiscount = 0;
        
        LocalDateTime lower = LocalDateTime.of(provider.currentDate(), LocalTime.of(11, 0));
        LocalDateTime upper = LocalDateTime.of(provider.currentDate(), LocalTime.of(16, 0));


        List<Double> discountList = new ArrayList<>();

        discountList.add(0.0);

        if (MOVIE_CODE_SPECIAL == specialCode) {
            specialDiscount = ticketPrice * 0.2;  // 20% discount for special movie
            discountList.add(specialDiscount);
        }

        if(showStartTime.isAfter(lower) && showStartTime.isBefore(upper) || showStartTime.isEqual(lower) || showStartTime.isEqual(upper)) {
            specialDiscount = ticketPrice * 0.25;  // 25% discount for special movie
            discountList.add(specialDiscount);
        }

        if(showStartTime.getDayOfMonth() == 7){
            sequenceDiscount = 1;
            discountList.add(sequenceDiscount);
        }


        if (showSequence == 1) {
            sequenceDiscount = 3; // $3 discount for 1st show
            discountList.add(sequenceDiscount);
        } else if (showSequence == 2) {

            sequenceDiscount = 2; // $2 discount for 2nd show
            discountList.add(sequenceDiscount);
        }

        Collections.sort(discountList);
        Collections.reverse(discountList);

        return discountList.get(0);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Double.compare(movie.ticketPrice, ticketPrice) == 0
                && Objects.equals(title, movie.title)
                && Objects.equals(description, movie.description)
                && Objects.equals(runningTime, movie.runningTime)
                && Objects.equals(specialCode, movie.specialCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, runningTime, ticketPrice, specialCode);
    }
}
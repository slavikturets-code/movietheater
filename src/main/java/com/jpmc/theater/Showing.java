package com.jpmc.theater;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Showing {
    private Movie movie;
    private int sequenceOfTheDay;
    private LocalDateTime showStartTime;


    public double getMovieFee() {
        return movie.calculateTicketPrice(this);
    }


}

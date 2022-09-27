package com.jpmc.theater;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class Theater {


    private Schedule schedule;


    public Theater() {
        schedule = new Schedule();
    }

    public static void main(String[] args) {
        Theater theater = new Theater();
        theater.schedule.printSchedule();
    }
}

package com.jpmc.theater;

import org.junit.jupiter.api.Test;


import java.time.LocalDateTime;

import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import java.time.*;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(PowerMockRunner.class)
@PrepareForTest({Reservation.class})
public class ReservationTests {

    LocalDateProvider provider = LocalDateProvider.singleton();

    @Test
    void TestTotalFeeWhenWhenShowingFirstSequenceOfTheDayAndDiscountTwentyPercent() {
        var customer = new Customer("John Doe", "unused-id");
        var showing = new Showing(
                new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1),
                1,
                LocalDateTime.now()
        );
        assertTrue(new Reservation(customer, showing, 3).totalFee() == 28.5);
    }

    @Test
    void TestTotalFeeWhenWhenShowingSecondSequenceOfTheDayAndDiscountTwentyPercent() {
        var customer = new Customer("Michael Jackson", "111");
        var showing = new Showing(
                new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 30, 1),
                2,
                LocalDateTime.now()
        );
        assertTrue(new Reservation(customer, showing, 4).totalFee() == 96);
    }

    @Test
    void TestTotalFeeWhenWhenShowingFirstSequenceOfTheDayAndDiscountTwentyFivePercent() {
        var customer = new Customer("Joe Biden", "222");
        var showing = new Showing(
                new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 40, 1),
                1,
                LocalDateTime.of(provider.currentDate(), LocalTime.of(14, 0))
        );
        assertTrue(new Reservation(customer, showing, 4).totalFee() == 120);
    }

    @Test
    void TestTotalFeeWhenWhenShowingSecondSequenceOfTheDayAndDiscountTwentyFivePercent() {
        var customer = new Customer("Nancy Pelosy", "433");
        var showing = new Showing(
                new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 52, 1),
                2,
                LocalDateTime.of(provider.currentDate(), LocalTime.of(14, 0))
        );
        assertTrue(new Reservation(customer, showing, 2).totalFee() == 78);
    }

    @Test
    void TestTotalFeeWhenWhenThreeDollarDiscountApplied() {
        var customer = new Customer("Ronaldo", "533");
        var showing = new Showing(
                new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 52, 2),
                1,
                LocalDateTime.of(provider.currentDate(), LocalTime.of(17, 0))
        );
        assertTrue(new Reservation(customer, showing, 2).totalFee() == 98);
    }

    @Test
    void TestTotalFeeWhenWhenTwoDollarDiscountApplied() {
        var customer = new Customer("Messi", "633");
        var showing = new Showing(
                new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 52, 2),
                2,
                LocalDateTime.of(provider.currentDate(), LocalTime.of(17, 0))
        );
        assertTrue(new Reservation(customer, showing, 3).totalFee() == 150);
    }

    @Test
    void TestTotalFeeWhenWhenOneDollarDiscountApplied() {
        var customer = new Customer("Robert Jackson", "639");
        var showing = new Showing(
                new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 10, 2),
                5,
                LocalDateTime.of(2022, Month.APRIL, 7, 10,00,00)
        );
        assertTrue(new Reservation(customer, showing, 5).totalFee() == 45);
    }

    @Test
    void TestReserveWhenSequenceOfTheDayNotExist(){

        var customer = new Customer("John Doe", "unused-id");
        var showing = new Showing(
                new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1),
                0,
                LocalDateTime.now()
        );

        Exception exception = assertThrows(IllegalStateException.class, () -> {
            new Reservation(customer, showing, 5).reserve(customer,0,3);
        });

        String expectedMessage = "not able to find any showing for given sequence 0";
        String actualMessage = "not able to find any showing for given sequence 0";

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void TestReserveWhenSequenceOfTheDayExist(){

        var customer = new Customer("John Doe", "unused-id");
        var showing = new Showing(
                new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1),
                0,
                LocalDateTime.now()
        );

        assertNotNull(new Reservation(customer, showing, 5).reserve(customer,1,3));
    }

}

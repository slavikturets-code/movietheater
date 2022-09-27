package com.jpmc.theater;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;


import java.time.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@RunWith(PowerMockRunner.class)
@PrepareForTest({Movie.class})
public class MovieTests {

    Showing showingMock;

    LocalDateProvider provider = LocalDateProvider.singleton();

    LocalDateTime localDateTime;

    @BeforeEach
    public void setup(){

        showingMock = mock(Showing.class);
    }

    @Test
    void testCalculateTicketPriceWhenTwentyPercentDiscountIsHighestDiscount() throws Exception {

        double expected = 16;
        double actual;

        localDateTime = LocalDateTime.of(provider.currentDate(), LocalTime.of(9, 0));

        Movie MovieSpy = Mockito.spy(new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 20, 1));

        when(showingMock.getSequenceOfTheDay()).thenReturn(9);
        when(showingMock.getShowStartTime()).thenReturn(localDateTime);

        actual = MovieSpy.calculateTicketPrice(showingMock);

        assertEquals(expected,actual);
    }

    @Test
    void testCalculateTicketPriceWhenTwentyFivePercentDiscountIsHighestDiscount() throws Exception {

        double expected = 15;
        double actual;

        localDateTime = LocalDateTime.of(provider.currentDate(), LocalTime.of(15, 0));

        Movie MovieSpy = Mockito.spy(new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 20, 1));

        when(showingMock.getSequenceOfTheDay()).thenReturn(1);
        when(showingMock.getShowStartTime()).thenReturn(localDateTime);

        actual = MovieSpy.calculateTicketPrice(showingMock);

        assertEquals(expected,actual);
    }

    @Test
    void testCalculateTicketPriceWhenItIsSeventhOfMonth() throws Exception {

        double expected = 19;
        double actual;

        localDateTime = LocalDateTime.of(2022, Month.APRIL, 7, 10,00,00);

        Movie MovieSpy = Mockito.spy(new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 20, 0));

        when(showingMock.getSequenceOfTheDay()).thenReturn(8);
        when(showingMock.getShowStartTime()).thenReturn(localDateTime);

        actual = MovieSpy.calculateTicketPrice(showingMock);

        assertEquals(expected,actual);
    }

    @Test
    void testCalculateTicketPriceWhenShowingFirstSequenceOfTheDay() throws Exception {

        double expected = 17;
        double actual;

        localDateTime = LocalDateTime.of(provider.currentDate(), LocalTime.of(10, 30));

        Movie MovieSpy = Mockito.spy(new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 20, 0));

        when(showingMock.getSequenceOfTheDay()).thenReturn(1);
        when(showingMock.getShowStartTime()).thenReturn(localDateTime);

        actual = MovieSpy.calculateTicketPrice(showingMock);

        assertEquals(expected,actual);
    }

    @Test
    void testCalculateTicketPriceWhenShowingSecondSequenceOfTheDay() throws Exception {

        double expected = 18;
        double actual;

        localDateTime = LocalDateTime.of(provider.currentDate(), LocalTime.of(10, 30));

        Movie MovieSpy = Mockito.spy(new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 20, 0));

        when(showingMock.getSequenceOfTheDay()).thenReturn(2);
        when(showingMock.getShowStartTime()).thenReturn(localDateTime);

        actual = MovieSpy.calculateTicketPrice(showingMock);

        assertEquals(expected,actual);
    }

    @Test
    void testEqualsWhenTwoObjectsEqual(){

        Movie movieOne = new Movie("Spider-Man: No Way Home","Action", Duration.ofMinutes(90), 20, 0);
        Movie movieTwo = new Movie("Spider-Man: No Way Home","Action", Duration.ofMinutes(90), 20, 0);

        Assert.assertTrue(movieOne.equals(movieTwo));

    }

    @Test
    void testEqualsWhenTwoObjectsNotEqual(){

        Movie movieOne = new Movie("Spider-Man: No Way Home","Action", Duration.ofMinutes(90), 20, 0);
        Movie movieTwo = null;

        Assert.assertFalse(movieOne.equals(movieTwo));

    }

    @Test
    void testHashCodeWhenTwoObjectsHaveSameHashCode(){

        Movie movieOne = new Movie("Spider-Man: No Way Home","Action", Duration.ofMinutes(90), 20, 0);
        Movie movieTwo = new Movie("Spider-Man: No Way Home","Action", Duration.ofMinutes(90), 20, 0);;

        Assert.assertEquals(movieOne.hashCode(),movieTwo.hashCode());

    }

    @Test
    void testHashCodeWhenTwoObjectsNotHaveSameHashCode(){

        Movie movieOne = new Movie("Spider-Man: No Way Home","Action", Duration.ofMinutes(90), 20, 0);
        Movie movieTwo = new Movie("The Batman","Action", Duration.ofMinutes(90), 20, 0);;

        Assert.assertNotSame(movieOne.hashCode(),movieTwo.hashCode());

    }
}

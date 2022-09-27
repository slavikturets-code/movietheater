package com.jpmc.theater;

import org.junit.jupiter.api.Test;

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
@PrepareForTest({LocalDateProvider.class})
public class LocalDateProviderTests {

    @Test
    void testCurrentDatePresentedByLocalDateProviderSingleton() {

        Assert.assertEquals(LocalDateProvider.singleton().currentDate(),LocalDate.now());
    }
}

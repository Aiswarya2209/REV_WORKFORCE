package com.revworkforcetest;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import com.revworkforce.model.Holiday;

public class HolidayTest {

    @Test
    public void testHolidayConstructorAndGetters() {

        Date date = new Date();
        Holiday holiday = new Holiday(date, "Republic Day");

        assertEquals(date, holiday.getDate());
        assertEquals("Republic Day", holiday.getName());
    }

    @Test
    public void testHolidayValuesNotNull() {

        Holiday holiday = new Holiday(new Date(), "Independence Day");

        assertNotNull(holiday.getDate());
        assertNotNull(holiday.getName());
    }
}

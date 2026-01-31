package com.revworkforce.service;

import com.revworkforce.dao.HolidayDao;

public class HolidayService {

    HolidayDao holidayDao = new HolidayDao();

    public void viewHolidays() {
        holidayDao.viewHolidays();
    }
}

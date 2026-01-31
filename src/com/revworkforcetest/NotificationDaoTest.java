package com.revworkforcetest;

import org.junit.Test;

import com.revworkforce.dao.NotificationDao;

public class NotificationDaoTest {

    NotificationDao dao = new NotificationDao();

    @Test
    public void testViewNotifications() {

        dao.viewNotifications(101); 

        
    }
}

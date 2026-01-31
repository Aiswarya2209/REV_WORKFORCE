package com.revworkforce.service;

import com.revworkforce.dao.NotificationDao;

public class NotificationService {

    private NotificationDao dao = new NotificationDao();

    public void viewNotifications(int empId) {
        dao.viewNotifications(empId);
    }
}

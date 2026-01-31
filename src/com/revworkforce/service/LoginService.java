package com.revworkforce.service;

import com.revworkforce.dao.LoginDAO;
import com.revworkforce.model.Employee;

public class LoginService {

    private LoginDAO loginDAO = new LoginDAO();

    public Employee login(int empId, String password) {
        return loginDAO.Login(empId, password);
    }
}

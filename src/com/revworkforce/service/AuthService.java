package com.revworkforce.service;

import com.revworkforce.dao.LoginDAO;
import com.revworkforce.model.Employee;

public class AuthService {

    private LoginDAO dao = new LoginDAO();

    public Employee authenticate(int empId, String password) {

        
        if (password == null || password.trim().isEmpty()) {
            System.out.println("Password cannot be empty");
            return null;
        }

        
        Employee emp = dao.Login(empId, password);

        if (emp == null) {
            System.out.println("Invalid Employee ID or Password");
        }

        return emp;
    }
}

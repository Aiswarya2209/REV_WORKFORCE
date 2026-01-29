
package com.revworkforce.service;

import com.revworkforce.dao.LoginDAO;
import com.revworkforce.model.Employee;

public class AuthService {

    LoginDAO dao = new LoginDAO();

    public Employee authenticate(int empId, String password) {

        // Password must contain letters and numbers
        if (!password.matches(".*[a-zA-Z].*") || !password.matches(".*\\d.*")) {
            System.out.println("Invalid Password! Must contain letters and numbers.");
            return null;
        }

        return dao.login(empId, password);
    }
}
package com.example.ElectricityTokenGenerator.services.Users;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

@Service
public class logoutUserService {

    public String logoutUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
            return "User logged out successfully.";
        }
        return "No active session found.";
    }
}

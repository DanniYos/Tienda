package org.dan.webapp.apiservlet.headers.services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

public class LoginServiceSecionImpl implements LoginService{

    @Override
    public Optional<String> getUserName(HttpServletRequest req) {
        HttpSession session = req.getSession();
        String username = (String) session.getAttribute("username");
        if (username != null){
            return Optional.of(username);
        }
        return Optional.empty();
    }
}

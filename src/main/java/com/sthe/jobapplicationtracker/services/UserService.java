package com.sthe.jobapplicationtracker.services;
import com.sthe.jobapplicationtracker.domain.User;
import com.sthe.jobapplicationtracker.exceptions.JATAuthException;

public interface UserService {
    User validateUser(String email, String password) throws JATAuthException;
    User registerUser(String firstName, String lastName,
                      String email, String password) throws JATAuthException;
}

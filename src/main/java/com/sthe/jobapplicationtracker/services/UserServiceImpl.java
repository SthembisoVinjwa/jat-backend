package com.sthe.jobapplicationtracker.services;

import com.sthe.jobapplicationtracker.domain.User;
import com.sthe.jobapplicationtracker.exceptions.JATAuthException;
import com.sthe.jobapplicationtracker.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Pattern;

@Service
@Transactional
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;

    @Override
    public User validateUser(String email, String password) throws JATAuthException {
        if (email != null) {
            email = email.toLowerCase();
            return userRepository.findByEmailAndPassword(email, password);
        } else {
            throw new JATAuthException("Email cannot be null");
        }
    }

    @Override
    public User registerUser(String firstName, String lastName, String email, String password) throws JATAuthException {
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        if (email != null) {
            email = email.toLowerCase();
            if (!pattern.matcher(email).matches()) throw new JATAuthException("Invalid email format");
        } else {
            throw new JATAuthException("Email cannot be null");
        }

        Integer emailCount = userRepository.getCountByEmail(email);
        if (emailCount > 0) throw new JATAuthException("Email already in use");

        Integer userId = userRepository.create(firstName, lastName, email, password);
        return userRepository.findById(userId);
    }
}

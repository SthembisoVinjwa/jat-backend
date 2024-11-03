package com.sthe.jobapplicationtracker.repositories;
import com.sthe.jobapplicationtracker.exceptions.JATAuthException;
import com.sthe.jobapplicationtracker.domain.User;

public interface UserRepository {
    Integer create(String firstName, String lastName, String email, String password) throws JATAuthException;

    User findByEmailAndPassword(String email, String password) throws JATAuthException;

    Integer getCountByEmail(String email);

    User findById(Integer userId);
}

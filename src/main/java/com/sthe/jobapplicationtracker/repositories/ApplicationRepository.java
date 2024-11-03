package com.sthe.jobapplicationtracker.repositories;

import com.sthe.jobapplicationtracker.domain.Application;
import com.sthe.jobapplicationtracker.exceptions.JATBadRequestException;
import com.sthe.jobapplicationtracker.exceptions.JATResourceNotFoundException;
import java.sql.Date;
import java.util.List;

public interface ApplicationRepository {
    List<Application> findAll(Integer userId) throws JATResourceNotFoundException;
    Application findById(Integer userId, Integer applicationId) throws JATResourceNotFoundException;
    Integer create(Integer userId, String company, String role, String status, String link,
                   Date dateOfApplication, String notes) throws JATBadRequestException;
    void update(Integer userId, Integer applicationId, Application application);
    void deleteById(Integer userId, Integer applicationId);
}

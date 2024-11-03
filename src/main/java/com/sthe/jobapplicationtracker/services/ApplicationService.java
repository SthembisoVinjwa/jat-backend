package com.sthe.jobapplicationtracker.services;

import com.sthe.jobapplicationtracker.domain.Application;
import com.sthe.jobapplicationtracker.exceptions.JATBadRequestException;
import com.sthe.jobapplicationtracker.exceptions.JATResourceNotFoundException;

import java.sql.Date;
import java.util.List;

public interface ApplicationService {
    List<Application> fetchAllApplications(Integer userId);
    Application fetchApplicationById(Integer userId, Integer applicationId)
            throws JATResourceNotFoundException;
    Application addApplication(Integer userId, String company, String role, String status, String link,
                               Date dateOfApplication, String notes) throws JATBadRequestException;
    void updateApplication(Integer userId, Integer applicationId, Application application)
            throws JATBadRequestException;
    void deleteApplication(Integer userId, Integer applicationId) throws JATResourceNotFoundException;
}

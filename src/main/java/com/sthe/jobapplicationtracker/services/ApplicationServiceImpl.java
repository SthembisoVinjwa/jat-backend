package com.sthe.jobapplicationtracker.services;

import com.sthe.jobapplicationtracker.domain.Application;
import com.sthe.jobapplicationtracker.exceptions.JATBadRequestException;
import com.sthe.jobapplicationtracker.exceptions.JATResourceNotFoundException;
import com.sthe.jobapplicationtracker.repositories.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

@Service
@Transactional
public class ApplicationServiceImpl implements ApplicationService{
    @Autowired
    ApplicationRepository applicationRepository;

    @Override
    public List<Application> fetchAllApplications(Integer userId) {
        return applicationRepository.findAll(userId);
    }

    @Override
    public Application fetchApplicationById(Integer userId, Integer applicationId) throws JATResourceNotFoundException {
        return applicationRepository.findById(userId, applicationId);
    }

    @Override
    public Application addApplication(Integer userId, String company, String role, String status, String link, Date dateOfApplication, String notes) throws JATBadRequestException {
        int applicationId = applicationRepository.create(userId, company, role, status, link, dateOfApplication, notes);
        return applicationRepository.findById(userId, applicationId);
    }

    @Override
    public void updateApplication(Integer userId, Integer applicationId, Application application) throws JATBadRequestException {
        applicationRepository.update(userId, applicationId, application);
    }

    @Override
    public void deleteApplication(Integer userId, Integer applicationId) throws JATResourceNotFoundException {
        applicationRepository.deleteById(userId, applicationId);
    }
}

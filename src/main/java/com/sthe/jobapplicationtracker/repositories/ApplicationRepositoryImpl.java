package com.sthe.jobapplicationtracker.repositories;

import com.sthe.jobapplicationtracker.domain.Application;
import com.sthe.jobapplicationtracker.exceptions.JATBadRequestException;
import com.sthe.jobapplicationtracker.exceptions.JATResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Date;
import java.util.List;

@Repository
public class ApplicationRepositoryImpl implements ApplicationRepository{
    private static final String SQL_CREATE =
            "INSERT INTO APPLICATIONS(APPLICATION_ID, USER_ID, COMPANY, ROLE, STATUS, LINK, " +
                    "DATE_OF_APPLICATION, NOTES) VALUES(NEXTVAL('APPLICATIONS_SEQUENCE'), " +
                    "?, ?, ?, ?, ?, ?, ?)";

    private static final String SQL_FIND_BY_ID = "SELECT APPLICATION_ID, USER_ID, COMPANY, ROLE, " +
            "STATUS, LINK, DATE_OF_APPLICATION, NOTES FROM APPLICATIONS" +
            " WHERE APPLICATION_ID = ? AND USER_ID = ?";

    private static final String SQL_FIND_ALL = "SELECT APPLICATION_ID, USER_ID, COMPANY, ROLE, " +
            "STATUS, LINK, DATE_OF_APPLICATION, NOTES FROM APPLICATIONS WHERE USER_ID = ?";

    private static final String SQL_UPDATE = "UPDATE APPLICATIONS SET COMPANY = ?, ROLE = ?, STATUS = ?, " +
            "LINK = ?, DATE_OF_APPLICATION = ?, NOTES = ? WHERE APPLICATION_ID = ? AND USER_ID = ?";

    private static final String SQL_DELETE = "DELETE FROM APPLICATIONS WHERE " +
            "APPLICATION_ID = ? AND USER_ID = ?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Application> findAll(Integer userId) throws JATResourceNotFoundException {
        return jdbcTemplate.query(SQL_FIND_ALL, new Object[]{userId}, applicationRowMapper);
    }

    @Override
    public Application findById(Integer userId, Integer applicationId) throws JATResourceNotFoundException {
        try {
            return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[]{applicationId, userId},
                    applicationRowMapper);
        } catch (Exception e) {
            throw new JATResourceNotFoundException("Application not found");
        }
    }

    @Override
    public Integer create(Integer userId, String company, String role, String status, String link, Date dateOfApplication, String notes) throws JATBadRequestException {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE, PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setInt(1, userId);
                ps.setString(2, company);
                ps.setString(3, role);
                ps.setString(4, status);
                ps.setString(5, link);
                ps.setDate(6, dateOfApplication);
                ps.setString(7, notes);
                return ps;
            }, keyHolder);
            return (Integer) keyHolder.getKeys().get("APPLICATION_ID");
        } catch (Exception e) {
            throw new JATBadRequestException("Invalid request");
        }
    }

    @Override
    public void update(Integer userId, Integer applicationId, Application application) {
        try {
            jdbcTemplate.update(SQL_UPDATE, new Object[]{application.getCompany(), application.getRole(),
            application.getStatus(), application.getLink(), application.getDateOfApplication(),
                    application.getNotes(), applicationId, userId});
        } catch (Exception e) {
            throw new JATBadRequestException("Invalid request");
        }
    }

    @Override
    public void deleteById(Integer userId, Integer applicationId) {
        int count = jdbcTemplate.update(SQL_DELETE, new Object[]{applicationId, userId});
        if (count == 0) throw new JATResourceNotFoundException("Application not found");
    }

    private RowMapper<Application> applicationRowMapper = ((rs, rowNum) -> {
        return new Application(
                rs.getInt("APPLICATION_ID"),
                rs.getInt("USER_ID"),
                rs.getString("COMPANY"),
                rs.getString("ROLE"),
                rs.getString("STATUS"),
                rs.getString("LINK"),
                rs.getDate("DATE_OF_APPLICATION"),
                rs.getString("NOTES")
        );
    });
}

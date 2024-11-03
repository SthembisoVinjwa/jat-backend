package com.sthe.jobapplicationtracker.resources;

import com.sthe.jobapplicationtracker.domain.Application;
import com.sthe.jobapplicationtracker.services.ApplicationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/applications")
public class ApplicationResource {
    @Autowired
    ApplicationService applicationService;

    @GetMapping("")
    public ResponseEntity<List<Application>> getAllApplications(HttpServletRequest request) {
        int userId = (Integer) request.getAttribute("userId");
        List<Application> applications = applicationService.fetchAllApplications(userId);
        return new ResponseEntity<>(applications, HttpStatus.OK);
    }

    @GetMapping("/{applicationId}")
    public ResponseEntity<Application> getApplicationById(HttpServletRequest request, @PathVariable
            ("applicationId") Integer applicationId) {
        int userId = (Integer) request.getAttribute("userId");
        Application application = applicationService.fetchApplicationById(userId, applicationId);
        return new ResponseEntity<>(application, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Application> addApplication(HttpServletRequest request, @RequestBody Map<String, Object> applicationMap) throws ParseException {
        int userId = (Integer) request.getAttribute("userId");
        String company = (String) applicationMap.get("company");
        String role = (String) applicationMap.get("role");
        String status = (String) applicationMap.get("status");
        String link = (String) applicationMap.get("link");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        java.util.Date utilDate = sdf.parse((String) applicationMap.get("dateOfApplication"));
        java.sql.Date dateOfApplication = new java.sql.Date(utilDate.getTime());
        String notes = (String) applicationMap.get("notes");

        Application application = applicationService.addApplication(userId, company, role, status,
                link, dateOfApplication, notes);
        return new ResponseEntity<>(application, HttpStatus.CREATED);
    }

    @PutMapping("/update/{applicationId}")
    public ResponseEntity<Map<String, Boolean>> updateApplication(
            HttpServletRequest request, @PathVariable("applicationId") Integer applicationId,
            @RequestBody Application application) {
        int userId = (Integer) request.getAttribute("userId");
        applicationService.updateApplication(userId, applicationId, application);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{applicationId}")
    public ResponseEntity<Map<String, Boolean>> deleteApplication(
            HttpServletRequest request, @PathVariable("applicationId") Integer applicationId) {
        int userId = (Integer) request.getAttribute("userId");
        applicationService.deleteApplication(userId, applicationId);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}

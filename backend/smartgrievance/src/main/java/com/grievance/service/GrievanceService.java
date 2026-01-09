package com.grievance.service;

import com.grievance.model.Grievance;
import com.grievance.repository.GrievanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrievanceService {

    @Autowired
    private GrievanceRepository repository;

    // SUBMIT (AUTO CLASSIFICATION)
    public Grievance submitGrievance(Grievance grievance) {

        // Default status
        grievance.setStatus("SUBMITTED");

        // Combine title + description for analysis
        String text = "";
        if (grievance.getTitle() != null)
            text += grievance.getTitle().toLowerCase();
        if (grievance.getDescription() != null)
            text += " " + grievance.getDescription().toLowerCase();

        // AUTO DEPARTMENT
        if (text.contains("water")) {
            grievance.setDepartment("Water Supply");
        } else if (text.contains("road") || text.contains("pothole")) {
            grievance.setDepartment("Roadways");
        } else if (text.contains("electric") || text.contains("power")) {
            grievance.setDepartment("Electricity");
        } else if (text.contains("garbage") || text.contains("waste")) {
            grievance.setDepartment("Sanitation");
        } else if (text.contains("bus") || text.contains("transport")) {
            grievance.setDepartment("Transport");
        } else {
            grievance.setDepartment("Municipality");
        }

        // AUTO URGENCY
        if (text.contains("urgent") || text.contains("immediate") || text.contains("emergency")) {
            grievance.setUrgency("HIGH");
        } else if (text.contains("delay") || text.contains("problem")) {
            grievance.setUrgency("MEDIUM");
        } else {
            grievance.setUrgency("LOW");
        }

        return repository.save(grievance);
    }

    // GET ALL
    public List<Grievance> getAllGrievances() {
        return repository.findAll();
    }

    // GET BY ID
    public Grievance getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    // MARK RESOLVED
    public Grievance markResolved(Long id) {
        Grievance g = repository.findById(id).orElse(null);
        if (g != null) {
            g.setStatus("RESOLVED");
            return repository.save(g);
        }
        return null;
    }
}

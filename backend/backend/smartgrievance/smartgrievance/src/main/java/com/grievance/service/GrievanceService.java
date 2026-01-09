package com.grievance.service;

import com.grievance.model.Grievance;
import com.grievance.repository.GrievanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class GrievanceService {

    @Autowired
    private GrievanceRepository repository;

    @Autowired
    private AIService aiService;

    @Autowired
    private EmailService emailService;

    public Grievance submitGrievance(Grievance grievance) {

        Map<String, String> aiResult =
                aiService.analyzeGrievance(grievance.getDescription());

        grievance.setDepartment(aiResult.get("department"));
        grievance.setUrgency(aiResult.get("urgency"));
        grievance.setStatus("SUBMITTED");
        grievance.setCreatedAt(LocalDateTime.now());

        Grievance saved = repository.save(grievance);

        // Notify citizen
        emailService.sendEmail(
                saved.getEmail(),
                "Grievance Submitted",
                "Your grievance ID is " + saved.getId()
        );

        return saved;
    }

    public Grievance markResolved(Long id) {
        Grievance g = repository.findById(id).orElseThrow();
        g.setStatus("RESOLVED");
        return repository.save(g);
    }


    public List<Grievance> getAllGrievances() {
        return repository.findAll();
    }
}

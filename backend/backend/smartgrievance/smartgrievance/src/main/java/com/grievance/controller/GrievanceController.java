package com.grievance.controller;

import com.grievance.model.Grievance;
import com.grievance.service.GrievanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/grievance")
@CrossOrigin
public class GrievanceController {

    @Autowired
    private GrievanceService grievanceService;

    // Submit grievance
    @PostMapping("/submit")
    public Grievance submit(@RequestBody Grievance grievance) {
        return grievanceService.submitGrievance(grievance);
    }

    // Get all grievances
    @GetMapping("/all")
    public List<Grievance> getAll() {
        return grievanceService.getAllGrievances();
    }

    // ADMIN ACTION: Mark grievance as RESOLVED
    @PutMapping("/resolve/{id}")
    public Grievance resolve(@PathVariable Long id) {
        return grievanceService.markResolved(id);
    }
}

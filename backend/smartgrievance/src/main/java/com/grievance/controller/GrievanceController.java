package com.grievance.controller;

import com.grievance.model.Grievance;
import com.grievance.service.GrievanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/grievance")

public class GrievanceController {

    @Autowired
    private GrievanceService grievanceService;

    // SUBMIT GRIEVANCE
    @PostMapping("/submit")
    public Grievance submit(@RequestBody Grievance grievance) {
        return grievanceService.submitGrievance(grievance);
    }

    // GET ALL (admin use)
    @GetMapping("/all")
    public List<Grievance> getAll() {
        return grievanceService.getAllGrievances();
    }

    // ✅ GET STATUS BY ID (IMPORTANT)
    @GetMapping("/{id}")
    public Grievance getById(@PathVariable Long id) {
        return grievanceService.getById(id);
    }

    // MARK AS RESOLVED (admin)
    @PutMapping("/resolve/{id}")
    public Grievance resolve(@PathVariable Long id) {
        return grievanceService.markResolved(id);
    }
}

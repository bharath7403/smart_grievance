package com.grievance.scheduler;

import com.grievance.model.Grievance;
import com.grievance.repository.GrievanceRepository;
import com.grievance.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class ReminderScheduler {

    @Autowired
    private GrievanceRepository repository;

    @Autowired
    private EmailService emailService;

    // Runs every 1 hour
    @Scheduled(fixedRate = 3600000)
    public void sendReminders() {

        List<Grievance> pendingGrievances =
                repository.findByStatusNot("RESOLVED");

        for (Grievance g : pendingGrievances) {

            // If grievance older than 2 days
            if (g.getCreatedAt().isBefore(LocalDateTime.now().minusDays(2))) {

                emailService.sendEmail(
                        "official@department.gov",
                        "Pending Grievance Reminder",
                        "Grievance ID " + g.getId() +
                                " is pending under " + g.getDepartment()
                );
            }
        }
    }
}

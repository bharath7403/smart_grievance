package com.grievance.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class Grievance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String title;

    @Column(length = 3000)
    private String description;

    private String department;
    private String urgency;
    private String status;

    private LocalDateTime createdAt;
}

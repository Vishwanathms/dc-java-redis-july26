package com.example.frontend;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.Instant;

@Entity
public class VisitLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Instant visitedAt;

    public VisitLog() {
    }

    public VisitLog(Instant visitedAt) {
        this.visitedAt = visitedAt;
    }

    public Long getId() {
        return id;
    }

    public Instant getVisitedAt() {
        return visitedAt;
    }
}

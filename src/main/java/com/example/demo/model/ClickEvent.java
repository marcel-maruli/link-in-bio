package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "click_event")
public class ClickEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long linkId;
    private String profileUsername;
    private String utmSource;
    private String utmMedium;
    private String utmCampaign;
    private String referrer;
    private String userAgent;
    private LocalDateTime createdAt;
}
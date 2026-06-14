package com.example.demo.repository;

import com.example.demo.model.ClickEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClickRepository extends JpaRepository<ClickEvent, Long> {
    long countByLinkId(Long linkId);
}
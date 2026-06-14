package com.example.demo.repository;

import com.example.demo.model.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LinkRepository extends JpaRepository<Link, Long> {
    List<Link> findByProfileId(Long profileId);

    List<Link> findByProfileUsername(String username);

    List<Link> findByProfileUsernameAndIsActiveTrue(String username);
}
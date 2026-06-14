package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ProfileDTO;
import com.example.demo.model.Profile;
import com.example.demo.service.ProfileService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @PostMapping("/profiles")
    public ResponseEntity<Profile> create(@Valid @RequestBody ProfileDTO dto) {
        return ResponseEntity.ok(profileService.save(dto));
    }

    @PutMapping("/profiles/{id}")
    public ResponseEntity<Profile> edit(@PathVariable Long id) {
        System.out.println("MASUK GA SIH ASU?");
        Profile profile = profileService.findByUserId(id);

        if (profile == null) {
            return ResponseEntity.notFound().build();
        }

        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();

        if (!profile.getUser().getUsername().equals(currentUsername)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return ResponseEntity.ok(profile);
    }

    @GetMapping("/public/{username}")
    public ResponseEntity<ProfileDTO> getPublic(@PathVariable String username) {
        Profile profile = profileService.findByUsername(username);
        return ResponseEntity.ok(ProfileDTO.fromEntity(profile));

    }
}
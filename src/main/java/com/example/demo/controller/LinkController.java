package com.example.demo.controller;

import com.example.demo.dto.LinkDTO;
import com.example.demo.model.Link;
import com.example.demo.model.Profile;
import com.example.demo.repository.LinkRepository;
import com.example.demo.service.LinkService;
import com.example.demo.service.ProfileService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/links")
public class LinkController {

    @Autowired
    private LinkService linkService;
    @Autowired
    private LinkRepository linkRepo;
    @Autowired
    private ProfileService profileService;
    
@PostMapping
public ResponseEntity<Link> create(@Valid @RequestBody LinkDTO dto) {
    String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
    
    return ResponseEntity.ok(linkService.saveWithUser(dto, currentUsername));
}

    @PutMapping("/{linkId}")
    public ResponseEntity<Link> update(@PathVariable Long linkId, @RequestBody LinkDTO dto) {
        Link link = linkRepo.findById(linkId).orElseThrow();
        Profile profile = profileService.findByUserId(linkId);
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();

        if (!link.getProfile().getUser().getUsername().equals(currentUsername)) {
            throw new RuntimeException("Akses Denied: Unauthorized!");
        }

        link.setTitle(dto.getTitle());
        Link updatedLink = linkRepo.save(link);

        return ResponseEntity.ok(updatedLink);
    }

   @DeleteMapping("/{linkId}")
public ResponseEntity<?> delete(@PathVariable Long linkId) {
    try {
        linkService.delete(linkId);
        return ResponseEntity.noContent().build();
    } catch (Exception e) {
        e.printStackTrace(); // INI WAJIB agar error muncul di terminal
        return ResponseEntity.status(500).body("Gagal hapus: " + e.getMessage());
    }
}
}
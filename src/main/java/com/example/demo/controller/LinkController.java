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
        return ResponseEntity.ok(linkService.save(dto));
    }

    @PutMapping("/{linkId}")
    public ResponseEntity<Link> update(@PathVariable Long linkId, @RequestBody LinkDTO dto) {
        Link link = linkRepo.findById(linkId).orElseThrow();
        Profile profile = profileService.findByUserId(linkId);

        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();

        if (!link.getProfile().getUser().getUsername().equals(currentUsername)) {
            throw new RuntimeException("Akses Ditolak: Anda bukan pemilik link ini!");
        }

        link.setTitle(dto.getTitle());
        Link updatedLink = linkRepo.save(link);

        return ResponseEntity.ok(updatedLink);
    }

    @DeleteMapping("/{linkId}")
    public ResponseEntity<Void> delete(@PathVariable Long linkId) {
        linkService.delete(linkId);
        return ResponseEntity.noContent().build();
    }
}
package com.example.demo.service;

import com.example.demo.dto.ProfileDTO;
import com.example.demo.model.Profile;
import com.example.demo.model.User;
import com.example.demo.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    public Profile save(ProfileDTO dto) {
        Profile profile = new Profile();
        User user = new User();
        user.setUsername(dto.getUser().getUsername());
        profile.setDisplayName(dto.getDisplayName());
        profile.setBio(dto.getBio());
        return profileRepository.save(profile);
    }

    public Profile findByUsername(String username) {
        return profileRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Profile not found"));
    }

    public Profile findByUserId(Long userId) {
        return profileRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Profile not found"));
    }

}
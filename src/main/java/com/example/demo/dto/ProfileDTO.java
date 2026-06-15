package com.example.demo.dto;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.model.Profile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ProfileDTO {
    @NotBlank(message = "Username is required")
    @Pattern(regexp = "^[a-z0-9]+$", message = "Only lowercase letters and numbers allowed")
    private String id;
    String displayName;
    String bio;
    String avatarUrl;
    private List<LinkDTO> links; 

    private UserDTO user;

    public static ProfileDTO fromEntity(Profile p) {
    ProfileDTO dto = new ProfileDTO();
    UserDTO userDto = new UserDTO();

    dto.setId("profile_" + String.format("%03d", p.getId()));
    userDto.setUsername(p.getUser().getUsername());
    dto.setUser(userDto);
    dto.setDisplayName(p.getDisplayName());
    dto.setBio(p.getBio());
    dto.setAvatarUrl(p.getAvatarUrl());

dto.setLinks(p.getLinks() != null 
    ? p.getLinks().stream()
         .map(LinkDTO::fromEntity) 
         .collect(Collectors.toList()) 
    : Collections.emptyList());

    return dto;
}
}
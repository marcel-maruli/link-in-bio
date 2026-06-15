package com.example.demo.dto;

import com.example.demo.model.Link;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class LinkDTO {

    private Long id;
    @NotBlank(message = "Title is required")
    @Size(min = 1, max = 100, message = "Title must be between 1 and 100 characters")
    private String title;

    @NotBlank(message = "URL is required")
    @Pattern(regexp = "^(http|https)://.*", message = "URL must start with http:// or https://")
    private String url;

    private Boolean isActive = true;

    private Integer position;
    private Long profileId;
    public static LinkDTO fromEntity(Link link) {
        LinkDTO dto = new LinkDTO();
        dto.setTitle(link.getTitle());
        dto.setUrl(link.getUrl());
        dto.setId(link.getId());
        return dto;
    }
}
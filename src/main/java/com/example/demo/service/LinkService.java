package com.example.demo.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.demo.dto.LinkDTO;
import com.example.demo.model.ClickEvent;
import com.example.demo.model.Link;
import com.example.demo.model.Profile;
import com.example.demo.repository.ClickRepository;
import com.example.demo.repository.LinkRepository;
import com.example.demo.repository.ProfileRepository;

import jakarta.transaction.Transactional;

@Service
public class LinkService {
    @Autowired
    private LinkRepository linkRepo;
    @Autowired
    private ClickRepository clickRepo;
    @Autowired
    private ProfileRepository profileRepository;

    @Transactional
    public Link updateLink(Long id, LinkDTO dto) {
        Link link = linkRepo.findById(id).orElseThrow();
        link.setTitle(dto.getTitle());
        link.setUrl(dto.getUrl());
        return linkRepo.save(link);
    }

    @Async
    public void trackClick(Long linkId, String referrer, String userAgent, String source, String medium,
            String campaign) {
        ClickEvent click = new ClickEvent();
        click.setLinkId(linkId);
        click.setReferrer(referrer);
        click.setUserAgent(userAgent);
        click.setUtmSource(source);
        click.setUtmMedium(medium);
        click.setUtmCampaign(campaign);
        click.setCreatedAt(LocalDateTime.now());
        clickRepo.save(click);
    }

    @Transactional
    public Link save(LinkDTO dto) {
        Link link = new Link();
        link.setTitle(dto.getTitle());
        link.setUrl(dto.getUrl());
        link.setPosition(dto.getPosition());

        Profile profile = profileRepository.findById(dto.getProfileId())
                .orElseThrow(() -> new RuntimeException("Profile tidak ditemukan"));

        link.setProfile(profile);
        return linkRepo.save(link);
    }

    @Transactional
    public void delete(Long id) {
        linkRepo.deleteById(id);
    }

}
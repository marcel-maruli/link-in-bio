package com.example.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.ClickRepository;
import com.example.demo.repository.LinkRepository;

@Service
public class AnalyticsService {
    @Autowired
    private ClickRepository clickRepo;
    @Autowired
    private LinkRepository linkRepo;

    public List<Map<String, Object>> getClickSummary() {
        return linkRepo.findAll().stream().map(link -> {
            Map<String, Object> map = new HashMap<>();
            map.put("linkId", link.getId());
            map.put("title", link.getTitle());
            map.put("clickCount", clickRepo.countByLinkId(link.getId()));
            return map;
        }).collect(Collectors.toList());
    }
}
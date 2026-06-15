package com.example.demo.service;

import com.example.demo.dto.ProfileDTO;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.model.Profile;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User tidak ditemukan: " + username));

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(new ArrayList<>())
                .build();
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User tidak ditemukan: " + username));
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Transactional
    public User registerUser(RegisterRequest req) {
        if (this.existsByUsername(req.getUsername()) == true) {
            throw new RuntimeException("Error: Username '" + req.getUsername() + "' is already taken!");
        }
        User user = new User();
        user.setUsername(req.getUsername());
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        user.setFullName(req.getFullName());

        Profile profile = new Profile();
        profile.setDisplayName(req.getFullName());
        profile.setBio("");
        profile.setUser(user);

        user.setProfile(profile);

        return userRepository.save(user);
    }
}
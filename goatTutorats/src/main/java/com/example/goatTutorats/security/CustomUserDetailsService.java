package com.example.goatTutorats.security;

import com.example.goatTutorats.repositories.TutorRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final TutorRepository tutorRepository;

    public CustomUserDetailsService(TutorRepository tutorRepository) {
        this.tutorRepository = tutorRepository;
    }

    /**
     * Return tutor identified by his username.
     * @param username Tutor's username
     * @return Tutor information.
     * @throws UsernameNotFoundException if no tutor found with this username.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.tutorRepository.getTutorsByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("No tutor found with username %s", username)));
    }
}

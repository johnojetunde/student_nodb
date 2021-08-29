package com.sda.student_nodb.service;

import com.sda.student_nodb.model.Role;
import com.sda.student_nodb.model.User;
import com.sda.student_nodb.repository.RoleRepository;
import com.sda.student_nodb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        var user = userRepository.findByUsernameIgnoreCase(s)
                .orElseThrow(() -> new RuntimeException("User not found"));

        var authorities = mapRolesToAuthorities(user.getRoles());

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(toList());
    }

    public User registerUser(User user) {
        boolean isUserExisting = userRepository.existsByUsername(user.getUsername());
        if (isUserExisting) {
            throw new RuntimeException("User with username " + user.getUsername() + " exists");
        }

        var role = roleRepository.findByNameIgnoreCase(user.getUserRole())
                .orElseGet(() -> new Role(null, user.getUserRole()));

        user.setRoles(Set.of(role));
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }
}

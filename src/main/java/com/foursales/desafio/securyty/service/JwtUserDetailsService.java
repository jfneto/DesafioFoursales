package com.foursales.desafio.securyty.service;

import com.foursales.desafio.securyty.models.User;
import com.foursales.desafio.securyty.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User hasUser = userRepository.findByEmail(email);
        if(hasUser.getEmail().equals(email)){
            return new org.springframework.security.core.userdetails.User(
                    hasUser.getEmail(),
                    hasUser.getPassword(),
                    new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + email);
        }
    }
}
package com.foursales.desafio.services;

import com.foursales.desafio.models.User;
import com.foursales.desafio.models.UserDetailsImpl;
import com.foursales.desafio.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        try {
            User user = userRepository.findByEmail(username);
            return new UserDetailsImpl(user);
        } catch(UsernameNotFoundException unf){
            throw new UsernameNotFoundException(username);
        }
    }
}

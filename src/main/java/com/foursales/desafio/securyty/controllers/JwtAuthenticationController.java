package com.foursales.desafio.securyty.controllers;

import com.foursales.desafio.securyty.config.JwtTokenUtil;
import com.foursales.desafio.securyty.config.WebSecurityConfig;
import com.foursales.desafio.securyty.models.JwtResponse;
import com.foursales.desafio.securyty.models.User;
import com.foursales.desafio.securyty.repositories.UserRepository;
import com.foursales.desafio.securyty.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody User user) throws Exception {
        User hasUser = userRepository.findByEmail(user.getEmail());
        if(hasUser != null){
            authenticate(user.getEmail(), user.getPassword());

            final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
            final String token = jwtTokenUtil.generateToken(userDetails);
            return ResponseEntity.ok(new JwtResponse(token));
        } else {
            return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.FORBIDDEN);
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> registerNewUser(@RequestBody User user) throws Exception {

        User hasUser = userRepository.findByEmail(user.getEmail());
        if(hasUser == null){
            user.setPassword(WebSecurityConfig.passwordEncoder().encode(user.getPassword()));
            User newUser = userRepository.save(user);
            newUser.setPassword("[removido]");
            return ResponseEntity.ok(newUser);
        } else {
            hasUser.setPassword("[removido]");
            return ResponseEntity.of(Optional.of(hasUser));
        }
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
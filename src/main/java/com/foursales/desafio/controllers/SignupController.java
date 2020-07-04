package com.foursales.desafio.controllers;

import com.foursales.desafio.models.User;
import com.foursales.desafio.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping(name = "signup")
public class SignupController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> signup(@RequestBody User user) throws URISyntaxException {
        User newUser = userService.create(user);
        return ResponseEntity.created(new URI("")).body(newUser);
    }

}

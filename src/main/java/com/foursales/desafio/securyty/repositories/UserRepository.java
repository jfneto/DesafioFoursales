package com.foursales.desafio.securyty.repositories;

import com.foursales.desafio.securyty.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
}

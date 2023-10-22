package com.alvuberui.barber.domain.user.repository;

import com.alvuberui.barber.domain.user.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends MongoRepository<User, String> {

    Optional<User> findByEmail(String email);
}

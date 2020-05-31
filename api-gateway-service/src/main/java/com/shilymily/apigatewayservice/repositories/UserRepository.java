package com.shilymily.apigatewayservice.repositories;

import com.shilymily.apigatewayservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by hmaskai
 * 5/24/20.
 */

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
  Optional<User> findByUserName(String userName);
}

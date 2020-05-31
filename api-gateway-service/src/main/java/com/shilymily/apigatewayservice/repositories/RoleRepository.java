package com.shilymily.apigatewayservice.repositories;

import com.shilymily.apigatewayservice.models.Role;
import com.shilymily.apigatewayservice.models.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by hmaskai
 * 5/25/20.
 */

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(RoleName roleName);
}

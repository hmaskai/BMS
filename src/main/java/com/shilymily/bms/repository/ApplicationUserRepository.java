package com.shilymily.bms.repository;

import com.shilymily.bms.entity.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by hmaskai on 10/3/18.
 */
@Repository
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {

    Optional<ApplicationUser> findByEmail(String email);

    Optional<ApplicationUser> findByUsernameOrEmail(String username, String email);

    List<ApplicationUser> findByIdIn(List<Long> userIds);

    Optional<ApplicationUser> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}

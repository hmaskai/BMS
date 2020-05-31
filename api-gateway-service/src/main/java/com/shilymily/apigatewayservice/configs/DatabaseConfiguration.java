package com.shilymily.apigatewayservice.configs;

import com.shilymily.apigatewayservice.models.Role;
import com.shilymily.apigatewayservice.models.RoleName;
import com.shilymily.apigatewayservice.repositories.RoleRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * Created by hmaskai
 * 5/31/20.
 */

@Configuration
public class DatabaseConfiguration {
  @Bean
  public ApplicationRunner initializer(RoleRepository repository) {
    return args -> repository.saveAll(Arrays.asList(new Role(RoleName.ROLE_USER),
                                                    new Role(RoleName.ROLE_ADMIN)));
  }
}

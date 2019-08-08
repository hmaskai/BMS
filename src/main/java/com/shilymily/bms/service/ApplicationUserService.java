package com.shilymily.bms.service;

import com.shilymily.bms.entity.ApplicationUser;
import com.shilymily.bms.repository.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by hmaskai on 10/22/18.
 */
@Service
public class ApplicationUserService {
    @Autowired
    private ApplicationUserRepository applicationUserRepository;

    public ApplicationUser addApplicationUser(ApplicationUser applicationUser){
        applicationUserRepository.save(applicationUser);
        return applicationUser;
    }
}

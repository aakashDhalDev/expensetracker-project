package com.oggy.expensetrackerapi.service;

import com.oggy.expensetrackerapi.entity.User;
import com.oggy.expensetrackerapi.entity.UserPrincipal;
import com.oggy.expensetrackerapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepo;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Boolean userExists = userRepo.existsByEmail(email);
        User user = null;
        if(userExists){
            user = userRepo.findByEmail(email);
        }
        else {
            throw new UsernameNotFoundException("User 404");
        }

        return new UserPrincipal(user);
    }
}

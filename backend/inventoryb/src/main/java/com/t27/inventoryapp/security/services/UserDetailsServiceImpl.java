package com.t27.inventoryapp.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.t27.inventoryapp.model.*;
import com.t27.inventoryapp.repository.*;

import jakarta.transaction.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
    
    @Autowired
    UserRepository userRepo;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws  UsernameNotFoundException{
        User user = userRepo.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User: " + username + "not found"));

        return UserDetailsImpl.build(user);
    }

}

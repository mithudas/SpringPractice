package com.springmvc.service;

import com.springmvc.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by mithu on 20/1/18.
 */
@Service
public class SpringSecurityService {

    @Autowired
    UserRepository userRepository;


    public com.springmvc.entity.User  getCurrentUser(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        com.springmvc.entity.User systemUser = userRepository.findByUsername(user.getUsername());
        return systemUser;

    }


}

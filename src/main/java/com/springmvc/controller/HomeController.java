package com.springmvc.controller;


import com.springmvc.entity.User;
import com.springmvc.entity.VerificationToken;
import com.springmvc.repositories.UserRepository;
import com.springmvc.repositories.VerificationTokenRepository;
import com.springmvc.service.SpringSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Controller
public class HomeController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    SpringSecurityService springSecurityService;

    @Autowired
    VerificationTokenRepository verificationTokenRepository;

    @RequestMapping(value = "/")
    public String home(){
        return "home";
    }
    @RequestMapping(value = "/home")
    @ResponseBody
    public String index(){
        User user = springSecurityService.getCurrentUser();

        System.out.println(user.getUsername());
        return "this id for admin ";
    }

    @RequestMapping(value = "/login")
    public String login(){
        return "login";
    }


    @RequestMapping(value = "/register")
    public String register(){
        return "register";
    }

    @RequestMapping(value = "/registerUser")
    public String registerUser(User user,HttpServletRequest httpServletRequest){
        String token= UUID.randomUUID().toString();
        user.setEnabled(false);
        userRepository.save(user);
        String authUrl="http://"+httpServletRequest.getServerName()
                + ":"
                +httpServletRequest.getServerPort()
                + httpServletRequest.getContextPath()
                +"/registrationConfirmation"
                +"?token="+token;


        System.out.println(authUrl);

        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);
        verificationTokenRepository.save(verificationToken);

        return "login";

    }

    @RequestMapping(value = "/registrationConfirmation")
    public String registrationConfirmation(String token) {
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token);
        User user = verificationToken.getUser();
        user.setEnabled(true);
        userRepository.save(user);
        return "redirect:/login";
    }
}

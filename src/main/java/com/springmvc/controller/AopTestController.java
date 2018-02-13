package com.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by mithu on 13/2/18.
 */

@Controller
public class AopTestController  {

    @RequestMapping(value = "/aopTest")
    public String aopTest(){
        System.out.println("this is aop Test");

        return "redirect:/login";


    }


    @RequestMapping(value = "/aopWithException")
    public String aopWithException () throws Exception  {
            throw new Exception("my exception");

    }
}

package com.felixlaura.controller;

import com.felixlaura.GreetApiClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomRestController {

    @Autowired
    private GreetApiClient greetApiClient;

    private Logger logger = LoggerFactory.getLogger(WelcomRestController.class);

    @GetMapping("/welcome")
    public String welcomeMsg(){

        logger.info("welcomeMsg() execution - start");

        String welcomeMsg = "Welcome to Felix IT...!!!";
        String greetMsg = greetApiClient.invokeGreetApi();

        logger.info("welcomeMsg() execution - end");
        return greetMsg + ", " + welcomeMsg;
    }
}

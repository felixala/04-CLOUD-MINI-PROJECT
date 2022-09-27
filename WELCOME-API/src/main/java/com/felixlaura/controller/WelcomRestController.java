package com.felixlaura.controller;

import com.felixlaura.GreetApiClient;
import com.felixlaura.binding.Pet;
import com.felixlaura.binding.WelcomeResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class WelcomRestController {

    @Autowired
    private GreetApiClient greetApiClient;

    private Logger logger = LoggerFactory.getLogger(WelcomRestController.class);

    @GetMapping("/welcome")
    public WelcomeResponse welcomeMsg(){

        logger.info("welcomeMsg() execution - start");
        String welcomeMsg = "Welcome to Felix IT...!!!";
        String greetMsg = greetApiClient.invokeGreetApi();

        RestTemplate rt = new RestTemplate();
        String petEndPoint = "https://fbqm3v39o8.execute-api.ap-south-1.amazonaws.com/dev/pets/1";
        ResponseEntity<Pet> entiy = rt.getForEntity(petEndPoint, Pet.class);
        Pet petData = entiy.getBody();

        WelcomeResponse finalResponse = new WelcomeResponse();
        finalResponse.setWelcomeMsg(welcomeMsg);
        finalResponse.setGreetMsg(greetMsg);
        finalResponse.setPet(petData);

        logger.info("welcomeMsg() execution - end");
        return finalResponse;
    }
}

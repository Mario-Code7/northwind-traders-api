package com.pluralsight.NorthwindTradersAPI.model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
        // this method will respond to http://localhost:8080/
        @RequestMapping(path="/", method= RequestMethod.GET)
        public String index(
                @RequestParam(defaultValue="World") String country
        ) {
            return "Best " + country + "!";
        }
    }
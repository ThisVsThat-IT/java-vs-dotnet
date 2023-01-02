package com.thisvsthat.javavsdotnet.controller;

import com.thisvsthat.javavsdotnet.service.BirthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BirthController {

    @Autowired
    BirthService birthService;


    @GetMapping("/calculate/all")
    public void calculateAgeforAll() {
        birthService.calculateAgeForAll();
    }

}

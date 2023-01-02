package com.thisvsthat.javavsdotnet.controller;

import com.thisvsthat.javavsdotnet.model.BirthModel;
import com.thisvsthat.javavsdotnet.service.BirthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BirthController {

    @Autowired
    BirthService birthService;


    @GetMapping("/calculate/all")
    public List<BirthModel> calculateAgeforAll() {
        return birthService.calculateAndSaveAgeForAll();
    }

}

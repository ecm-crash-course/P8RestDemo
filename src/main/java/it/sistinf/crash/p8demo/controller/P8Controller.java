package it.sistinf.crash.p8demo.controller;

import org.springframework.web.bind.annotation.RestController;

import it.sistinf.crash.p8demo.model.ObjectStoreModel;
import it.sistinf.crash.p8demo.service.P8Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@RestController
public class P8Controller {

    @Autowired
    private P8Service service;

    @RequestMapping(value="/osname", method=RequestMethod.GET, produces = "application/json")
    public ObjectStoreModel osDisplayName() {
        return service.getObjectStoreList();
    }
    
    
}
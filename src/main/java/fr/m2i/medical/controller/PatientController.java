package fr.m2i.medical.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/patient")
public class PatientController {

    // http://localhost:8080/patient/test
    //@RequestMapping(value = "/test", method = RequestMethod.GET )
    @GetMapping(value = "/test")
    @ResponseBody
    public String testme( ){
        return "<h1>Bonjour</h1>";
    }

}

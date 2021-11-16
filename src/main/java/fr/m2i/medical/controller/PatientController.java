package fr.m2i.medical.controller;


import fr.m2i.medical.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientService ps;

    // http://localhost:8080/patient
    @GetMapping(value = "")
    public String list( Model model ){
        model.addAttribute("patients" , ps.findAll() );
        return "list_patient";
    }

    public PatientService getPs() {
        return ps;
    }

    public void setPs(PatientService ps) {
        this.ps = ps;
    }

}

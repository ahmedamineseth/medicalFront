package fr.m2i.medical.api;

import fr.m2i.medical.entities.PatientEntity;
import fr.m2i.medical.entities.VilleEntity;
import fr.m2i.medical.repositories.PatientRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/api/patient")
public class PatientAPIController {

    private PatientRepository pr;

    public PatientAPIController( PatientRepository pr ){
        this.pr = pr;
    }

    @GetMapping(value="" , produces = "application/json")
    public Iterable<PatientEntity> getAll(){
        return pr.findAll();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public PatientEntity get(@PathVariable int id) {
        return pr.findById(id).get();
    }
}

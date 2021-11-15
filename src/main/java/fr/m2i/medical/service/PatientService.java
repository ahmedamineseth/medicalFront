package fr.m2i.medical.service;

import fr.m2i.medical.api.PatientAPIController;
import fr.m2i.medical.entities.PatientEntity;
import fr.m2i.medical.entities.VilleEntity;
import fr.m2i.medical.repositories.PatientRepository;
import fr.m2i.medical.repositories.VilleRepository;
import org.springframework.stereotype.Service;

import java.io.InvalidObjectException;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PatientService {

    private PatientRepository pr;
    private VilleRepository vr;

    public PatientService(PatientRepository pr, VilleRepository vr ){
        this.pr = pr;
        this.vr = vr;
    }

    public Iterable<PatientEntity> findAll() {
        return pr.findAll();
    }

    public PatientEntity findPatient(int id) {
        return pr.findById(id).get();
    }

    public void delete(int id) {
        pr.deleteById(id);
    }

    private void checkPatient( PatientEntity p ) throws InvalidObjectException {

        if( p.getPrenom().length() <= 2 ){
            throw new InvalidObjectException("Prénom invalide");
        }

        if( p.getNom().length() <= 2 ){
            throw new InvalidObjectException("Nom invalide");
        }

        if( p.getAdresse().length() <= 10 ){
            throw new InvalidObjectException("Adresse invalide");
        }

        System.out.println( "Ville passée en param " + p.getVille().getId() );

        /* try{
            VilleEntity ve = vr.findById(p.getVille().getId()).get();
        }catch( Exception e ){
            throw new InvalidObjectException("Ville invalide");
        } */

        VilleEntity ve = vr.findById(p.getVille().getId()).orElseGet( null );
        if( ve == null ){
            throw new InvalidObjectException("Ville invalide");
        }
    }

    public void addPatient(PatientEntity p) throws InvalidObjectException {
        checkPatient(p);
        pr.save(p);
    }

    public void editPatient(int id, PatientEntity p) throws InvalidObjectException {
        checkPatient(p);

        /*Optional<PatientEntity> pe = pr.findById(id);
        PatientEntity pp = pe.orElse( null );*/

        try{
            PatientEntity pExistant = pr.findById(id).get();

            pExistant.setPrenom( p.getPrenom() );
            pExistant.setNom( p.getNom() );
            pExistant.setAdresse( p.getAdresse() );
            pExistant.setDatenaissance( p.getDatenaissance() );
            pExistant.setVille( p.getVille() );

            pr.save( pExistant );

        }catch ( NoSuchElementException e ){
            throw e;
        }

        pr.save(p);
    }
}

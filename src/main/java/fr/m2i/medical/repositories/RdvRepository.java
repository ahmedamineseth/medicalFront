package fr.m2i.medical.repositories;

import fr.m2i.medical.entities.RdvEntity;
import fr.m2i.medical.entities.VilleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;

public interface RdvRepository extends CrudRepository<RdvEntity, Integer> {

    public Iterable<RdvEntity> findAll();

    public Iterable<RdvEntity> findByPatient_Id( int p );

    @Query( value="SELECT * FROM rdv WHERE date(dateheure) = ?1", nativeQuery=true )
    public Iterable<RdvEntity> findByCustomByDate(Date dh); // , Date rdvDate

    @Query( value="SELECT * FROM rdv WHERE patient = ?1 AND  date(dateheure) = ?2", nativeQuery=true )
    public Iterable<RdvEntity> findByCustomByDateEtPatient(int patientId , Date dh); // , Date rdvDate

    @Query(value=" SELECT month(dateheure) as mois , year(dateheure) as annee , count(*) as nbre FROM rdv GROUP BY month(dateheure) , year(dateheure)", nativeQuery=true)
    List<Object> getRdvStats();

    // select * from rdv where patient = :1 AND date(dateheure) = ':2'

}

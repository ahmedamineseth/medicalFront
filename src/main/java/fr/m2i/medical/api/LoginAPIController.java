package fr.m2i.medical.api;

import fr.m2i.medical.entities.UserEntity;
import fr.m2i.medical.entities.VilleEntity;
import fr.m2i.medical.repositories.UserRepository;
import fr.m2i.medical.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginAPIController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @PostMapping( value = "/api/login" ,  consumes = "application/json" ,  produces = "application/json")
    public ResponseEntity<UserEntity> get( @RequestBody  UserEntity u ) {

        UserEntity user = userRepository.findByUsernameOrEmail( u.getUsername(), u.getUsername() );

        if(user == null) {
            return ResponseEntity.notFound().build();
        } else {
            System.out.println( "encoded pass : " + u.getPassword() );
            System.out.println( "pass en bd : " + user.getPassword() );

            // user exists
            if( encoder.matches( u.getPassword() , user.getPassword() ) ){
                user.setPassword("****");
                return ResponseEntity.ok(user);
            }
            return ResponseEntity.badRequest().build();
        }

    }
}

package com.cognizant.ptaedgeservice.controller;
import com.cognizant.ptaedgeservice.model.Authorities;
import com.cognizant.ptaedgeservice.model.Users;
import com.cognizant.ptaedgeservice.respository.AuthoritiesRepository;
import com.cognizant.ptaedgeservice.respository.UserRepository;
import com.cognizant.ptaedgeservice.security.JwtConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins="http://localhost:3000")
public class AuthController {

private final AuthenticationManager authenticationManager;
private final JwtConverter converter;
@Autowired
    UserRepository userRepo;
@Autowired
    AuthoritiesRepository authRepo;

    public AuthController(AuthenticationManager authenticationManager, JwtConverter converter) {
        this.authenticationManager = authenticationManager;
        this.converter = converter;
        System.out.println("AuthController");
    }
    @PostMapping("/authenticate")
    public ResponseEntity<Map<String, String>> authenticate(@RequestBody Map<String, String> credentials) {

        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(credentials.get("username"), credentials.get("password"));

        try {
            Authentication authentication = authenticationManager.authenticate(authToken);

            if (authentication.isAuthenticated()) {
                String jwtToken = converter.getTokenFromUser((User) authentication.getPrincipal());

                HashMap<String, String> map = new HashMap<>();
                map.put("jwt_token", jwtToken);

                return new ResponseEntity<>(map, HttpStatus.OK);
            }

        } catch (AuthenticationException ex) {
            System.out.println(ex);
        }

        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @PostMapping("/refresh_token")
    public ResponseEntity<Map<String, String>> refreshToken(UsernamePasswordAuthenticationToken principal) {
        User user = new User(principal.getName(), principal.getName(), principal.getAuthorities());
        String jwtToken = converter.getTokenFromUser(user);

        HashMap<String, String> map = new HashMap<>();
        map.put("jwt_token", jwtToken);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }
    @PostMapping("/Signup")
    public ResponseEntity<Map<String, String>> sinUpTheCredentials(@RequestBody Users user){
        Authorities auth=new Authorities();
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        user.setEnabled(true);
        System.out.println(user);
        userRepo.save(user);
        auth.setUsername(user.getUsername());
        auth.setAuthority("PTA_MEMBER");
        System.out.println(auth);
        authRepo.save(auth);
        Map<String, String> credentials=new HashMap<>();
        credentials.put("username",user.getUsername());
        credentials.put("password",user.getPassword());
             return new ResponseEntity<>(credentials, HttpStatus.OK);
    }
 }






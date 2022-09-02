package com.apati147.springsecurityjwt.userauth.controller;

import com.apati147.springsecurityjwt.userauth.model.JwtRequest;
import com.apati147.springsecurityjwt.userauth.model.JwtResponse;
import com.apati147.springsecurityjwt.userauth.service.CustomUserDetailsService;
import com.apati147.springsecurityjwt.userauth.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
public class JwtController {

    private static final Logger logger = Logger.getLogger(JwtController.class.getName());

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public ResponseEntity<JwtResponse> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        logger.info("JWT Request: " + jwtRequest);

        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Bad Credentials!!");
        }

        //Fine Area
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(jwtRequest.getUsername());
        String token = jwtUtil.generateToken(userDetails);

        logger.info("Token Generated: " + token);

        return ResponseEntity.ok(new JwtResponse(token));
    }
}

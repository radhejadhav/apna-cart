package com.auth.apna.controller;

import com.auth.apna.config.JwtConfig;
import com.auth.apna.dto.JwtRequest;
import com.auth.apna.dto.JwtResponse;
import com.auth.apna.service.CustomUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collection;

@RestController
@RequestMapping("/auth")
public class AuthController {

    Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtConfig jwtTokenUtil;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @PostMapping("/authenticate")
    public String welcome(){
        return "WELCOME";
    }

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        logger.info("username:: "+ authenticationRequest.getUsername());

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword(), userDetails.getAuthorities());

        logger.info(userDetails.getAuthorities()+" "+userDetails.getUsername()+"Ascasccasca");

        final String token = jwtTokenUtil.generateToken(userDetails);

        logger.info("Authenticated user:: " + userDetails.getUsername());
        return ResponseEntity.ok(new JwtResponse(userDetails.getUsername(),token, jwtTokenUtil.getExpirationDateFromToken(token)));
    }

    private void authenticate(String username, String password, Collection<? extends GrantedAuthority> roles) throws Exception {
        try {
            logger.info("is authenticated:: "+String.valueOf(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password, roles)).isAuthenticated()));
        } catch (DisabledException e) {
            logger.error(e.getMessage());
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            logger.error(e.getMessage());
            throw new Exception("INVALID_CREDENTIALS", e);
        }catch (Exception e){
            logger.info(Arrays.toString(e.getStackTrace()));
        }
    }
}

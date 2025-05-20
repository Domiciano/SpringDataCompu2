package co.edu.icesi.introspringboot2.controller.api;

import co.edu.icesi.introspringboot2.dto.AuthRequest;
import co.edu.icesi.introspringboot2.dto.AuthResponse;
import co.edu.icesi.introspringboot2.security.CustomUserDetail;
import co.edu.icesi.introspringboot2.service.impl.CustomUserDetailsService;
import co.edu.icesi.introspringboot2.util.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthRestController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest){
        try {
            //Autenticarnos en la app de Springboot y si tenemos exitos creamos el token
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getEmail(),
                            authRequest.getPassword()
                    )
            );

            UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getEmail());
            String token = jwtService.generateToken(userDetails);
            AuthResponse authResponse = new AuthResponse(token);
            return ResponseEntity.status(200).body(authResponse);
        }catch (BadCredentialsException e){
            e.printStackTrace();
            return ResponseEntity.status(400).body(Map.of("error", e.getMessage()));
        }
    }



}

package co.edu.icesi.introspringboot2.controller.api;

import co.edu.icesi.introspringboot2.dto.AuthRequest;
import co.edu.icesi.introspringboot2.dto.AuthResponse;
import co.edu.icesi.introspringboot2.dto.UserRequest;
import co.edu.icesi.introspringboot2.service.UserService;
import co.edu.icesi.introspringboot2.service.impl.CustomUserDetailsService;
import co.edu.icesi.introspringboot2.util.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
public class UserRestController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;


    //EN CONSTRUCCIÓN
    @GetMapping("/me")
    public ResponseEntity<?> me(HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(401).body(Map.of("error", "Token no presente o mal formado"));
        }
        String token = authHeader.substring(7);
        String username = jwtService.extractAllClaims(token).getSubject();
        var user = userService.findByEmail(username);
        return ResponseEntity.status(200).body(user);
    }
}

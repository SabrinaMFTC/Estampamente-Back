package br.com.estampamente.controllers;

import br.com.estampamente.entities.DTOs.LoginDTO;
import br.com.estampamente.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final AuthenticationManager authManager;
    private final TokenService jwtUtil;

    @Autowired
    public AuthController(AuthenticationManager authManager,
                          TokenService jwtUtil) {
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginDTO loginDTO){
        try{
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDTO.getUsername(),
                            loginDTO.getPassword()
                    )
            );
            String token =  jwtUtil.generateToken(authentication.getName());
            return token;
        } catch (Exception e){
                return "";
        }

    }
}

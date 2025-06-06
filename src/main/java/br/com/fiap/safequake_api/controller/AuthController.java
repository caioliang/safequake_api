package br.com.fiap.safequake_api.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.safequake_api.model.Token;
import br.com.fiap.safequake_api.model.User;
import br.com.fiap.safequake_api.service.AuthService;
import br.com.fiap.safequake_api.service.TokenService;

@RestController
public class AuthController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthService authService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public Token login(@RequestBody Credentials credentials){ 
        var user = authService.loadUserByUsername(credentials.email());
        if (!passwordEncoder.matches(credentials.password(), user.getPassword())){
            throw new BadCredentialsException("Senha incorreta");
        }
        
        return tokenService.createToken((User) user);
    }
    
}
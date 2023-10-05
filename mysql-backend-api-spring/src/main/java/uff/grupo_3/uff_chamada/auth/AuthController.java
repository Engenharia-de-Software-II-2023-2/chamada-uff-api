package uff.grupo_3.uff_chamada.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uff.grupo_3.uff_chamada.auth.dto.request.LoginRequest;
import uff.grupo_3.uff_chamada.auth.dto.request.RegisterRequest;
import uff.grupo_3.uff_chamada.auth.dto.response.LoginResponse;
import uff.grupo_3.uff_chamada.config.TokenService;
import uff.grupo_3.uff_chamada.modules.user.User;
import uff.grupo_3.uff_chamada.modules.user.UserRepository;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Auth", description = "Authentication Requests")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequest request){
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
            var auth = this.authenticationManager.authenticate(usernamePassword);
            User user = userRepository.findUserByUsername(request.getUsername()).orElseThrow(() -> new IllegalStateException("usuário ou senha incorretos"));
            
            var token = tokenService.generateToken((User) auth.getPrincipal());
            
            return ResponseEntity.ok(new LoginResponse(user.getId(), token));

        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterRequest request){
        if (this.userRepository.findByUsername(request.name()) != null){
            return ResponseEntity.badRequest().build();
        }
        
        String encryptedPassword = new BCryptPasswordEncoder().encode(request.password());
        User newUser = new User(
            request.username(),
            encryptedPassword,
            request.role(),
            request.name(),
            request.email()
        );

        this.userRepository.save(newUser);

        return ResponseEntity.ok().build();
    }
}

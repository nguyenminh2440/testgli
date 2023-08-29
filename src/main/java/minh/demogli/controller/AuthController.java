package minh.demogli.controller;

import minh.demogli.payload.LoginDto;
import minh.demogli.payload.RegisterDto;
import minh.demogli.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/auth/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {
        String response = authService.login(loginDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/auth/register")
    public ResponseEntity<String> register(RegisterDto registerDto) throws Exception {
        String response = authService.register(registerDto);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}

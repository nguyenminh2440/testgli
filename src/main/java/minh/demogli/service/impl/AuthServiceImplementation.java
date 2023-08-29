package minh.demogli.service.impl;

import minh.demogli.entity.Role;
import minh.demogli.entity.User;
import minh.demogli.payload.LoginDto;
import minh.demogli.payload.RegisterDto;
import minh.demogli.repository.RoleRepository;
import minh.demogli.repository.UserRepository;
import minh.demogli.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImplementation  implements AuthService {
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public AuthServiceImplementation(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String login(LoginDto loginDto) {
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
                        (loginDto.getUsernameOrEmail(),loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "User logged in successfully";
    }

    @Override
    public String register(RegisterDto registerDto) throws Exception {
        //check username exists
        if(userRepository.existsByUsername(registerDto.getUsername())) {
            throw new Exception("Username exists");
        }
        if(userRepository.existsByEmail(registerDto.getEmail())) {
            throw new Exception("Email exists");
        }

        User user = new User();
        user.setName(registerDto.getName());
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Set<Role> roles =  new HashSet<>();
        Role defaultRole = roleRepository.findByName("ROLE_USER").get();
        roles.add(defaultRole);
        user.setRoles(roles);

        userRepository.save(user);

        return "User registered";
    }
}

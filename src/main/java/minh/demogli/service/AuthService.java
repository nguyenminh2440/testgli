package minh.demogli.service;

import minh.demogli.payload.LoginDto;
import minh.demogli.payload.RegisterDto;

public interface AuthService {
    String login(LoginDto loginDto);

    String register(RegisterDto registerDto) throws Exception;
}

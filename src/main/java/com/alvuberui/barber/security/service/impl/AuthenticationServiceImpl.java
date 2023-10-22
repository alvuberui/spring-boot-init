package com.alvuberui.barber.security.service.impl;

import com.alvuberui.barber.domain.user.entity.JwtAuthenticationResponse;
import com.alvuberui.barber.domain.user.entity.UserRole;
import com.alvuberui.barber.domain.user.repository.IUserRepository;
import com.alvuberui.barber.security.DTO.LoginRequestDTO;
import com.alvuberui.barber.security.DTO.RegisterRequestDTO;
import com.alvuberui.barber.security.service.IAuthenticationService;
import com.alvuberui.barber.security.service.IJWTService;
import com.alvuberui.barber.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements IAuthenticationService {

    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final IJWTService jwtService;
    private final AuthenticationManager authenticationManager;





    @Override
    public JwtAuthenticationResponse register(RegisterRequestDTO request) {
        var user = User.builder().firstName(request.getFirstName()).lastName(request.getLastName())
                .email(request.getEmail()).password(passwordEncoder.encode(request.getPassword()))
                .role(UserRole.USER).name(request.getName()).build();
        userRepository.save(user);
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse login(LoginRequestDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
}

package com.alvuberui.barber.security.service;

import com.alvuberui.barber.domain.user.entity.JwtAuthenticationResponse;
import com.alvuberui.barber.security.DTO.LoginRequestDTO;
import com.alvuberui.barber.security.DTO.RegisterRequestDTO;

public interface IAuthenticationService {
    JwtAuthenticationResponse register(RegisterRequestDTO request);

    JwtAuthenticationResponse login(LoginRequestDTO request);
}

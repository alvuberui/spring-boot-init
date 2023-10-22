package com.alvuberui.barber.domain.user.service;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService {
    UserDetailsService userDetailsService();
}

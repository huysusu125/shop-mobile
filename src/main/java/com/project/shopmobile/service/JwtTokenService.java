package com.project.shopmobile.service;

import com.project.shopmobile.entity.User;

public interface JwtTokenService {
    public String generateOauth2AccessToken(User user);
}

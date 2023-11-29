package com.qgsoftware.lastdevonearth.backend.services;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtUserService extends UserService {

    String generateJwtUser(UserDetails user);

    UserDetails getUserFromJwt(String jwt);

}

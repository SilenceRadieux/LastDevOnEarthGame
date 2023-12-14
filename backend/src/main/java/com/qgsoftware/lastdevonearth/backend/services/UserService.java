package com.qgsoftware.lastdevonearth.backend.services;

import com.qgsoftware.lastdevonearth.backend.exception.AccountExistsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends UserDetailsService {

    Authentication authenticate(String username, String password) throws Exception;

    UserDetails save(String username, String password) throws AccountExistsException;

    Long getUserId(String username);

}

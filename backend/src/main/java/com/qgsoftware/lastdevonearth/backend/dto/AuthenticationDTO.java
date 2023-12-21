package com.qgsoftware.lastdevonearth.backend.dto;

import org.springframework.security.core.userdetails.UserDetails;

public record AuthenticationDTO(String token, UserDetails user) {

}



package com.qgsoftware.lastdevonearth.backend.services.impl;


import com.qgsoftware.lastdevonearth.backend.entities.UserEntity;
import com.qgsoftware.lastdevonearth.backend.exception.AccountExistsException;
import com.qgsoftware.lastdevonearth.backend.repositories.UserRepository;
import com.qgsoftware.lastdevonearth.backend.services.JwtUserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.Optional;

@Service
public class JwtUserServiceImpl implements JwtUserService {

    private final String signingKey;

    private final AuthenticationConfiguration authenticationConfiguration;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public JwtUserServiceImpl(@Value("${jwt.signing.key}")
            String signingKey,
            AuthenticationConfiguration authenticationConfiguration,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {
        this.signingKey = signingKey;
        this.authenticationConfiguration = authenticationConfiguration;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByPseudonym(username);
        if (user == null) {
            throw new UsernameNotFoundException("The owner could not be found");
        }
        return user;
    }

    @Override
    public Authentication authenticate(String username, String password) throws Exception {
        Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);
        return authenticationConfiguration.getAuthenticationManager().authenticate(authentication);
    }

    @Override
    public UserDetails save(String username, String email, String password) throws AccountExistsException {
        UserDetails existingUser = userRepository.findByPseudonym(username);
        if (existingUser != null) {
            throw new AccountExistsException();
        }
        UserEntity userEntity = new UserEntity(username, email, passwordEncoder.encode(password));
        userRepository.save(userEntity);
        return userEntity;
    }

    @Override
    public UserDetails getUserFromJwt(String jwt) {
        String username = getUsernameFromToken(jwt);
        return loadUserByUsername(username);
    }

    private String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(signingKey)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    @Override
    public String generateJwtUser(UserDetails user) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + 3600 * 1000);

        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, signingKey)
                .compact();
    }

    public void updatePseudonym(Long id, String pseudonym) {
        Optional<UserEntity> userEntity = userRepository.findById(id);
        if (userEntity.isPresent()) {
            userEntity.get().setPseudonym(pseudonym);
            userRepository.save(userEntity.get());
        }
    }

    public void updatePassword(Long id, String password) {
        Optional<UserEntity> userEntity = userRepository.findById(id);
        if (userEntity.isPresent()) {
            userEntity.get().setPassword(password);
            userRepository.save(userEntity.get());
        }
    }

    public void updateEmail(Long id, String email) {
        Optional<UserEntity> userEntity = userRepository.findById(id);
        if (userEntity.isPresent()) {
            userEntity.get().setEmail(email);
            userRepository.save(userEntity.get());
        }
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

}

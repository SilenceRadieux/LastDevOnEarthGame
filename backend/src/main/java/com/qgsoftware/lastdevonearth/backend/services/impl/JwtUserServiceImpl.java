package com.qgsoftware.lastdevonearth.backend.services.impl;

import com.qgsoftware.lastdevonearth.backend.entities.ArticleEntity;
import com.qgsoftware.lastdevonearth.backend.entities.CommentaryEntity;
import com.qgsoftware.lastdevonearth.backend.entities.UserEntity;
import com.qgsoftware.lastdevonearth.backend.entities.VoteEntity;
import com.qgsoftware.lastdevonearth.backend.exception.AccountExistsException;
import com.qgsoftware.lastdevonearth.backend.repositories.ArticleRepository;
import com.qgsoftware.lastdevonearth.backend.repositories.CommentaryRepository;
import com.qgsoftware.lastdevonearth.backend.repositories.UserRepository;
import com.qgsoftware.lastdevonearth.backend.repositories.VoteRepository;
import com.qgsoftware.lastdevonearth.backend.services.ArticleServiceModel;
import com.qgsoftware.lastdevonearth.backend.services.JwtUserService;
import com.qgsoftware.lastdevonearth.backend.utils.ArticleMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.transaction.Transactional;
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

    private final ArticleRepository articleRepository;

    private final VoteRepository voteRepository;

    private final CommentaryRepository commentaryRepository;

    private final PasswordEncoder passwordEncoder;

    ArticleMapper articleMapper = ArticleMapper.INSTANCE;

    @Autowired
    public JwtUserServiceImpl(@Value("${jwt.signing.key}")
                              String signingKey,
                              AuthenticationConfiguration authenticationConfiguration,
                              UserRepository userRepository,
                              ArticleRepository articleRepository,
                              CommentaryRepository commentaryRepository,
                              VoteRepository voteRepository,
                              PasswordEncoder passwordEncoder) {
        this.signingKey = signingKey;
        this.authenticationConfiguration = authenticationConfiguration;
        this.userRepository = userRepository;
        this.articleRepository = articleRepository;
        this.commentaryRepository = commentaryRepository;
        this.voteRepository = voteRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username);
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
    public UserDetails save(String username, String password) throws AccountExistsException {
        UserDetails existingUser = userRepository.findByUsername(username);
        if (existingUser != null) {
            throw new AccountExistsException();
        }
        UserEntity userEntity = new UserEntity(username, passwordEncoder.encode(password));
        userRepository.save(userEntity);
        return userEntity;
    }

    @Override
    public Long getUserId(String username) {
        UserEntity userEntity = userRepository.findByUsername(username);
        return (userEntity != null) ? userEntity.getId() : null;
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
        Date expiryDate = new Date(now.getTime() + 1000 * 60 * 60 *24);

        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, signingKey)
                .compact();
    }

    public void updateUsername(Long id, String username) {
        Optional<UserEntity> userEntity = userRepository.findById(id);
        if (userEntity.isPresent()) {
            userEntity.get().setUsername(username);
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


    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public ArticleServiceModel addVote(Long id, Long articleId, String voteString) {
        Optional<UserEntity> userOptional = userRepository.findById(id);
        Optional<ArticleEntity> articleOptional = articleRepository.findById(articleId);

        if (userOptional.isPresent() && articleOptional.isPresent()) {
            UserEntity user = userOptional.get();
            ArticleEntity article = articleOptional.get();

            Optional<VoteEntity> existingVoteOptional = voteRepository.findByUserAndArticle(user, article);
            VoteEntity existingVote = null;
            if (existingVoteOptional.isPresent()) {
                existingVote = existingVoteOptional.get();
                existingVote.setVote(voteString);
                voteRepository.save(existingVote);
            } else {
                VoteEntity vote = new VoteEntity();
                vote.setArticle(article);
                vote.setUser(user);
                vote.setVote(voteString);
                voteRepository.save(vote);
            }

            return articleMapper.articleEntityToArticleServiceModel(article);
        } else {
            return null;
        }
    }

    @Transactional
    public boolean addComment(Long id, Long articleId, String commentText) {
        Optional<UserEntity> userOptional = userRepository.findById(id);
        Optional<ArticleEntity> articleOptional = articleRepository.findById(articleId);

        if (userOptional.isPresent() && articleOptional.isPresent()) {
            UserEntity user = userOptional.get();
            ArticleEntity article = articleOptional.get();

            CommentaryEntity comment = new CommentaryEntity();
            comment.setArticle(article);
            comment.setUser(user);
            comment.setDateCreation(new Date());
            comment.setContent(commentText);
            commentaryRepository.save(comment);

            return true;
        } else {
            return false;
        }
    }




}

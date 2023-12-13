package com.qgsoftware.lastdevonearth.backend.controllers;

import com.qgsoftware.lastdevonearth.backend.dto.AuthenticationDTO;
import com.qgsoftware.lastdevonearth.backend.dto.NewUserDTO;
import com.qgsoftware.lastdevonearth.backend.dto.UserDTO;
import com.qgsoftware.lastdevonearth.backend.utils.UserMapper;
import com.qgsoftware.lastdevonearth.backend.exception.AccountExistsException;
import com.qgsoftware.lastdevonearth.backend.services.impl.JwtUserServiceImpl;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("user")
public class UserController {

    UserMapper userMapper = UserMapper.INSTANCE;

    private final JwtUserServiceImpl userService;

    public UserController(JwtUserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/{id}/article/{articleId}")
    public boolean addVote(@PathVariable("id") Long id, @PathVariable("articleId") Long articleId, @RequestBody String vote) {
        return userService.addVote(id, articleId);
    }

    @PostMapping("/signup")
    public UserDTO addUser(@RequestBody NewUserDTO newUserDTO) throws AccountExistsException {
         return userMapper.userDetailsToUserDTO(userService.save(newUserDTO.username(), newUserDTO.password()));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationDTO> login(@RequestBody NewUserDTO userDTO) {
        try {
            Authentication authentication = userService.authenticate(userDTO.username(), userDTO.password());
            if(authentication != null){
                UserDetails userDetails = (UserDetails) authentication.getPrincipal();
                String jwtToken = userService.generateJwtUser(userDetails);
                return ResponseEntity.ok(new AuthenticationDTO(jwtToken, userDetails));
            }
        } catch (Exception e) {
           e.printStackTrace();
            return ResponseEntity.status(401).body(null);
        }
        return new ResponseEntity<>(null, HttpStatusCode.valueOf(500));
    }


    @PutMapping("/{id}/username")
    public void updateUserUsername(@PathVariable("id") Long id, @RequestBody UserDTO userDTO) {
        userService.updateUsername(id, userDTO.username());
    }

    @PutMapping("/{id}/password")
    public void updateUserPassword(@PathVariable("id") Long id, @RequestBody UserDTO userDTO) {
        userService.updatePassword(id, userDTO.password());
    }


    @DeleteMapping("/{id}")
    public boolean deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return true;
    }



}

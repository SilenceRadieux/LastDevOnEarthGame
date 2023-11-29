package com.qgsoftware.lastdevonearth.backend.controllers;

import com.qgsoftware.lastdevonearth.backend.dto.UserDTO;
import com.qgsoftware.lastdevonearth.backend.exception.AccountExistsException;
import com.qgsoftware.lastdevonearth.backend.services.impl.JwtUserServiceImpl;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("user")
public class UserController {


    private final JwtUserServiceImpl userService;

    public UserController(JwtUserServiceImpl userService) {
        this.userService = userService;
    }


    @PostMapping
    public void addUser(@RequestBody UserDTO userDTO) throws AccountExistsException {
        userService.save(userDTO.pseudonym(), userDTO.email(), userDTO.password());
    }


    @PutMapping("/{id}/pseudonym")
    public void updateUserPseudonym(@PathVariable("id") Long id, @RequestBody UserDTO userDTO) {
        userService.updatePseudonym(id, userDTO.pseudonym());
    }

    @PutMapping("/{id}/password")
    public void updateUserPassword(@PathVariable("id") Long id, @RequestBody UserDTO userDTO) {
        userService.updatePassword(id, userDTO.password());
    }

    @PutMapping("/{id}/email")
    public void updateUserEmail(@PathVariable("id") Long id, @RequestBody UserDTO userDTO) {
        userService.updateEmail(id, userDTO.email());
    }

    @DeleteMapping("/{id}")
    public boolean deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return true;
    }

}

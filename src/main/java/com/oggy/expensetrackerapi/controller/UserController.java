package com.oggy.expensetrackerapi.controller;

import com.oggy.expensetrackerapi.entity.User;
import com.oggy.expensetrackerapi.entity.UserModel;
import com.oggy.expensetrackerapi.service.JwtService;
import com.oggy.expensetrackerapi.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
public class  UserController {

    @Autowired
    private UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<User> save(@Valid @RequestBody UserModel user){
        return new ResponseEntity<User>(userService.createUser(user), HttpStatus.CREATED);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> readUser(@PathVariable Long id){
        return new ResponseEntity<User>(userService .readUser(id), HttpStatus.OK);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@RequestBody UserModel user, @PathVariable Long id){
        return new ResponseEntity<User>(userService.updateUser(user,id), HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/login")
    public String login(@RequestBody UserModel user){

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));

        if(authentication.isAuthenticated())
            return jwtService.generateToken(user.getEmail());
        else
            return "Login Failure :(";
    }
}

package com.utcn.demo.controller;

import com.utcn.demo.model.Role;
import com.utcn.demo.model.User;
import com.utcn.demo.repository.dtos.userdto.UserDTO;
import com.utcn.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/create")
    public void createUser(@RequestBody UserDTO userDTO){
        userService.createUser(userDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<UserService.LoginResponse> login(@RequestBody UserDTO userDTO) {
        UserService.LoginResponse loginResponse = userService.login(userDTO);
        if (loginResponse.getStatus() == 200) {
            return ResponseEntity.ok(loginResponse);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(loginResponse);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<UserService.RegisterResponse> registerUser(@RequestBody UserDTO userDTO){
        UserService.RegisterResponse response = userService.registerUser(userDTO);
        if (response.getStatus() == 200) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @DeleteMapping("/delete/{cnp}")
    public void deleteUser(@PathVariable long cnp){
        userService.deleteUser(cnp);
    }
    //update user
    @PutMapping("/update")
    public void updateUser(@RequestBody UserDTO userDTO){
        userService.updateUser(userDTO);
    }
    //read users
    @GetMapping("/getAll")
    public List<User> retrieveUsers(){
        return userService.retrieveUsers();
    }
    //retrieve user
    @GetMapping("/get/{cnp}")
    public User retrieveUser(@PathVariable long cnp){
        return userService.retrieveUser(cnp);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("http://localhost:4200");
            }
        };
    }

    @PostMapping("/updateScore")
    public void updateUserScore(@RequestBody User user, @RequestParam double points) {
        userService.updateUserScore(user, points);
    }

    @PutMapping("/updateRole/{cnp}")
    public void updateRole(@PathVariable Long cnp, @RequestParam Role newRole) {
        userService.updateUserRole(cnp, newRole);
    }

}

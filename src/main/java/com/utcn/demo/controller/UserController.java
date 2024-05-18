package com.utcn.demo.controller;

import com.utcn.demo.model.User;
import com.utcn.demo.repository.dtos.userdto.UserDTO;
import com.utcn.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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
    public int login(@RequestBody UserDTO userDTO){
        return userService.login(userDTO);
    }

    @PostMapping("/register")
    public int registerUser(@RequestBody UserDTO userDTO){
        return userService.registerUser(userDTO);
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

}

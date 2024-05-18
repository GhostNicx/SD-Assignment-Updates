package com.utcn.demo.service;

import com.utcn.demo.model.User;
import com.utcn.demo.repository.dtos.userdto.UserDTO;
import com.utcn.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void createUser (UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setRole(userDTO.getRole());
        userRepository.save(user);
    }

    public int login(UserDTO userDTO) {
        User user = userRepository.findByUsername(userDTO.getUsername());
        if (user != null && user.getPassword().equals(userDTO.getPassword())) {
            return 200;
        }
        return 404;
    }
    //signup to return 200 if user is created
    public int registerUser(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setRole("user");
        userRepository.save(user);
        return 200; // Return status code 200 if user is successfully created
    }

    public List <User> retrieveUsers() {
        return (List<User>) userRepository.findAll();
    }

    public User retrieveUser(long cnp) {
        return userRepository.findById(cnp).orElse(null);
    }

    public void deleteUser(Long cnp) {
        userRepository.deleteById(cnp);
    }

    public void updateUser(UserDTO userDTO) {
        User user = userRepository.findById(userDTO.getCnp()).orElse(null);
        if (user != null) {
            user.setUsername(userDTO.getUsername());
            user.setPassword(userDTO.getPassword());
            user.setEmail(userDTO.getEmail());
            user.setRole(userDTO.getRole());
            userRepository.save(user);
        }
    }

}

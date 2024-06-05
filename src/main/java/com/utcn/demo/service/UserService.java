package com.utcn.demo.service;

import com.utcn.demo.model.Role;
import com.utcn.demo.model.User;
import com.utcn.demo.repository.dtos.userdto.UserDTO;
import com.utcn.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
//import org.springframework.security.core.context.SecurityContextHolder;

import java.awt.*;
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
        user.setRole(userDTO.getRole() != null ? userDTO.getRole() : Role.USER);
        userRepository.save(user);
    }

    /**public int login(UserDTO userDTO) {
        User user = userRepository.findByUsername(userDTO.getUsername());
        if (user != null && user.getPassword().equals(userDTO.getPassword())) {
            return 200;
        }
        return 404;
    }
     **/
    //signup to return 200 if user is created
    public RegisterResponse registerUser(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setRole(Role.USER);
        userRepository.save(user);
        return new RegisterResponse(200, user.getCnp(), user.getRole());
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
            user.setRole(userDTO.getRole() != null ? userDTO.getRole() : Role.USER);
            userRepository.save(user);
        }
    }
    public void updateUserScore(User user, double points) {
        user.updateScore(points);
        userRepository.save(user);
    }
    /*public User getCurrentUser(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        return userRepository.findByUsername(username);
    }*/

    public String getRole(long cnp) {
        User user = userRepository.findById(cnp).orElse(null);
        if (user != null) {
            return user.getRole().toString();
        }
        return null;
    }

    public LoginResponse login(UserDTO userDTO) {
        User user = userRepository.findByUsername(userDTO.getUsername());
        if (user != null && user.getPassword().equals(userDTO.getPassword())) {
            return new LoginResponse(200, user.getCnp(), "Login successful", user.getRole());
        }
        return new LoginResponse(404, 0, "Invalid username or password", null);
    }

    public void updateUserRole(long cnp, Role role) {
        User user = userRepository.findById(cnp).orElse(null);
        if (user != null) {
            user.setRole(role);
            userRepository.save(user);
        }
    }

    public static class LoginResponse {
        private int status;
        private long cnp;
        private String message;

        private Role role;

        public LoginResponse(int status, long cnp, String message, Role role) {
            this.status = status;
            this.cnp = cnp;
            this.message = message;
            this.role = role;
        }

        // Getters and setters
        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public long getCnp() {
            return cnp;
        }

        public void setCnp(long cnp) {
            this.cnp = cnp;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public Role getRole() {
            return role;
        }

        public void setRole(Role role) {
            this.role = role;
        }
    }

    public static class RegisterResponse {
        private int status;
        private long cnp;
        private Role role;

        public RegisterResponse(int status, long cnp, Role role) {
            this.status = status;
            this.cnp = cnp;
            this.role = role;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public long getCnp() {
            return cnp;
        }

        public void setCnp(long cnp) {
            this.cnp = cnp;
        }

        public Role getRole() {
            return role;
        }

        public void setRole(Role role) {
            this.role = role;
        }
    }

}

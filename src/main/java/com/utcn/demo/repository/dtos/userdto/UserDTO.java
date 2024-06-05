package com.utcn.demo.repository.dtos.userdto;

import com.utcn.demo.model.Role;

public class UserDTO {
    private long cnp;
    private String username;
    private String password;
    private Role role;
    private String email;

    public UserDTO(long cnp,String username, String password, Role role, String email) {
        this.cnp = cnp;
        this.username = username;
        this.password = password;
        this.role = role;
        this.email = email;
    }

    public UserDTO() {
    }

    public long getCnp() {
        return cnp;
    }
    public void setCnp(long cnp) {
        this.cnp = cnp;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

}

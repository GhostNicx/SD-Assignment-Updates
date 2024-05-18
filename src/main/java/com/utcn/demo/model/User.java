package com.utcn.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "cnp")
    private long cnp;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "role")
    private String role;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "author")
    @JsonIgnore
    private List<Question> questions;

    @OneToMany(mappedBy = "author")
    @JsonIgnore
    private List<Answer> answers;

    public User() {
    }

    public User(long cnp,String username, String password, String role, String email) {
        this.cnp = cnp;
        this.username = username;
        this.password = password;
        this.role = role;
        this.email = email;
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

    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}

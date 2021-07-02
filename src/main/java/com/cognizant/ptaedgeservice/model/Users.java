package com.cognizant.ptaedgeservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name="users")
public class Users {
    @Id
    private String username;
    @NotBlank(message="password Required")
    private String password;
    private String email;
    boolean enabled;
    @OneToMany(mappedBy = "username", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Authorities> authorities;

    public Users(String username, String password, List<GrantedAuthority> authorities) {
        this(username,password,null,null);

    }

    public Users() {
    }

    public Users(String username, String password, String email, boolean enabled, Set<Authorities> authorities) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.enabled = enabled;
        this.authorities = authorities;
    }

    public Users(String username, String password, String email, Set<Authorities> authorities) {
        this(username,password,email,true,authorities);

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Authorities> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authorities> authorities) {
        this.authorities = authorities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return enabled == users.enabled && Objects.equals(username, users.username) && Objects.equals(password, users.password) && Objects.equals(email, users.email) && Objects.equals(authorities, users.authorities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, email, enabled, authorities);
    }

    @Override
    public String toString() {
        return "Users{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", enabled=" + enabled +
                ", authorities=" + authorities +
                '}';
    }
}

package com.cognizant.ptaedgeservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name="authorities")
public class Authorities {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String username;
    private String authority;

    public Authorities() {
    }

    public Authorities(Integer id, String username, String authority) {
        this.id = id;
        this.username = username;
        this.authority = authority;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Authorities that = (Authorities) o;
        return Objects.equals(id, that.id) && Objects.equals(username, that.username) && Objects.equals(authority, that.authority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, authority);
    }

    @Override
    public String toString() {
        return "Authorities{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", authority='" + authority + '\'' +
                '}';
    }
}

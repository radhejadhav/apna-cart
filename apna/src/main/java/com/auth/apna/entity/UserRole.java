package com.auth.apna.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Entity
@Setter
@Table(name = "roles")
public class UserRole implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_id")
    @SequenceGenerator(name = "seq_id", sequenceName = "ID_GENERATOR")
    private Long id;
    private String role;

    @ManyToMany(mappedBy = "roles", cascade = CascadeType.MERGE)
    @JsonBackReference
    @Nullable
    private List<User> user;

    @Override
    public String getAuthority() {
        return this.role;
    }

    public Long getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public List<User> getUser() {
        return user;
    }
}

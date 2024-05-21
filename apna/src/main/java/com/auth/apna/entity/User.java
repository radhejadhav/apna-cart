package com.auth.apna.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Setter
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_id")
    @SequenceGenerator(name = "seq_id", sequenceName = "ID_GENERATOR")
    private Long id;
    private String name;

    private String contact;
    private String username;

    @JsonIgnore
    private String password;

//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<UserRole> roles;
    private boolean enabled;
    private boolean accountNonExpired;
    private boolean credentialsNonExpired;
    private boolean accountNonLocked;

    public User(Builder builder) {
        this.name = builder.name;
        this.username = builder.username;
        this.password = builder.password;
        this.contact = builder.contact;
        this.roles = builder.roles;
        this.enabled = builder.enabled;
        this.accountNonExpired = builder.accountNonExpired;
        this.credentialsNonExpired = builder.credentialsNonExpired;
        this.accountNonLocked = builder.accountNonLocked;
    }

    public User() {
        this.roles = new HashSet<>();
    }

    public static Builder builder(){
        return new Builder();
    }

    public static final class Builder{
        private String name;
        private String username;
        private String password;
        private String contact;
        private Collection<UserRole> roles = new HashSet<>();
        private boolean enabled;
        private boolean accountNonExpired;
        private boolean credentialsNonExpired;
        private boolean accountNonLocked;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder setContact(String contact) {
            this.contact = contact;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setRoles(Set<UserRole> roles) {
            this.roles = roles;
            return this;
        }

        public Builder setEnabled(boolean enabled) {
            this.enabled = enabled;
            return this;
        }

        public Builder setAccountNonExpired(boolean accountNonExpired) {
            this.accountNonExpired = accountNonExpired;
            return this;
        }

        public Builder setCredentialsNonExpired(boolean credentialsNonExpired) {
            this.credentialsNonExpired = credentialsNonExpired;
            return this;
        }

        public Builder setAccountNonLocked(boolean accountNonLocked) {
            this.accountNonLocked = accountNonLocked;
            return this;
        }

        public User build(){
            return new User(this);
        }
    }

    @Override
    public Collection<UserRole> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }
}

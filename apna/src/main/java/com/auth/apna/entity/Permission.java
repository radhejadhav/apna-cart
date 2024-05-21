//package com.auth.apna.entity;
//
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.stereotype.Service;
//
//@Entity
//@Getter
//@Service
//@NoArgsConstructor
//public class Permission implements GrantedAuthority {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_id")
//    @SequenceGenerator(name = "seq_id", sequenceName = "ID_GENERATOR")
//    private Long id;
//    private String permission;
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "role_id",referencedColumnName = "id")
//    @JsonBackReference
//    private UserRole role;
//
//    @Override
//    public String getAuthority() {
//        return this.permission;
//    }
//}

package com.apnacart.payment.dto;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@ToString
public class UserDetails implements Serializable {

//    public static UUID serialVersionUUID;
    private String sub;
    private String name;
    private boolean emailVerified;
    private String preferredUsername;
    private String givenName;
    private String familyName;
    private String email;
}

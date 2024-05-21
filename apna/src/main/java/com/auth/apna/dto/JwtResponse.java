package com.auth.apna.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@AllArgsConstructor
@Data
public class JwtResponse {

    private String username;
    private String jwtToken;
    private Date tokenExpiry;
}

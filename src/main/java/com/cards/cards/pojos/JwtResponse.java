package com.cards.cards.pojos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtResponse {
    private int status_code;
    private String username;
    private String role;
    private String type;
    private String token;
    private String issuedAt;
    private String expiry;
}
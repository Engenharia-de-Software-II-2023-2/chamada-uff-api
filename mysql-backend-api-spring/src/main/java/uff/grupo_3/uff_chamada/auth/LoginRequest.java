package uff.grupo_3.uff_chamada.auth;

import lombok.Data;

@Data
public class LoginRequest {
    private final String username;
    private final String password;
}

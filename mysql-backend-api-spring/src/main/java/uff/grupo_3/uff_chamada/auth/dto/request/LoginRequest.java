package uff.grupo_3.uff_chamada.auth.dto.request;

import lombok.Data;

@Data
public class LoginRequest {
    private final String username;
    private final String password;
}

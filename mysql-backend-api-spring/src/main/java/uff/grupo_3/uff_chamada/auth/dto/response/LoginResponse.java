package uff.grupo_3.uff_chamada.auth.dto.response;

import lombok.Data;

@Data
public class LoginResponse {
    private String token;
    private int id;

    public LoginResponse(String token, int id){
        this.token = token;
        this.id = id;
    }
}

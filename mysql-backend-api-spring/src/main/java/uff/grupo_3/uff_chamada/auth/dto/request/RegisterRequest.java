package uff.grupo_3.uff_chamada.auth.dto.request;

import uff.grupo_3.uff_chamada.modules.user.UserRole;

public record RegisterRequest(
    String username,
    String password,
    UserRole role,
    String name,
    String email
) {
}

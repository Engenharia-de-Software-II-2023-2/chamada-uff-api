package uff.grupo_3.uff_chamada.modules.user;

public enum UserRole {
    PROFESSOR("professor"),
    STUDENT("student");

    private String role;

    UserRole(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}

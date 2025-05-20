package co.edu.icesi.introspringboot2.dto;

import java.util.List;

public class UserResponse {

    private String email;
    private List<RoleDTO> roles;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<RoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDTO> roles) {
        this.roles = roles;
    }
}

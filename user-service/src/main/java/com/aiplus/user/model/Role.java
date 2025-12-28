package com.aiplus.user.model;

public enum Role {
    CLIENT,
    ADMIN,
    DEVELOPER;

    public String getAuthority() {
        return "ROLE_" + this.name();
    }

}

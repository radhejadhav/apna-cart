package com.auth.apna.dto;

public enum PermissionEnum {
    READ("READ"),
    WRITE("WRITE"),
    CREATE("CREATE"),
    DELETE("DELETE"),
    UPDATE("UPDATE");

    private String permission;

    PermissionEnum(String s) {
        this.permission = s;
    }

    public String permission() {
        return permission;
    }
}

package ru.kpfu.itis.kuzmin.skillshare.dto;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum Roles {
    ADMIN("ADMIN"),
    MODER("MODER"),
    AUTHOR("AUTHOR"),
    EMPLOYER("EMPLOYER"),
    USER("USER");

    private String name;
    private String fullName;

    Roles(String name) {
        this.name = name;
        this.fullName = "ROLE_" + name;
    }


    public String getName() {
        return name;
    }

    public String getFullName () {
        return fullName;
    }

}

package ru.kpfu.itis.kuzmin.skillshare.dto;

public enum Roles {
    ADMIN("ROLE_ADMIN"),
    MODER("ROLE_MODER"),
    AUTHOR("ROLE_AUTHOR"),
    EMPLOYER("ROLE_EMPLOYER"),
    USER("ROLE_USER");

    private String name;

    Roles(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

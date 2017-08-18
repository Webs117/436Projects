package com.adam.memegenerator;

/**
 * Created by Adam Giaccaglia on 8/18/2017.
 */

public class User {
    private static String username;
    private static String password;

    public User(String usr, String pass){
        this.username = usr;
        this.password = pass;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        User.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        User.password = password;
    }
}

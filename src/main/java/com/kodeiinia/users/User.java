package com.kodeiinia.users;


public class User {
    
    private int id;
    private String username;
    private String password;

    public User(String username, String password) {
        this.id = 55; //temp!! pitäisi hakea käyttäjien määrän ja lisätä yksi
        this.username = username;
        this.password = password;
    }
    
    
    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}

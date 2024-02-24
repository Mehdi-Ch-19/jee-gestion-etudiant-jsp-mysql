package com.example.demojee.model;

public class User {
    private int id;
    private String name;
    private  String email;
    private String adresse ;

    public User(int id, String name, String email, String adresse) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.adresse = adresse;
    }

    public User(String name, String email, String adresse) {
        this.name = name;
        this.email = email;
        this.adresse = adresse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
}

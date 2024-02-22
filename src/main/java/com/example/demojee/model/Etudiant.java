package com.example.demojee.model;

public class Etudiant {
    private int id;
    private String name;
    private  String email;
    private double note ;

    public Etudiant(int id, String name, String email, double note) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.note = note;
    }

    public Etudiant(String name, String email, double note) {
        this.name = name;
        this.email = email;
        this.note = note;
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

    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        this.note = note;
    }
}

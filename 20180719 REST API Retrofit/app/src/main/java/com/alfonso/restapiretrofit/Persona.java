package com.alfonso.restapiretrofit;

public class Persona {

    //ATRIBUTOS
    long id;
    String name;
    String email;
    String username;
    Address address;


    //CONSTRUCTOR VACÍO
    public Persona(){
    }

    //CONSTRUCTOR
    public Persona(long id, String name, String email, String username, Address address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.username = username;
        this.address = address;
    }


    //SETTERS AND GETTERS
    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}


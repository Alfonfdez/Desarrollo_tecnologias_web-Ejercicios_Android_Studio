package com.alfonso.restapiretrofitpost;

public class Post {

    //ATRIBUTOS
    long id;
    long userId;
    String title;
    String body;

    //CONSTRUCTOR VACÍO
    public Post() {
    }

    //CONSTRUCTOR

    public Post(long id, long userId, String title, String body) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.body = body;
    }

    //SETTERS AND GETTERS
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }



}

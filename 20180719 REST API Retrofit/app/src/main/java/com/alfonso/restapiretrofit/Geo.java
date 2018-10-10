package com.alfonso.restapiretrofit;

public class Geo {

    //ATRIBUTOS
    float lat;
    float lng;

    //CONSTRUCTOR VACÍO
    public Geo() {
    }

    //CONSTRUCTOR
    public Geo(float lat, float lng) {
        this.lat = lat;
        this.lng = lng;
    }

    //SETTERS AND GETTERS
    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLng() {
        return lng;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }



}

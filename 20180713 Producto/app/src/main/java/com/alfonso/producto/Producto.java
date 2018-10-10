package com.alfonso.producto;

public class Producto {

    String nombre;
    int unidades;
    boolean comprado;
    String imagen;


    //SETTERS AND GETTERS
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    public boolean isComprado() {
        return comprado;
    }

    public void setComprado(boolean comprado) {
        this.comprado = comprado;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }


    //CONSTRUCTOR
    public Producto(String nombre, int unidades, boolean comprado, String imagen) {
        this.nombre = nombre;
        this.unidades = unidades;
        this.comprado = comprado;
        this.imagen = imagen;
    }



}

package com.alfonso.basededatos_tareas;

import com.orm.SugarRecord;

public class Tarea extends SugarRecord<Tarea> { //LÍNEA NUEVA A AÑADIR PARA LIBRERÍA SUGAR ORM (BBDD)


    //ATRIBUTOS
    String nombre;
    int duracion;
    boolean hecha;


    //CONSTRUCTOR VACÍO PARA LIBRERÍA SUGAR ORM (BBDD)
    public Tarea() {
    }


    //CONSTRUCTOR
    public Tarea(String nombre, int duracion, boolean hecha) {
        this.nombre = nombre;
        this.duracion = duracion;
        this.hecha = hecha;
    }


    //SETTERS AND GETTERS
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public boolean isHecha() {
        return hecha;
    }

    public void setHecha(boolean hecha) {
        this.hecha = hecha;
    }



}

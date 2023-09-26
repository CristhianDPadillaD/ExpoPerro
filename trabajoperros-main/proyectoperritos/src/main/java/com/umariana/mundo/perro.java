/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.umariana.mundo;

import java.io.Serializable;

/**
 *
 * @author Grupo 7
 */
public class perro implements Serializable{
    
    // Creamos variables de las caracteristicas que va a llevar el perro
    String nombre;
    String raza;
    String imagen;
    int puntos;
    int edad;

     //método constructor vacio
    public perro() {
        
    }
    //método costructor lleno
    public perro(String nombre, String raza, String imagen, int puntos, int edad) {
        this.nombre = nombre;
        this.raza = raza;
        this.imagen = imagen;
        this.puntos = puntos;
        this.edad = edad;
    }
    
    //setters y getters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
    
     
    
}

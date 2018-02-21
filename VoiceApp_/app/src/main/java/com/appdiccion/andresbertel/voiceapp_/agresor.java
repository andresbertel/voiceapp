package com.appdiccion.andresbertel.voiceapp_;

/**
 * Created by user on 20/02/2018.
 */


/**
 * Created by andres.bertel on 25/10/2017.
 */

public class agresor {

    private int id;
    private String nombre;
    private String descripcion_agresor;
    private String descripcion_agresion;

    // Constructor de un objeto Contactos
    public agresor(int id, String nombre, String descripcion_agresor, String descripcion_agresion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion_agresor = descripcion_agresor;
        this.descripcion_agresion = descripcion_agresion;
    }

    // Recuperar/establecer ID
    public int getID() {
        return id;
    }
    public void setID(int id) {
        this.id = id;
    }

    // Recuperar/establecer NOMBRE
    public String getNOMBRE() {
        return nombre;
    }
    public void setNOMBRE(String nombre) {
        this.nombre = nombre;
    }

    // Recuperar/establecer TELEFONO
    public String getDesAgresor() {
        return descripcion_agresor;
    }
    public void setDesAgresor(String DesAgresor) {
        this.descripcion_agresor = DesAgresor;
    }

    // Recuperar/establecer EMAIL
    public String getdescripcion_agresion() {
        return descripcion_agresion;
    }
    public void setdescripcion_agresion(String DesAgresion) {
        this.descripcion_agresion = DesAgresion;
    }
}
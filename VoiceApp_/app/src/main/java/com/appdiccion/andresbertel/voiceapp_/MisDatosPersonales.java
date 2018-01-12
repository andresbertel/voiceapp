package com.appdiccion.andresbertel.voiceapp_;

/**
 * Created by user on 29/11/2017.
 */

public class MisDatosPersonales {

    private int id;
    private String nombre;
    private String alias;
    private String policia;

    // Constructor de un objeto Contactos
    public MisDatosPersonales(int id, String nombre, String alias, String policia) {
        this.id = id;
        this.nombre = nombre;
        this.alias = alias;
        this.policia = policia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getPolicia() {
        return policia;
    }

    public void setPolicia(String policia) {
        this.policia = policia;
    }
}
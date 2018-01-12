package com.appdiccion.andresbertel.voiceapp_.Modelo;

import java.io.Serializable;

/**
 * Created by Administrador on 30/10/2017.
 */

public class Datos implements Serializable{

    private String nombre_persona;
    private String nombre_cuestionario;
    private String  id_persona;


    public Datos(String nombre_persona, String nombre_cuestionario,String id_persona) {
        this.nombre_persona = nombre_persona;
        this.nombre_cuestionario = nombre_cuestionario;
        this.id_persona=id_persona;
    }

    public String getId_persona() {
        return id_persona;
    }

    public void setId_persona(String id_persona) {
        this.id_persona = id_persona;
    }


    public String getNombre_persona() {
        return nombre_persona;
    }

    public void setNombre_persona(String nombre_persona) {
        this.nombre_persona = nombre_persona;
    }

    public String getNombre_cuestionario() {
        return nombre_cuestionario;
    }

    public void setNombre_cuestionario(String nombre_cuestionario) {
        this.nombre_cuestionario = nombre_cuestionario;
    }
}

package com.appdiccion.andresbertel.voiceapp_.Modelo;

/**
 * Created by CASA on 30/10/2017.
 */

public class Persona {

    private String NOMBRE="";
    private Integer EDAD;
    private Integer ID;  //identificacion

    public Persona(String NOMBRE, Integer EDAD, Integer ID) {
        this.NOMBRE = NOMBRE;
        this.EDAD = EDAD;
        this.ID = ID;
    }


    public Persona() {

    }

    public String getNOMBRE() {
        return NOMBRE;
    }

    public void setNOMBRE(String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }

    public Integer getEDAD() {
        return EDAD;
    }

    public void setEDAD(Integer EDAD) {
        this.EDAD = EDAD;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }
}

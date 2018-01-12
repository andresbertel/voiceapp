
package com.appdiccion.andresbertel.voiceapp_.Modelo;

/**
 * Created by Administrador on 27/10/2017.
 */

public class Cuestionario {

    private String NOMBRE="";
    private String OBJETO="";
    private Integer NPREGUNTAS;
    private Integer ID;
    private Integer item;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Cuestionario(String NOMBRE, String OBJETO, Integer NPREGUNTAS,Integer ID,Integer item) {
        this.NOMBRE = NOMBRE;
        this.OBJETO = OBJETO;
        this.NPREGUNTAS = NPREGUNTAS;
        this.ID=ID;
        this.item=item;
    }


    public Cuestionario() {

    }

    public Integer getItem() {
        return item;
    }

    public void setItem(Integer item) {
        this.item = item;
    }

    public String getNOMBRE() {
        return NOMBRE;
    }

    public void setNOMBRE(String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }

    public String getOBJETO() {
        return OBJETO;
    }

    public void setOBJETO(String OBJETO) {
        this.OBJETO = OBJETO;
    }

    public Integer getNPREGUNTAS() {
        return NPREGUNTAS;
    }

    public void setNPREGUNTAS(Integer NPREGUNTAS) {
        this.NPREGUNTAS = NPREGUNTAS;
    }
}

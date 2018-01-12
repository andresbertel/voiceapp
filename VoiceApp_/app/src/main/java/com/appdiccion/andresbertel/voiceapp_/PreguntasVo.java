package com.appdiccion.andresbertel.voiceapp_;

/**
 * Created by CASA on 28/10/2017.
 */

public class PreguntasVo {

    private String Pregunta;
    private Integer id;
    private Integer puntos;
    private  Integer item;



    public PreguntasVo(String pregunta, Integer id, Integer puntos, Integer Item) {
        Pregunta = pregunta;
        this.id = id;
        this.puntos = puntos;
        this.item=Item;
    }

    public Integer getItem() {
        return item;
    }

    public void setItem(Integer item) {
        this.item = item;
    }

    public String getPregunta() {
        return Pregunta;
    }

    public void setPregunta(String pregunta) {
        Pregunta = pregunta;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPuntos() {
        return puntos;
    }

    public void setPuntos(Integer puntos) {
        this.puntos = puntos;
    }
}

package com.appdiccion.andresbertel.voiceapp_.Modelo;

/**
 * Created by Administrador on 30/10/2017.
 */

public class Responde {

    private Integer Idpersona;  //identificacion
    private Integer ID;   //idcuestinario
    private Integer Idpregunta;
    private Integer idRespuesta;


    public Responde(Integer idpersona, Integer ID, Integer idpregunta, Integer idRespuesta) {
        Idpersona = idpersona;
        this.ID = ID;
        Idpregunta = idpregunta;
        this.idRespuesta = idRespuesta;
    }

    public Integer getIdpersona() {
        return Idpersona;
    }

    public void setIdpersona(Integer idpersona) {
        Idpersona = idpersona;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getIdpregunta() {
        return Idpregunta;
    }

    public void setIdpregunta(Integer idpregunta) {
        Idpregunta = idpregunta;
    }

    public Integer getIdRespuesta() {
        return idRespuesta;
    }

    public void setIdRespuesta(Integer idRespuesta) {
        this.idRespuesta = idRespuesta;
    }
}

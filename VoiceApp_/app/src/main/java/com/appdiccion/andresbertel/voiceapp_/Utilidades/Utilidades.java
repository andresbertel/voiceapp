package com.appdiccion.andresbertel.voiceapp_.Utilidades;

/**
 * Created by Administrador on 27/10/2017.
 */

public class Utilidades {

    public static final int  DATA_VERSION=2;

    //DATOS PARA CREAR EL CUESTIONARIO
    public static final String TABLA_CUESTIONARIO="cuestionario";
    public  static final String CAMPO_NOMBRE="nombre";
    public static final String CAMPO_OBJETO="objeto";
    public static final String CAMPO_PREGUNTA="npreguntas";
    public static final String CAMPO_ID="id";
    //DATOS PARA FINALIZAR EL CUESTIONARIO

    //DATOS PERSONA
    public static final String TABLA_PERSONA="persona";
    public static final String CAMPO_NOMBRE_PERSONA="nombre";
    public static final String CAMPO_EDAD_PERSONA="edad";
    public static final String CAMPO_ID_PERSONA = "id";
    //FIN DATOS PERSONA


    //DATOS RESPONDER
    public static final String TABLA_RESPONDER="responder";
    public static final String CAMPO_ID_PERSONA_RESPONDER = "idPersona";
    public static final String CAMPO_ID_CUESTIONARIO_RESPONDER = "idCuestinario";
    public static final String CAMPO_ID_PREGUNTA_RESPONDER = "idPregunta";
    public static final String CAMPO_ID_RESPONDER="id";
    public static final String CAMPO_ID_RESPUESTA="idRespuesta";
    //FIN DATOS RESPONDER



    public static final String CREAR_TABLA_CUESTIONARIO="CREATE TABLE "+TABLA_CUESTIONARIO+" ("+CAMPO_ID+" INTEGER PRIMARY KEY, "+CAMPO_NOMBRE+" TEXT,"+CAMPO_OBJETO+" TEXT,"+CAMPO_PREGUNTA+" INTEGER)";
    public static final String CREAR_TABLA_PERSONA="CREATE TABLE "+TABLA_PERSONA+" ("+CAMPO_ID_PERSONA+" INTEGER PRIMARY KEY AUTOINCREMENT, "+CAMPO_NOMBRE_PERSONA+" TEXT, "+CAMPO_EDAD_PERSONA+" INTEGER)";

    public static final String CREAR_TABLA_RESPONDE="CREATE TABLE "+TABLA_RESPONDER+" ("+CAMPO_ID_RESPONDER+" INTEGER PRIMARY KEY AUTOINCREMENT , "+CAMPO_ID_RESPUESTA+" INTEGER, "+CAMPO_ID_PERSONA_RESPONDER+" INTEGER, "+CAMPO_ID_CUESTIONARIO_RESPONDER+" INTEGER, "+CAMPO_ID_PREGUNTA_RESPONDER+" INTEGER)";

}

package com.example.app_control_domotico.data.model;


public class ObjRegistro {
    private String ID;
    private String Fecha;

    public ObjRegistro(String id, String fecha){
        ID = id;
        Fecha = fecha;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }
}

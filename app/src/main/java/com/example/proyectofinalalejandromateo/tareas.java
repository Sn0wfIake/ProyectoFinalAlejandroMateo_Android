package com.example.proyectofinalalejandromateo;

public class tareas {
    private String fecha;
    private String nombreEvento;
    private String restoEvento;

    public tareas(String fecha, String nombreEvento, String restoEvento) {
        this.fecha = fecha;
        this.nombreEvento = nombreEvento;
        this.restoEvento = restoEvento;
    }

    public tareas() {
    }

    public tareas(String fecha, String nombreEvento) {
        this.fecha = fecha;
        this.nombreEvento = nombreEvento;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    public String getRestoEvento() {
        return restoEvento;
    }

    public void setRestoEvento(String restoEvento) {
        this.restoEvento = restoEvento;
    }

    @Override
    public String toString() {
        return nombreEvento;
    }
}

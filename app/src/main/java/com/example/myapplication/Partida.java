package com.example.myapplication;

public class Partida {
    private String nombre;
    private String tries;
    private String time;

    public String getNombre() {
        return nombre;
    }

    public String getTries() {
        return tries;
    }

    public String getTime() {
        return time;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTries(String tries) {
        this.tries = tries;
    }

    public void setTime(String time) {
        this.time = time;
    }



    public Partida(String nombre, String tries, String time) {
        this.nombre = nombre;
        this.tries = tries;
        this.time = time;
    }

    @Override
    public String toString() {
        return "Partida{" +
                "nombre='" + nombre + '\'' +
                ", tries=" + tries +
                ", time=" + time +
                '}';
    }
}

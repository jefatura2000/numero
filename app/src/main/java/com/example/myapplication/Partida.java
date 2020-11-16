package com.example.myapplication;

import android.net.Uri;

public class Partida {
    private String nombre;
    private String tries;
    private String time;
    private Uri imageUri;

    public String getNombre() {
        return nombre;
    }

    public String getTries() {
        return tries;
    }

    public String getTime() {
        return time;
    }

    public Uri getImageUri(){ return imageUri; }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTries(String tries) {
        this.tries = tries;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setImageUri(Uri imageUri) {
        this.imageUri = imageUri;
    }



    public Partida(String nombre, String tries, String time,Uri imageUri) {
        this.nombre = nombre;
        this.tries = tries;
        this.time = time;
        this.imageUri=imageUri;
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

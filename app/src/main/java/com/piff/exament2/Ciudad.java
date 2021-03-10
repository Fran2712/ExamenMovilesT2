package com.piff.exament2;

public class Ciudad {

    private String nombre;
    private Double latitud;
    private Double longitud;
    private String imagen;

    public Ciudad(String nombre, Double latitud, Double longitud, String imagen) {
        this.nombre = nombre;
        this.latitud = latitud;
        this.longitud = longitud;
        this.imagen = imagen;
    }

    public Ciudad() {
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}

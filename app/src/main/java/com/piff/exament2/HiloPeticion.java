package com.piff.exament2;

import android.os.Handler;

public class HiloPeticion implements Runnable{

    private String ciudad;
    private MapsActivity c;
    private int pos;

    public HiloPeticion(MapsActivity c, String ciudad, int i) {
        this.ciudad = ciudad;
        this.c = c;
        this.pos = i;
    }

    @Override
    public void run() {
        PedirDatos.conectar(pos,ciudad);

        Double lat = PedirDatos.PedirLatitud();
        Double lon = PedirDatos.PedirLongitud();
        String img = PedirDatos.PedirImagen();

        Ciudad c = new Ciudad( ciudad, lat,lon,img);

        AccesoFirebase.añadirCiudad(c);

    }

}

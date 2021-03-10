package com.piff.exament2;

import com.google.android.gms.maps.model.LatLng;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class PedirDatos {
    private static Document doc;
    private String nombre;

    public static void conectar(int i,String ciudad) {
        try {
            doc = Jsoup.connect("https://es.wikipedia.org/wiki/" + ciudad).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Double PedirLatitud() {
        String latitud = "";
        int cont = 0;
        Elements newsHeadlines = doc.getElementsByClass("latitude");
        for (Element headline : newsHeadlines) {
            if (cont == 0){
                latitud = headline.text();
                cont++;
            }

        }
        String p = latitud.replace("°", " ");
        p = p.replace("′", " ");
        p = p.replace("″", " ");
        String[] s = p.split(" ");

        Double grados = Double.parseDouble(s[0]);
        Double min = Double.parseDouble(s[1]);
        Double seg = Double.parseDouble(s[2]);
        String co = s[3];

        Double coor = grados + min / 60 + seg / 3600;

        if (co.equals("S")){
            coor = -coor;
        }
        return coor;
    }

    public static Double PedirLongitud() {
        String longitud = "";
        int cont = 0;
        Elements newsHeadlines = doc.getElementsByClass("longitude");
        for (Element headline : newsHeadlines) {
            if (cont == 0){
                longitud = headline.text();
                cont++;
            }
        }
        String p = longitud.replace("°", " ");
        p = p.replace("′", " ");
        p = p.replace("″", " ");
        String[] s = p.split(" ");

        Double grados=0.0;
        Double min = 0.0;
        Double seg = 0.0 ;
        String co;

        if(s[2].equals("O")){
            grados = Double.parseDouble(s[0]);
            min = Double.parseDouble(s[1]);
            co = s[2];
        }else if ( s[1].equals("O")){
            grados = Double.parseDouble(s[0]);
            min = 0.0;
            seg = 0.0;
            co = s[1];
        }else {
            grados = Double.parseDouble(s[0]);
            min = Double.parseDouble(s[1]);
            seg = Double.parseDouble(s[2]);
            co = s[3];
        }



        Double coor = grados + min / 60 + seg / 3600;

        if (co.equals("O")){
            coor = -coor;
        }
        return coor;
    }

    public static String PedirImagen() {
        String imagen = "";
        int cont = 0;
        Elements newsHeadlines = doc.getElementsByClass("image");
        for (Element headline : newsHeadlines) {
            if (cont == 0) {
                imagen = headline.child(cont).attr("src");
                cont++;
            }
        }

        return imagen;
    }
}

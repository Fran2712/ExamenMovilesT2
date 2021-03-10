package com.piff.exament2;

import androidx.fragment.app.FragmentActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, AccesoFirebase.comunicacionSacarCoords{

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        AccesoFirebase s = new AccesoFirebase(MapsActivity.this);
        AccesoFirebase.crearConexion();
        //AccesoFirebase.verDatos();

        ArrayList<String> ciudades = new ArrayList<String>();
        ciudades.add("Londres");
        ciudades.add("Pontevedra");
        ciudades.add("Móstoles");

        for (int i = 0 ; i< ciudades.size(); i++){
            HiloPeticion r = new HiloPeticion(MapsActivity.this,ciudades.get(i), i);
            Thread t1 = new Thread(r);
            t1.start();
        }
        AccesoFirebase.sacarDatos();





    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(0, 0);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                AlertDialog.Builder alertadd = new AlertDialog.Builder(MapsActivity.this);
                LayoutInflater factory = LayoutInflater.from(MapsActivity.this);

                final View view = factory.inflate(R.layout.alert_dialog_img, null);
                ImageView v = view.findViewById(R.id.imagen_wiki);
                Ciudad ciu = (Ciudad) marker.getTag();
                Glide.with(view).load("https:" + ciu.getImagen()).into(v);
                alertadd.setView(view);
                alertadd.setNeutralButton("Here!", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dlg, int sumthin) {

                    }
                });

                alertadd.show();
                return false;
            }
        });
    }


    @Override
    public void sacarCoords(Ciudad c) {
        LatLng ln = new LatLng(c.getLatitud(),c.getLongitud() );
        MarkerOptions mk = new MarkerOptions().position(ln);
        Marker marcador = mMap.addMarker(mk);
        marcador.setTag(c);
    }
}
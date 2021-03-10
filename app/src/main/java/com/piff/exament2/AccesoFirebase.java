package com.piff.exament2;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AccesoFirebase {

    private static DatabaseReference myRef;
    private static FirebaseDatabase database;
    private static MapsActivity clase_llamante;

    public AccesoFirebase(MapsActivity clase_llamante) {
        this.clase_llamante = clase_llamante;
    }

    public static void crearConexion() {
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Ciudades");
    }
    public static void añadirCiudad(Ciudad c) {
        String clave = c.getNombre().toLowerCase();
        myRef.child(clave).setValue(c);
    }
    public static void sacarDatos(){
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Iterable<DataSnapshot> daa = dataSnapshot.getChildren();
                for (DataSnapshot d : daa){
                    Ciudad c = d.getValue(Ciudad.class);
                    clase_llamante.sacarCoords(c);
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read valu
            }
        });
    }

    public interface comunicacionSacarCoords {
        public void sacarCoords(Ciudad c);
    }
}

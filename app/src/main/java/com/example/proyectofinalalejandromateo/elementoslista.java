package com.example.proyectofinalalejandromateo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class elementoslista extends AppCompatActivity {
    TextView fechaseleccionada;
    TextView textoevento;
    TextView tituloevento;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elementoslista);
        fechaseleccionada =findViewById(R.id.fecha);
        textoevento= findViewById(R.id.evento);
        tituloevento= findViewById(R.id.titulo);
        Intent intent= getIntent();
        fechaseleccionada.setText(intent.getStringExtra("fechaseleccionada"));
        textoevento.setText(intent.getStringExtra("textoevento"));
        tituloevento.setText(intent.getStringExtra("tituloevento"));
    }
}
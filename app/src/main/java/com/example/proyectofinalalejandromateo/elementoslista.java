package com.example.proyectofinalalejandromateo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class elementoslista extends AppCompatActivity {
    TextView fechaseleccionada;
    TextView textoevento;
    TextView tituloevento;
    EditText trabajo;

    Map agenda = new HashMap();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elementoslista);

        //Meto todos los datos de mi agenda
        agenda= recuperarPreferencias();


        trabajo = (EditText) findViewById(R.id.editTextTextMultiLine);
        fechaseleccionada = findViewById(R.id.fecha);
        textoevento = findViewById(R.id.evento);
        tituloevento = findViewById(R.id.titulo);
        Intent intent = getIntent();

        //
        fechaseleccionada.setText(intent.getStringExtra("fechaseleccionada"));
        tituloevento.setText(intent.getStringExtra("tituloevento"));

        textoevento.setText(muestraevento((String) fechaseleccionada.getText()));


        //Boton para añadir evento
        Button anadir = (Button) findViewById(R.id.anadir);
        anadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fecha = (String) fechaseleccionada.getText();
                String evento = String.valueOf(trabajo.getText());

                //Actualizo el hasmap
                insertaevento(fecha, evento);
                //Vuelco el hasmap en mis preferencias
                guardarPreferencias(fecha, evento);

                trabajo.setText("");
            }
        });
        //Boton para borrar el texto del evento
        Button borrartxt = (Button) findViewById(R.id.borrartxt);
        borrartxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fecha = (String) fechaseleccionada.getText();


                //Actualizo hasmap
                borrarevento(fecha);
                //Borro la preferencia de esta fecha para eliminar el dato
                borrarAgenda(fecha);
            }
        });
        //Boton volver
        Button volver = (Button) findViewById(R.id.volver);
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              finish();
            }
        });


        //Boton para borrar eventos

        Button borrar = (Button) findViewById(R.id.borrar);
        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String fecha = (String) fechaseleccionada.getText();
                // Build an AlertDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(elementoslista.this);

                // Set a title for alert dialog
                builder.setTitle("¿Deseas borrar esta tarea por completo?");

                // Ask the final question
                builder.setMessage("ESTA ACCION NO SE PUEDE DESHACER");

                // Set click listener for alert dialog buttons
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch(which){
                            case DialogInterface.BUTTON_POSITIVE:
                                // User clicked the Yes button
                                //Actualizo hasmap
                                borrarevento(fecha);
                                //Borro la preferencia de esta fecha para eliminar el dato
                                borrarpreferencias(fecha);
                                Toast.makeText(getApplicationContext(), "Si", Toast.LENGTH_SHORT).show();
                                finish();
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                // User clicked the No button
                                Toast.makeText(getApplicationContext(), "nel prro", Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }
                };


                // Set the alert dialog yes button click listener
                builder.setPositiveButton("Yes", dialogClickListener);

                // Set the alert dialog no button click listener
                builder.setNegativeButton("No",dialogClickListener);

                AlertDialog dialog = builder.create();
                // Display the alert dialog on interface
                dialog.show();
            }








        });
    }

    //Muestra evento segun la fecha
    private String muestraevento(String fecha) {
        String evento = (String) agenda.get(fecha);
        return evento;
    }

    //metodo para añadir eventos al arraylist
    private void insertaevento(String fecha, String evento) {

        agenda.put(fecha, evento);

        textoevento.setText(evento);

    }
    // Metodo para borrar eventos del arraylist
    private void borrarevento(String fecha) {
        agenda.remove(fecha);
        textoevento.setText(muestraevento(fecha));
    }


    //PREFERENCIAS
    //Guardo mis datos, tambien los actualiza
    private void guardarPreferencias(String fecha, String evento) {
        SharedPreferences preferences = getSharedPreferences("agenda", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(fecha, evento);
        //IMPORTANTE PARA QUE SE CREE EL ARCHIVO
        editor.commit();
    }
    //Extraigo todos los datos de mi archivo
    private Map recuperarPreferencias() {
        Map agenda = new HashMap();
        SharedPreferences preferences = getSharedPreferences("agenda", Context.MODE_PRIVATE);
        agenda = preferences.getAll();
        return agenda;
    }
    //Borro la preferencia
    private void borrarpreferencias(String fecha) {
        SharedPreferences preferences = getSharedPreferences("agenda", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(fecha).commit();

        SharedPreferences preferences1 = getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        SharedPreferences.Editor editorb = preferences1.edit();
        editorb.remove(fecha).commit();
    }
    private void borrarAgenda(String fecha) {
        SharedPreferences preferences = getSharedPreferences("agenda", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(fecha).commit();

    }

}
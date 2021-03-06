package com.example.proyectofinalalejandromateo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class agenda extends AppCompatActivity {
    Map agenda = new HashMap();
    ArrayList<tareas> ListaAgenda = new ArrayList<tareas>();
    ListView listaeventos;
    TextView vacio;
    Map textoAgenda = new HashMap();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_agenda);
        vacio = findViewById(R.id.vacio);
        //Lo primero es cargar mi agenda con los datos almacenados
        agenda = recuperarPreferencias();
        textoAgenda = recuperarAgenda();

        //Cargo mi Arraylist para volcar los datos en mi listView
        ListaAgenda = hasmapToArray((HashMap) agenda);
//Boton volver
        Button volver = (Button) findViewById(R.id.volver);
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //Genero mi lista, con los eventos del calendario
        listaeventos = (ListView) findViewById(R.id.listaeventos);
        if (ListaAgenda.isEmpty()) {
            vacio.setText("Nada que hacer!! Puedes añadir tareas desde el calendario");
        } else {
            vacio.setText("");
            ArrayAdapter<tareas> adaptador = new ArrayAdapter<tareas>(this, android.R.layout.simple_list_item_1, ListaAgenda);
            listaeventos.setAdapter(adaptador);
            listaeventos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    tareas t = ListaAgenda.get(position);
                    Intent intent = new Intent(getApplicationContext(), elementoslista.class);
                    intent.putExtra("fechaseleccionada", t.getFecha());
                    intent.putExtra("textoevento", t.getRestoEvento());
                    intent.putExtra("tituloevento", t.getNombreEvento());
                    startActivity(intent);

                }
            });
        }
    }


    //PREFERENCIAS
    //Guardo mis datos, tambien los actualiza
    private void guardarPreferencias(String fecha, String evento) {
        SharedPreferences preferences = getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(fecha, evento);
        //IMPORTANTE PARA QUE SE CREE EL ARCHIVO
        editor.commit();
    }

    //Borro la preferencia
    private void borrarpreferencias(String fecha) {
        SharedPreferences preferences = getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(fecha).commit();
    }

    //Extraigo todos los datos de mi archivo
    private Map recuperarPreferencias() {
        Map agenda = new HashMap();
        SharedPreferences preferences = getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        agenda = preferences.getAll();
        return agenda;
    }


    //Saco datos guardados de la agenda
    private Map recuperarAgenda() {
        Map agenda = new HashMap();
        SharedPreferences preferences = getSharedPreferences("agenda", Context.MODE_PRIVATE);
        agenda = preferences.getAll();
        return agenda;
    }

    //arraylist?ª?!?!?
    private ArrayList hasmapToArray(HashMap agenda) {
        Iterator<String> it = agenda.keySet().iterator();
        ArrayList ListaAgenda = new ArrayList<tareas>();
        while (it.hasNext()) {
            tareas t = new tareas();
            String clave = it.next();
            String valor = (String) agenda.get(clave);
            t.setFecha(clave);
            t.setNombreEvento(valor);
            ListaAgenda.add(new tareas(t.getFecha().toString(), t.getNombreEvento().toString()));

        }

        return ListaAgenda;
    }

    protected void onResume() {
        super.onResume();
        setContentView(R.layout.activity_agenda);
        vacio = findViewById(R.id.vacio);
        //Lo primero es cargar mi agenda con los datos almacenados
        agenda = recuperarPreferencias();
        textoAgenda = recuperarAgenda();

        //Cargo mi Arraylist para volcar los datos en mi listView
        ListaAgenda = hasmapToArray((HashMap) agenda);

        //Boton volver
        Button volver = (Button) findViewById(R.id.volver);
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //Genero mi lista, con los eventos del calendario
        listaeventos = (ListView) findViewById(R.id.listaeventos);
        if (ListaAgenda.isEmpty()) {
            vacio.setText("Nada que hacer!! Puedes añadir tareas desde el calendario");
        } else {
            vacio.setText("");
            ArrayAdapter<tareas> adaptador = new ArrayAdapter<tareas>(this, android.R.layout.simple_list_item_1, ListaAgenda);
            listaeventos.setAdapter(adaptador);
            listaeventos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    tareas t = ListaAgenda.get(position);
                    Intent intent = new Intent(getApplicationContext(), elementoslista.class);
                    intent.putExtra("fechaseleccionada", t.getFecha());
                    intent.putExtra("textoevento", t.getRestoEvento());
                    intent.putExtra("tituloevento", t.getNombreEvento());
                    startActivity(intent);

                }
            });
        }

    }


}
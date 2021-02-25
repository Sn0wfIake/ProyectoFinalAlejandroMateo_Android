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
    ArrayList <tareas> ListaAgenda = new ArrayList<tareas>();
    ListView listaeventos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);
        //Lo primero es cargar mi agenda con los datos almacenados
        agenda= recuperarPreferencias();
        //Cargo mi Arraylist para volcar los datos en mi listView
        ListaAgenda= hasmapToArray((HashMap) agenda);

        //Genero mi lista, con los eventos del calendario
        listaeventos= (ListView) findViewById(R.id.listaeventos);

        ArrayAdapter <tareas> adaptador = new ArrayAdapter<tareas>(this, android.R.layout.simple_list_item_1, ListaAgenda);
        listaeventos.setAdapter(adaptador);
        listaeventos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               tareas t = ListaAgenda.get(position);
               Intent intent = new Intent(getApplicationContext(), elementoslista.class);
               intent.putExtra("fechaseleccionada",t.getFecha() );
               intent.putExtra("textoevento", t.getNombreEvento());
               intent.putExtra("tituloevento", t.getRestoEvento());
               startActivity(intent);

              Toast.makeText(getApplicationContext(),"ID: "+t.getFecha()+" Nombre: "+t.getNombreEvento(), Toast.LENGTH_SHORT).show();
           }
       });
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

//Con este metodo me guardo los eventos de la agenda
    private void guardarDatos(String fecha, String evento) {
        SharedPreferences preferences = getSharedPreferences("agenda", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(fecha, evento);
        //IMPORTANTE PARA QUE SE CREE EL ARCHIVO
        editor.commit();
    }
    //arraylist?ª?!?!?
        private ArrayList hasmapToArray(HashMap agenda) {
            Iterator<String> it = agenda.keySet().iterator();
            ArrayList ListaAgenda = new ArrayList<tareas>();
            while(it.hasNext()){
                tareas t = new tareas();
                String clave = it.next();
                String valor = (String) agenda.get(clave);
                t.setFecha(clave);
                t.setNombreEvento(valor);
                ListaAgenda.add(new tareas(t.getFecha().toString(), t.getNombreEvento().toString()));

            }

            return ListaAgenda;
        }



}
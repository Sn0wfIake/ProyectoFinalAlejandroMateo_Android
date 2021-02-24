package com.example.proyectofinalalejandromateo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
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
    ArrayList ListaAgenda = new ArrayList();
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

        ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ListaAgenda);
        listaeventos.setAdapter(adaptador);
       listaeventos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

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
    //arraylist?ª?!?!?
        private ArrayList hasmapToArray(HashMap agenda) {
            Iterator<String> it = agenda.keySet().iterator();
            ArrayList ListaAgenda = new ArrayList<Map<String, String>>();
            while(it.hasNext()){
                String clave = it.next();
                String valor = (String) agenda.get(clave);
                String cosa="Evento:" + valor+" "+ clave;

                ListaAgenda.add(cosa);

            }

            return ListaAgenda;
        }



}
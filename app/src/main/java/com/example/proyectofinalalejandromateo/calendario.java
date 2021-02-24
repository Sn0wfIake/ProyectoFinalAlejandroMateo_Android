package com.example.proyectofinalalejandromateo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class calendario extends AppCompatActivity {
    CalendarView calendarView;
    TextView fechaseleccionada;
    TextView textoevento;
    EditText trabajo;
    Map agenda = new HashMap();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario);

        //Cargo mi variable agenda, con los datos de otras sesiones si hubiese
        agenda = recuperarPreferencias();

        //Mi calendario
        calendarView = (CalendarView) findViewById(R.id.calendarView);

        //La fecha que ha sido seleccionada en el calendario
        fechaseleccionada = (TextView) findViewById(R.id.evento);

        //TextView en el que se muestra el evento
        textoevento = (TextView) findViewById(R.id.texto);

        //Para introducir la tarea
        trabajo = (EditText) findViewById(R.id.editTextTextPersonName2);

        //Variable para almacenar la fecha seleccionada
        fechaseleccionada.setText("");

        //Donde se muestra tarea si hay
        textoevento.setText("");

        //Control del calendario
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {
                i1 += 1;
                String fecha = "Fecha: " + i2 + " - " + i1 + " - " + i;
                fechaseleccionada.setText(fecha);
                // Toast.makeText(getApplicationContext(), "Selected Date:\n"+fecha, Toast.LENGTH_LONG).show();

                //Si la agenda contiene la fecha seleccionada, se mostrara el evento, en caso contrario, el textoevento se vacia, esto es para que no se quede marcado el evento anterior
                if (agenda.containsKey(fecha)) {
                    textoevento.setText(muestraevento(fecha));
                } else {
                    textoevento.setText("");
                }
            }
        });

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

        //Boton para borrar eventos
        Button borrar = (Button) findViewById(R.id.borrarEvento);
        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fecha = (String) fechaseleccionada.getText();
                //Actualizo hasmap
                borrarevento(fecha);
                //Borro la preferencia de esta fecha para eliminar el dato
                borrarpreferencias(fecha);
            }
        });


    }


    //Muestra evento segun la fecha
    private String muestraevento(String fecha) {
        String evento = (String) agenda.get(fecha);
        return evento;
    }

    // Metodo para borrar eventos del arraylist
    private void borrarevento(String fecha) {
        agenda.remove(fecha);
        textoevento.setText(muestraevento(fecha));
    }

    //metodo para añadir eventos al arraylist
    private void insertaevento(String fecha, String evento) {

        agenda.put(fecha, evento);

        textoevento.setText(evento);

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

}
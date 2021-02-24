package com.example.proyectofinalalejandromateo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
        calendarView = (CalendarView) findViewById(R.id.calendarView);
        fechaseleccionada = (TextView) findViewById(R.id.evento);
         textoevento = (TextView) findViewById(R.id.texto);
         trabajo = (EditText) findViewById(R.id.editTextTextPersonName2);
        fechaseleccionada.setText("");
        textoevento.setText("");
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {
                i1+=1;
                String fecha="Fecha: " + i2 + " - " + i1 + " - " + i;
                fechaseleccionada.setText(fecha);
               // Toast.makeText(getApplicationContext(), "Selected Date:\n"+fecha, Toast.LENGTH_LONG).show();
                if (agenda.containsKey(fecha)){
                    textoevento.setText(muestraevento(fecha));
                }else {
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

                insertaevento(fecha, evento);
            }
        });
        Button borrar = (Button) findViewById(R.id.borrarEvento);
        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fecha = (String) fechaseleccionada.getText();

                borrarevento(fecha);
            }
        });



    }
    //Muestra evento segun la fecha
    private String muestraevento(String fecha) {
        String evento= (String) agenda.get(fecha);
        return evento;
    }

    // Metodo para borrar eventos del arraylist
    private void borrarevento(String fecha) {
        agenda.remove(fecha);
        textoevento.setText("");

    }
    //metodo para añadir eventos al arraylist
    private void insertaevento(String fecha, String evento) {

        agenda.put(fecha, evento);
        textoevento.setText(evento);

    }
}
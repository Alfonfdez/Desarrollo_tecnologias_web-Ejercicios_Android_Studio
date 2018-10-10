package com.alfonso.formulario;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //DECLARACIONES

    EditText profile;
    EditText email;
    EditText phone;
    EditText lock;
    Button guardar;
    Button borrar;
    EditText date;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //FINDVIEWs
        profile = findViewById(R.id.profile);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        lock = findViewById(R.id.lock);
        date = findViewById(R.id.date);
        guardar = findViewById(R.id.guardar);
        borrar = findViewById(R.id.borrar);


        /*---DECLARACIONES---*/


        //BOTÓN BORRAR
        borrar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                openDialog();
            }

        });

        //BOTÓN GUARDAR
        guardar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                //SI ALGÚN CAMPO ESTÁ VACÍO
                if (profile.equals("")) {
                    Toast.makeText(getApplicationContext(), "Hay un elemento vacío", Toast.LENGTH_LONG).show();
                } else if (email.equals("")) {
                    Toast.makeText(getApplicationContext(), "Hay un elemento vacío", Toast.LENGTH_LONG).show();
                } else if (phone.equals("")) {
                    Toast.makeText(getApplicationContext(), "Hay un elemento vacío", Toast.LENGTH_LONG).show();
                } else if (lock.equals("")) {
                    Toast.makeText(getApplicationContext(), "Hay un elemento vacío", Toast.LENGTH_LONG).show();
                }
                //SI TODOS LOS CAMPOS ESTÁN RELLENOS
                else {

                    Persona persona = new Persona();

                    String nombre = profile.getText().toString();
                    String mail = email.getText().toString();
                    String telefono = phone.getText().toString();
                    String pass = lock.getText().toString();

                    persona.setNombre(nombre);
                    persona.setMail(mail);
                    persona.setTelefono(telefono);
                    persona.setPass(pass);

                    Toast.makeText(getApplicationContext(), "Persona " + persona.getNombre() + " creada correctamente", Toast.LENGTH_LONG).show();

                }
            }

        });


        //SELECCIONAR FECHA
        date.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                openDatePickerDialog();

            }

        });



    }



    //ALERT DIALOG
    public void openDialog(){

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        //MODIFICAR EL TÍTULO
        alertDialogBuilder.setTitle(getString(R.string.app_name));

        alertDialogBuilder.setMessage( getString(R.string.alert))
                .setCancelable(false)

                //QUE QUEREMOS HACER SI CLICA SÍ
                .setPositiveButton(getString(R.string.btn_si), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        profile.setText("");
                        email.setText("");
                        phone.setText("");
                        lock.setText("");
                        }
                    })

                    //QUE QUEREMOS HACER SI CLICA NO -> NO HAREMOS NADA, CERRAMOS EL ALERT
                    .setNegativeButton(getString(R.string.btn_no), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel(); // No fem res, tanquem el Alert.
                        }
                    });

        AlertDialog alertDialog = alertDialogBuilder.create(); //crear el alert dialog
        alertDialog.show(); //MOSTRAR EN PANTALLA

    }



    //FUNCIÓN CALENDARIO A LA FECHA ACTUAL
    public void openDatePickerDialog() {

        DatePickerDialog dateDialog = new DatePickerDialog(this, pDateSetListener, pYear, pMonth, pDay);

        dateDialog.getDatePicker().setMinDate(System.currentTimeMillis()); //Fecha a partir de hoy, no se puede escoger fechas pasadas

        dateDialog.show();

    }



    //FUNCIÓN CALENDARIO
    int pDay, pMonth, pYear = 0;
    Activity activity = this;

    private DatePickerDialog.OnDateSetListener pDateSetListener =  new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

            pYear = year;
            pMonth = monthOfYear;
            pDay = dayOfMonth;

            String fromDay = Integer.toString(pDay);
            String fromMonth = Integer.toString(pMonth + 1);

            if (fromDay.length() == 1) fromDay = "0" + fromDay;
            if (fromMonth.length() == 1) fromMonth = "0" + fromMonth;

            //Seteamos la fecha
            date.setText(fromDay + "/" + fromMonth + "/" + pYear);
        }
    };



}

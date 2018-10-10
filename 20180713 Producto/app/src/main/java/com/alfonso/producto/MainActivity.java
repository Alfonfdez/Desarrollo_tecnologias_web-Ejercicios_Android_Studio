package com.alfonso.producto;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    //DECLARACIÓN
    EditText et1;
    Button btn1;
    ListView list;
    ArrayList<Producto> listadoproductos;
    //ArrayList<String> elementos_lista; //ANTIGUA LÍNEA
    EditText et2;
    CheckBox ch1;
    EditText et3;



    //ArrayAdapter<String> adapter; //ANTIGUA LÍNEA

    //NUEVA LÍNEA
    CustomAdapter adapter; //A MODIFICAR


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //FIND VIEWS BY ID
        et1 = findViewById(R.id.et1);
        btn1 = findViewById(R.id.btn1);
        list = findViewById(R.id.list);
        et2 = findViewById(R.id.et2);
        ch1 = findViewById(R.id.ch1);
        et3 = findViewById(R.id.et3);


        //elementos_lista = new ArrayList<String>(); //ANTIGUA LÍNEA
        listadoproductos = new ArrayList<Producto>();


        //ADAPTER
        //adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, elementos_lista );
        //adapter = new ArrayAdapter<String>(this, R.layout.row, R.id.texto, elementos_lista ); //LÍNEA QUE CAMBIAMOS
        adapter = new CustomAdapter (this, R.layout.row, listadoproductos);

        list.setAdapter(adapter);


        //AL CLICAR AL BOTÓN AÑADIREMOS LOS ELEMENTOS A UNA LISTA
        btn1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (!"".equals(et1.getText().toString())&&!"".equals(et2.getText().toString())&&!"".equals(et3.getText().toString())){

                    String nombre = et1.getText().toString();

                    String unidades = et2.getText().toString();
                    int unidades_final = Integer.parseInt(unidades); //PARSEAMOS A 'INT' EL VALOR 'STRING'

                    String imagen = et3.getText().toString();

                    listadoproductos.add(new Producto(nombre,unidades_final,ch1.isChecked(),imagen));

                    //elementos_lista.add(elemento); //ANTIGUA LÍNEA

                    adapter.notifyDataSetChanged();

                } else if("".equals(et1.getText().toString())||"".equals(et2.getText().toString())||"".equals(et3.getText().toString())){
                    Toast.makeText(getApplicationContext(), "Hay un elemento vacío", Toast.LENGTH_LONG).show();
                }

            }

        });



        //AL HACER UN 'CLICK' MOSTRAR LA POSICIÓN DEL ELEMENTO Y SU VALOR CON UN 'TOAST'
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                int itemPosition = i;

                //CONVERTIMOS A "STRING" LO QUE PONGA ESCRITO EN LA POSICIÓN "i"
                String itemValue = (String) list.getItemAtPosition(i);

                Toast.makeText(getApplicationContext(), "Posición:"+itemPosition+" Valor: " +itemValue , Toast.LENGTH_LONG).show();

            }

        });

        //AL HACER 'LONG CLICK' PREGUNTAR SI QUEREMOS BORRAR Y EJECUTAR LA OPCIÓN ESCOGIDA
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                int itemPosition = i;

                openDialog(itemPosition);

                return true;//'True' para que no ir a "click" sencillo

            }

        });



    }



    //ALERT DIALOG
    public void openDialog(final int itemPosition){

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        //MODIFICAR EL TÍTULO
        alertDialogBuilder.setTitle(getString(R.string.app_name));

        alertDialogBuilder.setMessage( "¿Seguro que lo quieres borrar?")
                .setCancelable(false)

                //QUE QUEREMOS HACER SI CLICA SÍ
                .setPositiveButton("Sí", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int id) {

                        listadoproductos.remove(itemPosition);

                        //elementos_lista.remove(itemPosition); //LÍNEA ANTIGUA

                        adapter.notifyDataSetChanged();

                    }
                })

                //QUE QUEREMOS HACER SI CLICA NO -> NO HAREMOS NADA, CERRAMOS EL ALERT
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.cancel(); // No fem res, tanquem el Alert.

                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create(); //crear el alert dialog
        alertDialog.show(); //MOSTRAR EN PANTALLA

    }





}


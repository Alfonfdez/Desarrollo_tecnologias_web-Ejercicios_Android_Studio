package com.alfonso.basededatos_tareas;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //DECLARACIONES
    ArrayList<Tarea> tareas;
    ListView listView;
    CustomAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //FIND VIEWS IDs
        listView = findViewById(R.id.listView);

        //ArrayList<Tarea>()
        tareas = new ArrayList<Tarea>();

        //LLAMA AL MÉTODO: CONSULTA A LA BASE DE DATOS
        getList();

        //ADAPTER
        adapter = new CustomAdapter(this, R.layout.row, tareas);

        //LISTVIEW
        listView.setAdapter(adapter);



        //AL HACER UN 'CLICK' MOSTRAR LA NUEVA PANTALLA Y MOSTRAR LA INFORMACIÓN DE ÉSTE
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                //Exemple Select by ID:
                long valor_id_base_datos = tareas.get(i).getId(); //ID ÚNICA DE ESTA TAREA
                Tarea tarea = Tarea.findById(Tarea.class,valor_id_base_datos);

                //add the function to perform here
                Intent intent = new Intent(MainActivity.this, Main3Activity.class);

                //RECOGEMOS LO GUARDADO EN LA TAREA ESPECÍFICA
                intent.putExtra("valor_id",valor_id_base_datos);
                intent.putExtra("valor_duracion",tarea.getDuracion());
                intent.putExtra("valor_nombre",tarea.getNombre());
                intent.putExtra("valor_hecha",tarea.isHecha());

                startActivity(intent);//IR A 'Main3Activity'

            }

        });


        //AL HACER 'LONG CLICK' PREGUNTAR SI QUEREMOS BORRAR Y EJECUTAR LA OPCIÓN ESCOGIDA
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                int itemPosition = i;

                openDialog(itemPosition);

                return true;//'True' para que no ir a "click" sencillo
            }

        });


    }

    // CÓDIGO PARA MOSTRAR LOS ICONOS DE LA CARPETA 'MENU'/'MENU_MAIN.XML' EN EL "ACTIONBAR"
    // FUENTE: https://www.journaldev.com/9357/android-actionbar-example-tutorial
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // CÓDIGO PARA CUANDO EL USUARIO CLICA EN CADA UNA DE LOS ICONOS EN EL "ACTIONBAR"
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {

            case R.id.add:

                //add the function to perform here
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);

                startActivity(intent);//IR A 'Main2Activity'

                return(true);

            case R.id.exit:

                //add the function to perform here
                finish();
                return(true);
        }

        return(super.onOptionsItemSelected(item));

    }




    //ACTIVITY 'onResume()' -> Surt sempre que entres a la pantalla, primer
    //cop, al tornar a obrir la app després de obrir
    //una altra, al tornar d’altre Activity
    @Override
    protected void onResume() {
        super.onResume();

        //FIND VIEWS IDs
        listView = findViewById(R.id.listView);

        //ArrayList<Tarea>()
        tareas = new ArrayList<Tarea>();

        //LLAMA AL MÉTODO: CONSULTA A LA BASE DE DATOS
        getList();

        //ADAPTER
        adapter = new CustomAdapter(this, R.layout.row, tareas);

        //LISTVIEW
        listView.setAdapter(adapter);

        adapter.notifyDataSetChanged();

    }

    //CONSULTA A LA BASE DE DATOS - NUEVA LÍNEA BBDD (BASE DE DATOS)
    public void getList() {
        tareas.clear();
        tareas.addAll(Tarea.listAll(Tarea.class));
    }

    //ALERT DIALOG
    public void openDialog(final int itemPosition){

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        //MODIFICAR EL TÍTULO
        alertDialogBuilder.setTitle(getString(R.string.app_name));

        alertDialogBuilder.setMessage( "¿Seguro que quieres borrar la tarea?")
                .setCancelable(false)

                //QUE QUEREMOS HACER SI CLICA SÍ
                .setPositiveButton("Sí", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int id) {

                        //ELIMINAR UNA TAREA DE LA BASE DE DATOS - NUEVA LÍNEA BBDD (BASE DE DATOS)
                        //Recoger el producto de la base de datos
                        tareas.get(itemPosition).delete();

                        //ELIMINAR DEL ArrayList<Tarea> tareas;
                        tareas.remove(itemPosition);

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

package com.alfonso.restapiretrofit;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    //DECLARACIÓN
    Button btn_retrofit;
    Button btn_anadir;
    ArrayList<Persona> personas;
    ListView listView;
    PersonaAdapter adapter;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //FIND VIEWERS BY ID
        btn_retrofit = findViewById(R.id.btn_retrofit);
        btn_anadir = findViewById(R.id.btn_anadir);
        listView = findViewById(R.id.listView);

        personas = new ArrayList<Persona>();

        //ADAPTER
        adapter = new PersonaAdapter(this, R.layout.row, personas);

        //LISTVIEW
        listView.setAdapter(adapter);


        //CLIC EN EL BOTÓN 'RETROFIT' PARA PINTAR EL LISTADO DE PERSONAS PROVENIENTE DE LA WEB-> https://jsonplaceholder.typicode.com/
        btn_retrofit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                //1º) COMPROBAR SI TENEMOS CONEXIÓN A INTERNET
                //SI CLICAMOS AL BOTÓN: Toast -> SÍ HAY CONEXIÓN A INTERNET ('True') / Toast -> NO HAY CONEXIÓN A INTERNET ('False')
                //SÍ HAY CONEXIÓN A INTERNET ('True')
                if(isConnected()){

                    Toast.makeText(getApplicationContext(),"SÍ hay conexión a Internet",Toast.LENGTH_LONG).show();


                    //ProgressDialog ('Loading' de Android, lo pararemos con la línea "dialog.dismiss()" después que cargue la lista)
                    //"dialog.dismiss()" está más abajo
                    progressDialog = ProgressDialog.show(MainActivity.this, "", "Loading. Please wait...", true);

                    //INICIALIZAR LA CLASE CREADA (PARA LIBRERÍA 'RETROFIT')
                    MyService service = RetrofitClientInstance.getRetrofitInstance().create(MyService.class);

                    //HACER LA LLAMADA Y CAPTURAR EL RESULTADO O ERROR
                    Call<List<Persona>> call = service.listPersonas();

                    call.enqueue(new Callback<List<Persona>>() {
                        @Override
                        public void onResponse(Call<List<Persona>> call, Response<List<Persona>> response) {

                            //ProgressDialog
                            //AQUÍ PARAREMOS EL 'LOADING' DE ANDROID (UNA VEZ CARGADA LA LISTA DE PERSONAS DE LA WEB Y PASADAS AL "ArrayList<Persona> personas" O LA LIBRERÍA 'GSON')
                            progressDialog.dismiss();

                            //AQUÍ EL RESULTADO
                            personas.addAll(response.body());



                            adapter.notifyDataSetChanged();


                        }
                        @Override
                        public void onFailure(Call<List<Persona>> call, Throwable t) {

                            //ProgressDialog
                            //AQUÍ PARAREMOS EL 'LOADING' DE ANDROID (UNA VEZ CARGADA LA LISTA DE PERSONAS DE LA WEB Y PASADAS AL "ArrayList<Persona> personas" O LA LIBRERÍA 'GSON')
                            progressDialog.dismiss();

                            //MOSTRAR ERRORES
                        }
                    });



                }
                //NO HAY CONEXIÓN A INTERNET ('False')
                else{
                    Toast.makeText(getApplicationContext(),"NO hay conexión a Internet",Toast.LENGTH_LONG).show();
                }

            }

        });



        //CLIC EN CUALQUIER PERSONA DEL LISTADO PARA IR A OTRA ACTIVITY "Main2Activity" Y VERLA EN DETALLE
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {


                //ProgressDialog ('Loading' de Android, lo pararemos con la línea "dialog.dismiss()" después que cargue la lista)
                //"dialog.dismiss()" está más abajo
                progressDialog = ProgressDialog.show(MainActivity.this, "", "Loading. Please wait...", true);


                //INICIALIZAR LA CLASE CREADA (PARA LIBRERÍA 'RETROFIT')
                MyService service = RetrofitClientInstance.getRetrofitInstance().create(MyService.class);

                //HACER LA LLAMADA Y CAPTURAR EL RESULTADO O ERROR
                Call<List<Persona>> call = service.listPersonas();

                call.enqueue(new Callback<List<Persona>>() {
                    @Override
                    public void onResponse(Call<List<Persona>> call, Response<List<Persona>> response) {

                        //ProgressDialog
                        //AQUÍ PARAREMOS EL 'LOADING' DE ANDROID (UNA VEZ CARGADA LA LISTA DE PERSONAS DE LA WEB Y PASADAS AL "ArrayList<Persona> personas" O LA LIBRERÍA 'GSON')
                        progressDialog.dismiss();

                        //SELECCIONAR PERSONA POR SU 'POSICION="i"' Y DESPUÉS POR SU "ID"
                        long valor_id_Retrofit = personas.get(i).getId(); //ID ÚNICA DE ESTA TAREA

                        //add the function to perform here
                        Intent intent = new Intent(MainActivity.this, Main2Activity.class);

                        //RECOGEMOS EL 'ID' DE ESTA TAREA ESPECÍFICA Y LO ENVIAMOS A LA 'Main3Activity'
                        intent.putExtra("valor_id",valor_id_Retrofit);

                        startActivity(intent);//IR A 'Main2Activity'*/


                    }
                    @Override
                    public void onFailure(Call<List<Persona>> call, Throwable t) {

                        //ProgressDialog
                        //AQUÍ PARAREMOS EL 'LOADING' DE ANDROID (UNA VEZ CARGADA LA LISTA DE PERSONAS DE LA WEB Y PASADAS AL "ArrayList<Persona> personas" O LA LIBRERÍA 'GSON')
                        progressDialog.dismiss();

                        //MOSTRAR ERRORES
                    }
                });

            }

        });

        //LONG CLIC EN CUALQUIER PERSONA DEL LISTADO Y PREGUNTAR SI QUEREMOS BORRAR MEDIANTE 'openDialog' Y EJECUTAR LA OPCIÓN ESCOGIDA (SÍ/NO)
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                int personPosition = i;

                //ProgressDialog ('Loading' de Android, lo pararemos con la línea "dialog.dismiss()" después que cargue la lista)
                //"dialog.dismiss()" está más abajo
                progressDialog = ProgressDialog.show(MainActivity.this, "", "Loading. Please wait...", true);

                openDialog(personPosition);

                return true;//'True' para que no ir a "click" sencillo

            }
        });


        //CLIC EN EL BOTÓN PARA AÑADIR UNA NUEVA PERSONA. NOS IREMOS A OTRA 'ACTIVITY'-> "Main3Activity"
        btn_anadir.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                //add the function to perform here
                Intent intent = new Intent(MainActivity.this, Main3Activity.class);

                startActivity(intent);//IR A 'Main3Activity'*/

            }

        });


    }

    /*/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\*/


    //ACTIVITY 'onResume()' -> Surt sempre que entres a la pantalla, primer
    //cop, al tornar a obrir la app després de obrir
    //una altra, al tornar d’altre Activity
    @Override
    protected void onResume() {

        super.onResume();

        // ¡¡AL TRATARSE DE UNA 'API' DE PRUEBAS, NO VEREMOS LOS RESULTADOS EN LA LISTA (POR ESO LO DEJO COMENTADO)!!

        /*
        //ProgressDialog ('Loading' de Android, lo pararemos con la línea "dialog.dismiss()" después que cargue la lista)
        //"dialog.dismiss()" está más abajo
        dialog = ProgressDialog.show(MainActivity.this, "", "Loading. Please wait...", true);

        //INICIALIZAR LA CLASE CREADA (PARA LIBRERÍA 'RETROFIT')
        MyService service = RetrofitClientInstance.getRetrofitInstance().create(MyService.class);

        //HACER LA LLAMADA Y CAPTURAR EL RESULTADO O ERROR
        Call<List<Persona>> call = service.listPersonas();

        call.enqueue(new Callback<List<Persona>>() {
            @Override
            public void onResponse(Call<List<Persona>> call, Response<List<Persona>> response) {
                //AQUÍ EL RESULTADO
                personas.addAll(response.body());

                //ProgressDialog
                //AQUÍ PARAREMOS EL 'LOADING' DE ANDROID (UNA VEZ CARGADA LA LISTA DE PERSONAS DE LA WEB Y PASADAS AL "ArrayList<Persona> personas" O LA LIBRERÍA 'GSON')
                dialog.dismiss();

                adapter.notifyDataSetChanged();


            }
            @Override
            public void onFailure(Call<List<Persona>> call, Throwable t) {
                //ProgressDialog
                //AQUÍ PARAREMOS EL 'LOADING' DE ANDROID (UNA VEZ CARGADA LA LISTA DE PERSONAS DE LA WEB Y PASADAS AL "ArrayList<Persona> personas" O LA LIBRERÍA 'GSON')
                dialog.dismiss();

                //MOSTRAR ERRORES
            }
        });

        */


    }

    /*/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\*/

    //FUNCIÓN PARA COMPROBAR SI TENEMOS CONEXIÓN A INTERNET
    public boolean isConnected(){
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }


    //ALERT DIALOG
    public void openDialog(final int personPosition){

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        //MODIFICAR EL TÍTULO
        alertDialogBuilder.setTitle(getString(R.string.app_name));

        alertDialogBuilder.setMessage( "¿Seguro que quieres eliminar esta Persona?")
                .setCancelable(false)

                //QUE QUEREMOS HACER SI CLICA SÍ
                .setPositiveButton("Sí", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int id) {

                        //INICIALIZAR LA CLASE CREADA (PARA LIBRERÍA 'RETROFIT')
                        MyService service = RetrofitClientInstance.getRetrofitInstance().create(MyService.class);

                        Call<Void> call_deletepersona = service.deletePersona(personPosition);
                        call_deletepersona.enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {

                                //ProgressDialog
                                //AQUÍ PARAREMOS EL 'LOADING' DE ANDROID (UNA VEZ CARGADA LA LISTA DE PERSONAS DE LA WEB Y PASADAS AL "ArrayList<Persona> personas" O LA LIBRERÍA 'GSON')
                                progressDialog.dismiss();

                                //TODO:Mostrar missatge d'esborrat.
                                Toast.makeText(MainActivity.this, "Borrado correctamente", Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {

                                //ProgressDialog
                                //AQUÍ PARAREMOS EL 'LOADING' DE ANDROID (UNA VEZ CARGADA LA LISTA DE PERSONAS DE LA WEB Y PASADAS AL "ArrayList<Persona> personas" O LA LIBRERÍA 'GSON')
                                progressDialog.dismiss();

                                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                            }
                        });

                        //ELIMINAR DEL ArrayList<Persona> personas;
                        personas.remove(personPosition);

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

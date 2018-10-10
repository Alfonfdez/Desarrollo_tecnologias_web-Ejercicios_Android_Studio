package com.alfonso.restapiretrofitpost;

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
    //Button btn_anadir;
    ArrayList<Post> posts;
    ListView listView;
    PostAdapter adapter;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //FIND VIEWERS BY ID
        btn_retrofit = findViewById(R.id.btn_retrofit);
        //btn_anadir = findViewById(R.id.btn_anadir);
        listView = findViewById(R.id.listView);

        posts = new ArrayList<Post>();

        //ADAPTER
        adapter = new PostAdapter(this, R.layout.row, posts);

        //LISTVIEW
        listView.setAdapter(adapter);


        //CLIC EN EL BOTÓN 'RETROFIT' PARA PINTAR EL LISTADO DE POSTS PROVENIENTE DE LA WEB-> https://jsonplaceholder.typicode.com/posts
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

                    //HACER LA LLAMADA PARA LISTAR TODA LA LISTA DE 'POSTS' DE LA WEB
                    Call<List<Post>> call = service.listPosts();

                    call.enqueue(new Callback<List<Post>>() {
                        @Override
                        public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                            //ProgressDialog
                            //AQUÍ PARAREMOS EL 'LOADING' DE ANDROID (UNA VEZ CARGADA LA LISTA DE 'POSTS' DE LA WEB Y PASADAS AL "ArrayList<Post> posts"
                            progressDialog.dismiss();

                            //AÑADIR LA LISTA DE 'POSTS' DE LA WEB A UN "ArrayList<Post> posts"
                            posts.addAll(response.body());

                            //REFRESCAR EL 'ADAPTER' PARA QUE PINTE EL LISTADO DE 'POSTS' DESDE EL "ArrayList<Post> posts"
                            adapter.notifyDataSetChanged();


                        }
                        @Override
                        public void onFailure(Call<List<Post>> call, Throwable t) {

                            //ProgressDialog
                            //AQUÍ PARAREMOS EL 'LOADING' DE ANDROID (UNA VEZ CARGADA LA LISTA DE 'POSTS' DE LA WEB Y PASADAS AL "ArrayList<Post> posts"
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



        //CLIC EN CUALQUIER 'POST' DEL LISTADO PARA IR A OTRA ACTIVITY "Main2Activity" Y VERLO EN DETALLE
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {


                //ProgressDialog ('Loading' de Android, lo pararemos con la línea "dialog.dismiss()" después que cargue la lista)
                //"dialog.dismiss()" está más abajo
                progressDialog = ProgressDialog.show(MainActivity.this, "", "Loading. Please wait...", true);


                //INICIALIZAR LA CLASE CREADA (PARA LIBRERÍA 'RETROFIT')
                MyService service = RetrofitClientInstance.getRetrofitInstance().create(MyService.class);

                //HACER LA LLAMADA PARA LISTAR TODA LA LISTA DE 'POSTS' DE LA WEB
                Call<List<Post>> call = service.listPosts();

                call.enqueue(new Callback<List<Post>>() {
                    @Override
                    public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                        //ProgressDialog
                        //AQUÍ PARAREMOS EL 'LOADING' DE ANDROID (UNA VEZ CARGADA LA LISTA DE 'POSTS' DE LA WEB Y PASADAS AL "ArrayList<Post> posts"
                        progressDialog.dismiss();

                        //SELECCIONAR 'POST' POR SU 'POSICION="i"' (EN EL "ArrayList<Post> posts") Y DESPUÉS POR SU "ID"
                        long valor_id_Retrofit_post = posts.get(i).getId(); //ID ÚNICA DE ESTA TAREA

                        //SELECCIONAR 'PERSONA' POR SU 'POSICION="i"' (EN EL "ArrayList<Post> posts") Y DESPUÉS POR SU "userId"
                        long valor_id_Retrofit_persona = posts.get(i).getUserId(); //ID ÚNICA DE ESTA TAREA


                        //CON 'INTENT' IREMOS A OTRA 'ACTIVITY' (PANTALLA) Y LE ENVIAREMOS UNOS VALORES
                        Intent intent = new Intent(MainActivity.this, Main2Activity.class);

                        //RECOGEMOS EL 'ID' DE ESTE 'POST' ESPECÍFICO Y LO ENVIAREMOS A LA 'Main2Activity'
                        intent.putExtra("valor_id_post",valor_id_Retrofit_post);

                        //RECOGEMOS EL 'userId' DE ESTA 'PERSONA' ESPECÍFICA (QUE HA CREADO EL POST EN CUESTIÓN) Y LO ENVIAREMOS A LA 'Main2Activity'
                        intent.putExtra("valor_id_persona",valor_id_Retrofit_persona);

                        startActivity(intent);//IR A 'Main2Activity'*/


                    }
                    @Override
                    public void onFailure(Call<List<Post>> call, Throwable t) {

                        //ProgressDialog
                        //AQUÍ PARAREMOS EL 'LOADING' DE ANDROID (UNA VEZ CARGADA LA LISTA DE 'POSTS' DE LA WEB Y PASADAS AL "ArrayList<Post> posts"
                        progressDialog.dismiss();

                        //MOSTRAR ERRORES
                    }
                });

            }

        });


        //LONG CLIC EN CUALQUIER 'POST' DEL LISTADO Y PREGUNTAR SI QUEREMOS BORRAR MEDIANTE 'openDialog' Y EJECUTAR LA OPCIÓN ESCOGIDA (SÍ/NO)
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                int postPosition = i;

                //ProgressDialog ('Loading' de Android, lo pararemos con la línea "dialog.dismiss()" después que cargue la lista)
                //"dialog.dismiss()" está más abajo
                progressDialog = ProgressDialog.show(MainActivity.this, "", "Loading. Please wait...", true);

                openDialog(postPosition);

                return true;//'True' para que no ir a "click" sencillo

            }
        });


       /* //CLIC EN EL BOTÓN PARA AÑADIR UN NUEVO 'POST'. NOS IREMOS A OTRA 'ACTIVITY'-> "Main3Activity"
        btn_anadir.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                //add the function to perform here
                Intent intent = new Intent(MainActivity.this, Main3Activity.class);

                startActivity(intent);//IR A 'Main3Activity'

            }

        });*/


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
        Call<List<Post>> call = service.listPosts();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                //AQUÍ EL RESULTADO
                posts.addAll(response.body());

                //ProgressDialog
                //AQUÍ PARAREMOS EL 'LOADING' DE ANDROID (UNA VEZ CARGADA LA LISTA DE 'POSTS' DE LA WEB Y PASADAS AL "ArrayList<Post> posts"
                dialog.dismiss();

                adapter.notifyDataSetChanged();


            }
            @Override
            public void onFailure(Call<List<Persona>> call, Throwable t) {
                //ProgressDialog
                //AQUÍ PARAREMOS EL 'LOADING' DE ANDROID (UNA VEZ CARGADA LA LISTA DE 'POSTS' DE LA WEB Y PASADAS AL "ArrayList<Post> posts"
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
    public void openDialog(final int postPosition){

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        //MODIFICAR EL TÍTULO
        alertDialogBuilder.setTitle(getString(R.string.app_name));

        alertDialogBuilder.setMessage( "¿Seguro que quieres eliminar este Post?")
                .setCancelable(false)

                //QUE QUEREMOS HACER SI CLICA SÍ
                .setPositiveButton("Sí", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int id) {

                        //INICIALIZAR LA CLASE CREADA (PARA LIBRERÍA 'RETROFIT')
                        MyService service = RetrofitClientInstance.getRetrofitInstance().create(MyService.class);

                        //ELIMINAR EL 'POST' SELECCIONADO POR SU POSICIÓN ("postPosition") DENTRO DEL "ArrayList<Post> posts"
                        Call<Void> call_deletepost = service.deletePost(postPosition);
                        call_deletepost.enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {

                                //ProgressDialog
                                //AQUÍ PARAREMOS EL 'LOADING' DE ANDROID (UNA VEZ CARGADA LA LISTA DE 'POSTS' DE LA WEB Y PASADAS AL "ArrayList<Post> posts"
                                progressDialog.dismiss();

                                //TODO:Mostrar missatge d'esborrat.
                                Toast.makeText(MainActivity.this, "Borrado correctamente", Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {

                                //ProgressDialog
                                //AQUÍ PARAREMOS EL 'LOADING' DE ANDROID (UNA VEZ CARGADA LA LISTA DE 'POSTS' DE LA WEB Y PASADAS AL "ArrayList<Post> posts"
                                progressDialog.dismiss();

                                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                            }
                        });

                        //ELIMINAR DEL "ArrayList<Post> posts"
                        posts.remove(postPosition);

                        adapter.notifyDataSetChanged();

                    }
                })

                //QUE QUEREMOS HACER SI CLICA NO -> NO HAREMOS NADA, CERRAREMOS EL 'ALERT DIALOG'
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.cancel(); // NO HACEMOS NADA, CERRAREMOS EL 'ALERT DIALOG'

                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create(); //CREAR EL 'ALERT DIALOG'
        alertDialog.show(); //MOSTRAR EN PANTALLA

    }





}

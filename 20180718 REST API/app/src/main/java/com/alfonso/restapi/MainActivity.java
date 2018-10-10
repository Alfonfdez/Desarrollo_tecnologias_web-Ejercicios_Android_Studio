package com.alfonso.restapi;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    //DECLARACIÓN
    Button btn_conexion;
    Button btn_retrofit;
    ArrayList<Persona> personas;
    ListView listView;
    PersonaAdapter adapter;
    ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //FIND VIEWERS BY ID
        btn_conexion = findViewById(R.id.btn_conexion);
        btn_retrofit = findViewById(R.id.btn_retrofit);
        listView = findViewById(R.id.listView);

        personas = new ArrayList<Persona>();

        //ADAPTER
        adapter = new PersonaAdapter(this, R.layout.row, personas);

        //LISTVIEW
        listView.setAdapter(adapter);




        //1º) COMPROBAR SI TENEMOS CONEXIÓN A INTERNET
        //SI CLICAMOS AL BOTÓN: Toast -> SÍ HAY CONEXIÓN A INTERNET ('True') / Toast -> NO HAY CONEXIÓN A INTERNET ('False')
        btn_conexion.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                //SÍ HAY CONEXIÓN A INTERNET ('True')
                if(isConnected()){

                    Toast.makeText(getApplicationContext(),"SÍ hay conexión a Internet",Toast.LENGTH_LONG).show();

                    HttpAsyncTask tarea = new HttpAsyncTask();

                    //ProgressDialog ('Loading' de Android, lo pararemos con la línea "dialog.dismiss()" después que cargue la lista)
                    //"dialog.dismiss()" está más abajo
                    dialog = ProgressDialog.show(MainActivity.this, "", "Loading. Please wait...", true);

                    //EJECUTAMOS UNA DIRECCIÓN 'URL' CON UNA BASE DE DATOS PREDEFINIDA
                    tarea.execute("https://jsonplaceholder.typicode.com/users");
                }
                //NO HAY CONEXIÓN A INTERNET ('False')
                else{
                    Toast.makeText(getApplicationContext(),"NO hay conexión a Internet",Toast.LENGTH_LONG).show();
                }

            }

        });


        //CLICAMOS AL BOTÓN PARA HACER UNA LLAMADA A LA LIBRERÍA 'RETROFIT'
        btn_retrofit.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                //INICIALIZAR LA CLASE CREADA (PARA LIBRERÍA 'RETROFIT')
                MyService service = RetrofitClientInstance.getRetrofitInstance().create(MyService.class);

                //HACER LA LLAMADA Y CAPTURAR EL RESULTADO O ERROR
                Call<List<Persona>> call = service.listPersonas();
                call.enqueue(new Callback<List<Persona>>() {
                    @Override
                    public void onResponse(Call<List<Persona>> call, Response<List<Persona>> response) {
                        //AQUÍ EL RESULTADO
                        personas.addAll(response.body());
                        adapter.notifyDataSetChanged();

                    }
                    @Override
                    public void onFailure(Call<List<Persona>> call, Throwable t) {
                        //MOSTRAR ERRORES
                    }
                });

            }

        });

    }


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


    //PRIVATE CLASS 'HttpAsyncTask'
    private class HttpAsyncTask extends AsyncTask<String, Void, String> {

        //la acción que queremos hacer, en este caso recoger datos del servidor.
        @Override
        protected String doInBackground(String... urls) {
            return GET(urls[0]);
        }

        //que hacemos cuando ya tenemos el resultado
        @Override
        protected void onPostExecute(String result) {

            //Listado en formato String:
            //Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();

            // convertir los datos a JSON para leerlos y crear Persona
            JSONArray jsonArray_result = null;




            //OBLIGA A CREAR UN "TRY & CATCH" CON LA LÍNEA: JSONArray jsonArray_result = new JSONArray(result);
            try {
                jsonArray_result = new JSONArray(result);
                jsonArray_result.length(); //Nº de users


                //CREAR UNA INSTANCIA DE LA LIBRERÍA 'GSON' (WEB: https://geekytheory.com/trabajando-con-json-en-android-gson)
                Gson gson = new Gson();


                for (int i = 0; i<jsonArray_result.length(); i++) {

                    JSONObject json_persona = jsonArray_result.getJSONObject(i);

                    long id = json_persona.getLong("id");
                    //String id_string = json_persona.getString("id");
                    //long id = Long.parseLong(id_string);
                    String name = json_persona.getString("name");
                    String email = json_persona.getString("email");
                    String username = json_persona.getString("username");

                    Persona persona = new Persona(id, name, email, username);

                    //AÑADIR AL "ArrayList<Persona> personas"
                    //personas.add(persona);

                    //LEER EL JSON CON LA LIBRERÍA GSON (WEB: https://geekytheory.com/trabajando-con-json-en-android-gson)
                    personas.add(gson.fromJson(json_persona.toString(), Persona.class));
                    //personas.add(gson.fromJson(jsonuser.toString(), Persona.class));

                }

                //ProgressDialog
                //AQUÍ PARAREMOS EL 'LOADING' DE ANDROID (UNA VEZ CARGADA LA LISTA DE PERSONAS DE LA WEB Y PASADAS AL "ArrayList<Persona> personas" O LA LIBRERÍA 'GSON')
                dialog.dismiss();

                adapter.notifyDataSetChanged();


            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }


    //FUNCIÓN 'GET' LE PASAMOS UNA 'URL' COMO PARÁMETRO
    public static String GET(String sUrl) {
        URL url;
        String result = "";
        HttpURLConnection urlConnection = null;
        try {
            url = new URL(sUrl);
            urlConnection = (HttpURLConnection) url.openConnection(); //abrir la conexión

            //Leer el resultado
            InputStream in = urlConnection.getInputStream();
            result = convertInputStreamToString(in);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        //Devuelve el resultado (lo recogeremos en el onPostExecute)
        return result;
    }


    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while ((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }


}


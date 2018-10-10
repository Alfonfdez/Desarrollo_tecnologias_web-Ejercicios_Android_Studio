package com.alfonso.restapiretrofit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Main2Activity extends AppCompatActivity {

    //DECLARACIONES
    TextView tv_id2;
    TextView tv_name2;
    TextView tv_email2;
    TextView tv_username2;
    TextView tv_street2;
    TextView tv_suite2;
    TextView tv_city2;
    TextView tv_zipcode2;
    TextView tv_lat2;
    TextView tv_lng2;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //FIND VIEWS BY ID
        tv_id2 = findViewById(R.id.tv_id2);
        tv_name2 = findViewById(R.id.tv_name2);
        tv_email2 = findViewById(R.id.tv_email2);
        tv_username2 = findViewById(R.id.tv_username2);
        tv_street2 = findViewById(R.id.tv_street2);
        tv_suite2 = findViewById(R.id.tv_suite2);
        tv_city2 = findViewById(R.id.tv_city2);
        tv_zipcode2 = findViewById(R.id.tv_zipcode2);
        tv_lat2 = findViewById(R.id.tv_lat2);
        tv_lng2 = findViewById(R.id.tv_lng2);


        //CÓDIGO PARA EL 'ACTIONBAR' CON LA POSIBILIDAD DE VOLVER HACIA ATRÁS
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Persona al detalle");

        //RECOGEMOS LA 'ID' GUARDADA EN LA PERSONA ESPECÍFICA ANTERIOR DE 'MainActivity'
        long valor_id = getIntent().getLongExtra("valor_id",0);
        final String id = String.valueOf(valor_id);

        //ProgressDialog ('Loading' de Android, lo pararemos con la línea "dialog.dismiss()" después que cargue la lista)
        //"dialog.dismiss()" está más abajo
        progressDialog = ProgressDialog.show(Main2Activity.this, "", "Loading. Please wait...", true);

        //INICIALIZAR LA CLASE CREADA (PARA LIBRERÍA 'RETROFIT')
        MyService service = RetrofitClientInstance.getRetrofitInstance().create(MyService.class);

        //HACER LA LLAMADA DE LA PERSONA EN CONCRETO(CON EL VALOR DEL ID) Y CAPTURAR EL RESULTADO O ERROR
        Call<Persona> call_unapersona = service.getPersona(valor_id);

        call_unapersona.enqueue(new Callback<Persona>() {
            @Override
            public void onResponse(Call<Persona> call, Response<Persona> response) {

                //ProgressDialog
                //AQUÍ PARAREMOS EL 'LOADING' DE ANDROID (UNA VEZ CARGADA LA LISTA DE PERSONAS DE LA WEB Y PASADAS AL "ArrayList<Persona> personas" O LA LIBRERÍA 'GSON')
                progressDialog.dismiss();

                if (response.body() != null) {
                    Persona persona = response.body();
                    //TODO:Mostrar la informació de la persona al formulari.

                    tv_id2.setText(id);

                    String name = persona.getName();
                    tv_name2.setText(name);

                    String email = persona.getEmail();
                    tv_email2.setText(email);

                    String username = persona.getUsername();
                    tv_username2.setText(username);

                    String street = persona.getAddress().getStreet();
                    tv_street2.setText(street);

                    String suite = persona.getAddress().getSuite();
                    tv_suite2.setText(suite);

                    String city = persona.getAddress().getCity();
                    tv_city2.setText(city);

                    String zipcode = persona.getAddress().getZipcode();
                    tv_zipcode2.setText(zipcode);

                    float lat = persona.getAddress().getGeo().getLat();
                    String lat_string = String.valueOf(lat);
                    tv_lat2.setText(lat_string);

                    float lng = persona.getAddress().getGeo().getLng();
                    String lng_string = String.valueOf(lng);
                    tv_lng2.setText(lng_string);

                }
            }

            @Override
            public void onFailure(Call<Persona> call, Throwable t) {

                //ProgressDialog
                //AQUÍ PARAREMOS EL 'LOADING' DE ANDROID (UNA VEZ CARGADA LA LISTA DE PERSONAS DE LA WEB Y PASADAS AL "ArrayList<Persona> personas" O LA LIBRERÍA 'GSON')
                progressDialog.dismiss();

                Toast.makeText(Main2Activity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });


        //CLIC EN 'LAT' PARA MOSTRAR EN EL GOOGLE MAPS LAS COORDENADAS
        tv_lat2.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                String lat = tv_lat2.getText().toString();
                String lng = tv_lng2.getText().toString();

                String uri = String.format("geo:"+lat+","+lng);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                startActivity(intent);

            }
        });



    }


    //CÓDIGO PARA EL 'ACTIONBAR' CON LA POSIBILIDAD DE VOLVER HACIA ATRÁS.
    //EL 'finish()' cerrará la página actual
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

}

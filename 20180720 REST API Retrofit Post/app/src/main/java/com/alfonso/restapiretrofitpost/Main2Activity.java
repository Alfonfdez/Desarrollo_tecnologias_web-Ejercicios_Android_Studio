package com.alfonso.restapiretrofitpost;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.lang.Long.parseLong;

public class Main2Activity extends AppCompatActivity {

    //DECLARACIONES
    TextView tv_id2;
    TextView tv_user_id2;
    TextView tv_title2;
    TextView tv_body2;
    TextView tv_name2;
    TextView tv_email2;
    TextView tv_street2;

    ProgressDialog progressDialog;
    ProgressDialog progressDialog2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //FIND VIEWS BY ID
        tv_id2 = findViewById(R.id.tv_id2);
        tv_user_id2 = findViewById(R.id.tv_user_id2);
        tv_title2 = findViewById(R.id.tv_title2);
        tv_body2 = findViewById(R.id.tv_body2);
        tv_name2 = findViewById(R.id.tv_name2);
        tv_email2 = findViewById(R.id.tv_email2);
        tv_street2 = findViewById(R.id.tv_street2);


        //CÓDIGO PARA EL 'ACTIONBAR' CON LA POSIBILIDAD DE VOLVER HACIA ATRÁS
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Post al detalle");

        //RECOGEMOS LA 'ID' DEL 'POST' ESPECÍFICO ESCOGIDO EN LA ANTERIOR DE 'MainActivity'
        long valor_id_post = getIntent().getLongExtra("valor_id_post",0);
        final String id_post = String.valueOf(valor_id_post);

        //RECOGEMOS LA 'ID' DE LA 'PERSONA' ESPECÍFICA ESCOGIDA EN LA ANTERIOR DE 'MainActivity'
        long valor_id_persona = getIntent().getLongExtra("valor_id_persona",0);
        final String id_persona = String.valueOf(valor_id_persona);


        //1º RECOGEREMOS LOS VALORES DEL 'POST' (ID, USERID, TITLE, BODY)

        //ProgressDialog ('Loading' de Android, lo pararemos con la línea "dialog.dismiss()" después que cargue la lista)
        //"dialog.dismiss()" está más abajo
        progressDialog = ProgressDialog.show(Main2Activity.this, "", "Loading. Please wait...", true);

        //INICIALIZAR LA CLASE CREADA (PARA LIBRERÍA 'RETROFIT')
        MyService service = RetrofitClientInstance.getRetrofitInstance().create(MyService.class);

        //HACER LA LLAMADA DEL 'POST' EN CONCRETO(CON EL VALOR DEL ID) Y CAPTURAR EL RESULTADO O ERROR
        Call<Post> call_un_post = service.getPost(valor_id_post);

        call_un_post.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {

                //ProgressDialog
                //AQUÍ PARAREMOS EL 'LOADING' DE ANDROID (UNA VEZ CARGADA LA LISTA DE PERSONAS DE LA WEB Y PASADAS AL "ArrayList<Persona> personas" O LA LIBRERÍA 'GSON')
                progressDialog.dismiss();

                if (response.body() != null) {
                    Post post = response.body();
                    //TODO:Mostrar la informació de la persona al formulari.

                    tv_id2.setText(id_post);

                    tv_user_id2.setText(id_persona);

                    String title = post.getTitle();
                    tv_title2.setText(title);

                    String body = post.getBody();
                    tv_body2.setText(body);

                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

                //ProgressDialog
                //AQUÍ PARAREMOS EL 'LOADING' DE ANDROID (UNA VEZ CARGADA LA LISTA DE PERSONAS DE LA WEB Y PASADAS AL "ArrayList<Persona> personas" O LA LIBRERÍA 'GSON')
                progressDialog.dismiss();

                Toast.makeText(Main2Activity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });



        //2º RECOGEREMOS LOS VALORES DE LA 'PERSONA' (NAME, EMAIL, ADDRESS(STREET))

        //ProgressDialog ('Loading' de Android, lo pararemos con la línea "dialog.dismiss()" después que cargue la lista)
        //"dialog.dismiss()" está más abajo
        progressDialog2 = ProgressDialog.show(Main2Activity.this, "", "Loading. Please wait...", true);

        //HACER LA LLAMADA DE LA PERSONA EN CONCRETO(CON EL VALOR DEL ID) Y CAPTURAR EL RESULTADO O ERROR
        Call<Persona> call_una_persona = service.getPersona(valor_id_persona);

        call_una_persona.enqueue(new Callback<Persona>() {
            @Override
            public void onResponse(Call<Persona> call, Response<Persona> response) {

                //ProgressDialog
                //AQUÍ PARAREMOS EL 'LOADING' DE ANDROID (UNA VEZ CARGADA LA LISTA DE PERSONAS DE LA WEB Y PASADAS AL "ArrayList<Persona> personas" O LA LIBRERÍA 'GSON')
                progressDialog2.dismiss();

                if (response.body() != null) {
                    Persona persona = response.body();
                    //TODO:Mostrar la informació de la persona al formulari.

                    String name = persona.getName();
                    tv_name2.setText(name);

                    String email = persona.getEmail();
                    tv_email2.setText(email);

                    String street = persona.getAddress().getStreet();
                    tv_street2.setText(street);

                }
            }

            @Override
            public void onFailure(Call<Persona> call, Throwable t) {

                //ProgressDialog
                //AQUÍ PARAREMOS EL 'LOADING' DE ANDROID (UNA VEZ CARGADA LA LISTA DE PERSONAS DE LA WEB Y PASADAS AL "ArrayList<Persona> personas" O LA LIBRERÍA 'GSON')
                progressDialog2.dismiss();

                Toast.makeText(Main2Activity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });


    }

    /*/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\*/

    //CÓDIGO PARA EL 'ACTIONBAR' CON LA POSIBILIDAD DE VOLVER HACIA ATRÁS.
    //EL 'finish()' cerrará la página actual
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }



}

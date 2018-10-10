package com.example.administrador.intents1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.FileObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btn_enviar, btn_mail, btn_share, btn_web, btn_maps, btn_prefs, btn_times;
    EditText et_text;
    SharedPreferences prefs; //declaro objecte SharedPreferences

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_enviar = findViewById(R.id.btnEnviar);
        btn_share = findViewById(R.id.btnShare);
        btn_mail = findViewById(R.id.btnMail);
        btn_web = findViewById(R.id.btnWeb);
        btn_maps = findViewById(R.id.btnMaps);
        btn_prefs = findViewById(R.id.btnPrefs);
        btn_times = findViewById(R.id.btn_times);

        et_text = findViewById(R.id.et_text);

        //Inicialitzar variable SharedPreferences
        prefs = getSharedPreferences(getString(R.string.sharedprefs), Context.MODE_PRIVATE);

        //Mostrar el valor guardat al SharedPreferences
        //Sino, mostrar ""
        et_text.setText(prefs.getString("oldText", ""));

        btn_enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ResultadoActivity.class);
                intent.putExtra("resultado", et_text.getText().toString());
                startActivity(intent);

            }
        });

        btn_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "Text a compartir!");
                startActivity(Intent.createChooser(intent, "Selecciona app"));

            }
        });

        btn_mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "Texto a compartir");
                intent.putExtra(Intent.EXTRA_EMAIL, "hello@email.com");
                intent.putExtra(Intent.EXTRA_SUBJECT, "Hello");
                startActivity(intent);

            }
        });

        btn_web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.google.com"));
                startActivity(intent);

            }
        });
        btn_maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri gmmIntentUri = Uri.parse("google.streetview:cbll=46.414382,10.013988");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });

        btn_prefs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Guardar al SharedPreferences el valor del EditText
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("oldText", et_text.getText().toString());
                //Commit necesari per a que es guardi
                editor.commit();

            }
        });
        btn_times.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), prefs.getInt("times", 0) +  " times", Toast.LENGTH_LONG).show();
            }
        });

    }


}

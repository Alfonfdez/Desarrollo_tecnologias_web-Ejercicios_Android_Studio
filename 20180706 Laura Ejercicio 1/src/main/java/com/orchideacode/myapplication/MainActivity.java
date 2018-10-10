package com.orchideacode.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView tvNombre; //declaro un TextView, no hace nada aún
    Button btnProves; //declaro un Button.
    EditText etNombre; //declaro un EditText.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvNombre = findViewById(R.id.tvnombre); //asignar el del layout
        etNombre = findViewById(R.id.etNombre);
        btnProves = findViewById(R.id.btnProves); //asignar el botón del layout

        //Explicación de algunos atributos del layout:
        /*
        * Si estamos utilizando un RelativeLayout, atributos para los elementos:
        *
        *  android:layout_centerInParent="true" -> Centrar en el centro del contenedor
        *  android:layout_centerVertical="true" -> Centrar verticalmente (arriba-abajo)
        *  android:layout_centerHorizontal="true" -> Centrar horizontalmente (izquierda-derecha)
        *  android:layout_above="@id/tvhello" -> El elemento esta por encima de otro con id "tvhello"
        *  android:layout_below="@id/tvhello" -> El elemento esta por debajo de otro con id "tvhello"
        *  android:layout_toRightOf="@id/tvhello" -> El elemento esta a la derecha de otro con id "tvhello"
        *  android:layout_toLeftOf="@id/tvhello" -> El elemento esta a la izquierda de otro con id "tvhello"
        *
        *  android:layout_alignParentRight="true" -> Elemento alineado a la derecha del contenedor (lo mismo para Left, Top, Bottom)
        * */

        //TextView (https://developer.android.com/reference/android/widget/TextView):


        tvNombre.setText("texto modificado en el codigo"); //Modificar el texto

        String texto = tvNombre.getText().toString(); //Coger el texto del TextView

        tvNombre.setTextColor(getResources().getColor(R.color.colorAccent)); //modificar textColor (se pueden modificar más atributos)

        //Button (https://developer.android.com/reference/android/widget/Button):



        //Programar que hacemos en el click del Botón
        btnProves.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String texto_edittext = etNombre.getText().toString(); //Coger el texto de dentro del EditText.
                Toast.makeText(getApplicationContext(), "Click botón"+texto_edittext, Toast.LENGTH_SHORT).show();
                tvNombre.setText(texto_edittext); //Mostrar texto del EditText en el TextView de la pantalla.
            }
        });
        //Programar que hacemos en el long click del botón:
        btnProves.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(getApplicationContext(), "Looooong click botón", Toast.LENGTH_SHORT).show();

                return true; //false ejecuta LongClick y Click, true-> solo ejecuta LongClick
            }
        });

        //EditText:






    }
}

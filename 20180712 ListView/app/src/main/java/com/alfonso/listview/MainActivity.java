package com.alfonso.listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //DECLARACIÓN
    ListView listView;
    ListView listView2;
    ArrayList<String> nombres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //FIND VIEW BY ID
        listView = findViewById(R.id.list);
        listView2 = findViewById(R.id.list2);


        //CREAR UNA "ARRAYLIST"
        String valores[] = new String[]{
                "Platano", "Manzana", "Melocotón", "Fresa"
        };

        //CREAR UN "ARRAY_ADAPTER": Un objecte Adapter actúa com a pont entre un ListView i les dades d’una Vista (View). El
        //adaptador permet l’acces als elements de dades, també és responsable de crear una vista per a
        //cada element de la colecció de dades.
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, valores );

        listView.setAdapter(adapter);

        //CUANDO CLICAMOS LEVEMENTE "SHORT CLICK"
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //COGE LA POSICIÓN DE LA LISTA
                int itemPosition = i;
                //COGE EL VALOR DEL TEXTO EN DICHA POSICIÓN
                String itemValue = (String) listView.getItemAtPosition(i);
                //LO MUESTRA EN UN 'TOAST'
                Toast.makeText(getApplicationContext(), "Posición:"+itemPosition+" Valor: " +itemValue , Toast.LENGTH_LONG).show();
            }

        });

        //CUANDO CLICAMOS MÁS LARGO "LONG CLICK"
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                    Toast.makeText(getApplicationContext(), "long click",Toast.LENGTH_LONG).show();

                    return false; //SI QUISIERAMOS QUE NO VOLVIERA A UN CLIC CORTO, PONDRIAMOS "True"

            }

        });


        /*/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\*/

        //HAREMOS LOS MISMO QUE ANTES PERO CON UN "ARRAYLIST"

        nombres = new ArrayList<String>();

        nombres.add("Antonio");
        nombres.add("Jose");
        nombres.add("Manuel");
        nombres.add("David");

        //MODIFICAR LISTADO EN "ADAPTER"
        final ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, nombres);

        listView2.setAdapter(adapter2);

        //CON "LONG CLICK" BORRAR ITEMS DE LA LISTA
        listView2.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                int itemPosition = i;

                nombres.remove(itemPosition);

                adapter2.notifyDataSetChanged();

                return false;

            }

        });

    }
}

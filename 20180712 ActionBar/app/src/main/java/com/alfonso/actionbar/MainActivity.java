package com.alfonso.actionbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //ASIGNACIONES
    TextView count;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //FIND VIEWS BY ID
        count = findViewById(R.id.tv);


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
    public boolean onOptionsItemSelected(MenuItem item) { switch(item.getItemId()) {
        case R.id.add:

            //add the function to perform here
            count.setText("Add is clicked");
            return(true);

        case R.id.reset:

            //add the function to perform here
            count.setText("Nothing is selected");
            return(true);

        case R.id.about:

            //add the function to perform here
            Toast.makeText(this, R.string.about_toast, Toast.LENGTH_LONG).show();
            return(true);

        case R.id.exit:

            //add the function to perform here
            finish();
            return(true);
    }
        return(super.onOptionsItemSelected(item));
    }

}

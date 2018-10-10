package com.orchideacode.layouts1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btn_ok; //declaració botó
    Button btn_cancel;
    TextView txtText;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_ok = findViewById(R.id.btnOk); //instanciar amb el id del botó del layout
        btn_cancel = findViewById(R.id.btnCancel);
        editText = findViewById(R.id.editText);
        txtText = findViewById(R.id.txtExample);

        //función onClick del botó.
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtText.setText(getString(R.string.editext_name).replace("NOMBRE", editText.getText().toString()));
                editText.setText("");
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.setText("");
                txtText.setText("");
            }
        });

    }
}

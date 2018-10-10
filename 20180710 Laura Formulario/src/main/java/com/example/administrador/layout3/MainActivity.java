package com.example.administrador.layout3;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText et_name, et_mail, et_phone, et_password, et_date;
    Button btn_ok, btn_cancel;

    int pDay, pMonth, pYear = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        et_name = findViewById(R.id.et_nom);
        et_mail = findViewById(R.id.et_mail);
        et_phone = findViewById(R.id.et_phone);
        et_password = findViewById(R.id.et_password);
        et_date = findViewById(R.id.et_data);

        btn_ok = findViewById(R.id.btnOk);
        btn_cancel = findViewById(R.id.btnCancel);

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();

            }
        });


        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ("".equals(et_name.getText().toString()) || "".equals(et_mail.getText().toString()) || "".equals(et_phone.getText().toString()) || "".equals(et_password.getText().toString())) {
                    Toast.makeText(getApplicationContext(), getString(R.string.message_empty), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), getString(R.string.message_saved), Toast.LENGTH_LONG).show();
                }
            }
        });

        et_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDatePickerDialog();
            }
        });
    }

    public void openDatePickerDialog() {

        DatePickerDialog dateDialog =
                new DatePickerDialog(this,
                        pDateSetListener,
                        pYear, pMonth, pDay);

        dateDialog.getDatePicker().setMinDate(System.currentTimeMillis()); //Fecha a partir de hoy, no se puede escoger fechas pasadas

        dateDialog.show();

    }

    private DatePickerDialog.OnDateSetListener pDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    pYear = year;
                    pMonth = monthOfYear;
                    pDay = dayOfMonth;

                    String fromDay = Integer.toString(pDay);
                    String fromMonth = Integer.toString(pMonth + 1);

                    if (fromDay.length() == 1) fromDay = "0" + fromDay;
                    if (fromMonth.length() == 1) fromMonth = "0" + fromMonth;

                    et_date.setText(fromDay + "/" + fromMonth + "/" + pYear);
                }
            };

    public void openDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);

        // set title
        alertDialogBuilder.setTitle(getString(R.string.app_name));

        // set dialog message
        alertDialogBuilder
                .setMessage(getString(R.string.dialog_text))
                .setCancelable(false)
                .setPositiveButton(getString(R.string.btn_yes), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //Eliminem el contingut del edittext:
                        deleteEditText();
                    }
                })
                .setNegativeButton(getString(R.string.btn_no), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // No fem res, tanquem el Alert.
                        dialog.cancel();
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }


    public void deleteEditText() {
        et_name.setText("");
        et_mail.setText("");
        et_phone.setText("");
        et_password.setText("");
    }
}

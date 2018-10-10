package com.alfonso.restapiretrofit;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class PersonaAdapter extends ArrayAdapter<Persona> {

    ArrayList<Persona> objects; //Corresponde al array de cosas del ListadoActivity.
    Context context; //Referencia al MainActivity
    int resource; //identificador del layour fila


    //CONSTRUCTOR
    public PersonaAdapter(Context context, int resource, ArrayList<Persona> objects) {
        super(context, resource, objects);
        this.objects = objects;
        this.context = context;
        this.resource = resource;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row;
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        row = inflater.inflate(resource, parent, false);

        //MOSTRARLO AL ROW
        //ID
        TextView textView_id = (TextView)row.findViewById(R.id.tv_id);
        textView_id.setText(String.valueOf(objects.get(position).getId()));

        //NAME
        TextView textView_name = (TextView)row.findViewById(R.id.tv_name);
        textView_name.setText(objects.get(position).getName());

       /* //EMAIL
        TextView textView_email = (TextView)row.findViewById(R.id.tv_email);
        textView_email.setText(objects.get(position).getEmail());*/

        //USERNAME
        TextView textView_username = (TextView)row.findViewById(R.id.tv_username);
        textView_username.setText(objects.get(position).getUsername());

        return row;


    }

}

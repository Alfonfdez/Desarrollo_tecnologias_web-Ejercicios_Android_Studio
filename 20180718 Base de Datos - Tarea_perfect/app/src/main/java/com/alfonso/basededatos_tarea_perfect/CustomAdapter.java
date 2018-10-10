package com.alfonso.basededatos_tarea_perfect;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<Tarea> {

    int layoutResourceId;
    Context context;
    ArrayList<Tarea> data;

    //CONSTRUCTOR
    public CustomAdapter(Context context, int layoutResourceId, ArrayList<Tarea> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    //MÉTODOS
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row;
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        row = inflater.inflate( layoutResourceId, parent, false);

        //PONER LAS UNIDADES EN EL PRIMER 'TEXTVIEW'
        TextView tv_duracion = (TextView)row.findViewById(R.id.tv_duracion);
        tv_duracion.setText( String.valueOf(data.get(position).getDuracion()));

        //PONER EL NOMBRE EN EL SEGUNDO 'TEXTVIEW'
        TextView tv_nombre = (TextView)row.findViewById(R.id.tv_nombre);
        tv_nombre.setText( data.get(position).getNombre());

        if(data.get(position).isHecha()){ //"data.get(position)" ES NUESTRA ARRAYLIST Y SU POSICIÓN - "isHecha()" es si está marcada la Checkbox
            tv_nombre.setTextColor(context.getResources().getColor(R.color.red));
        }

        //Altres camps
        return row;

    }



}


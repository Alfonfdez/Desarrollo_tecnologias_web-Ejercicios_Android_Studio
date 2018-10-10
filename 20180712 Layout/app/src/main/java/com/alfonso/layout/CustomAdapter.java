package com.alfonso.layout;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<String> {

    //DECLARACIONES
    int layoutResourceId;
    Context context;
    ArrayList<String> data;


    //CONSTRUCTOR
    public CustomAdapter(Context context, int layoutResourceId, ArrayList<String> data) {
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
        TextView tv_nombre = (TextView)row.findViewById(R.id.texto);
        tv_nombre.setText( data.get(position));

        //data.get(position) -> ES NUESTRA ARRAYLIST Y SU POSICIÓN (i)
        if(data.get(position).startsWith("P")){
            tv_nombre.setTextColor(context.getResources().getColor(R.color.red));
        } else if(data.get(position).startsWith("p")){
            tv_nombre.setTextColor(context.getResources().getColor(R.color.red));
        } else if(data.get(position).startsWith("C")){
            tv_nombre.setTextColor(context.getResources().getColor(R.color.red));
        } else if(data.get(position).startsWith("c")){
            tv_nombre.setTextColor(context.getResources().getColor(R.color.red));
        }

        //Altres camps
        return row;

    }



}






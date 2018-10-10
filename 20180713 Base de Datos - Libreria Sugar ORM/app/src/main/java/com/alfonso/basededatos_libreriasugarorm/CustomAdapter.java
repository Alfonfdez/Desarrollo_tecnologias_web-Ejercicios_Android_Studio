package com.alfonso.basededatos_libreriasugarorm;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<Producto> {

    int layoutResourceId;
    Context context;
    ArrayList<Producto> data;
    //ArrayList<String> data; //ANTIGUA LÍNEA


    //CONSTRUCTOR
    public CustomAdapter(Context context, int layoutResourceId, ArrayList<Producto> data) {
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
        TextView tv_unidades = (TextView)row.findViewById(R.id.texto_unidades);
        tv_unidades.setText( String.valueOf(data.get(position).getUnidades())); //NUEVA LÍNEA

        //PONER EL NOMBRE EN EL SEGUNDO 'TEXTVIEW'
        TextView tv_nombre = (TextView)row.findViewById(R.id.texto_nombre);
        tv_nombre.setText( data.get(position).getNombre()); //NUEVA LÍNEA


        /*//PONER LA IMAGEN EN EL 'IMAGEVIEW'
        ImageView imagen = (ImageView)row.findViewById(R.id.iv1);
        //---DECLARACIÓN PICASSO: SUBIR IMÁGENES DE INTERNET EN NUESTRA APP---
        Picasso.get().load(data.get(position).getImagen()) //COJO EL STRING CON LA URL (DE NUESTRA IMAGEN ESCOGIDA DE INTERNET)
                .placeholder(R.drawable.perro)
                .error(R.drawable.perro)
                .into(imagen);*/

        //IMAGEN PARA UTILIZAR EN EL EJEMPLO: http://i.imgur.com/DvpvklR.png


        //tv_nombre.setText( data.get(position)); //ANTIGUA LÍNEA

        if(data.get(position).isComprado()){ //"data.get(position)" ES NUESTRA ARRAYLIST Y SU POSICIÓN - "isComprado()" es si está marcada la Checkbox
            tv_nombre.setTextColor(context.getResources().getColor(R.color.red));
        }

        //Altres camps
        return row;

    }



}
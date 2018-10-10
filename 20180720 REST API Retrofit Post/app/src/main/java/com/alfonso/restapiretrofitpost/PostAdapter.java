package com.alfonso.restapiretrofitpost;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class PostAdapter extends ArrayAdapter<Post> {

    ArrayList<Post> objects; //Corresponde al array de cosas del ListadoActivity.
    Context context; //Referencia al MainActivity
    int resource; //identificador del layour fila


    //CONSTRUCTOR
    public PostAdapter(Context context, int resource, ArrayList<Post> objects) {
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

        //USERID
        TextView textView_name = (TextView)row.findViewById(R.id.tv_user_id);
        textView_name.setText(String.valueOf(objects.get(position).getUserId()));

        //TITLE
        TextView textView_title = (TextView)row.findViewById(R.id.tv_title);
        textView_title.setText(objects.get(position).getTitle());

        return row;


    }

}


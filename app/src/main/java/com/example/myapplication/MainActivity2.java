package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    static ArrayList<Partida> lista=new ArrayList<Partida>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        final Button volver=findViewById(R.id.button2);
        String name ="pepe";
        String time = "50";
        String tries = "patata";

        volver.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                openAcivity1();

            }
        });

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {

                crearVentana();

            } else {

                tries=getIntent().getExtras().getString("tries");
                time=getIntent().getExtras().getString("time");
                name=getIntent().getExtras().getString("name");
                Partida p = new Partida(name,tries, time);
                lista.add(p);
                crearVentana();

            }
        }
    }
    public void crearVentana(){

        TableRow.LayoutParams  params1=new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT,1.0f);
        TableRow.LayoutParams params2=new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.WRAP_CONTENT);
        TableLayout tbl=(TableLayout) findViewById(R.id.tabla);
        for(Partida p:lista)
        {
            //Creating new tablerows and textviews
            TableRow row=new TableRow(this);
            TextView txt1=new TextView(this);
            TextView txt2=new TextView(this);
            TextView txt3=new TextView(this);
            //setting the text
            txt1.setText(p.getTries());
            txt2.setText(p.getTime());
            txt3.setText(p.getNombre());
            txt1.setLayoutParams(params1);
            txt2.setLayoutParams(params1);
            txt3.setLayoutParams(params1);
            //the textviews have to be added to the row created
            row.addView(txt1);
            row.addView(txt2);
            row.addView(txt3);
            row.setLayoutParams(params2);
            tbl.addView(row);
        }

    }
    public void openAcivity1(){
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}

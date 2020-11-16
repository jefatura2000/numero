package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    AdapterDatos adapter;
    static ArrayList<Partida> listPartidas=new ArrayList<Partida>();
    RecyclerView recycler;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        recycler=findViewById(R.id.ReciclerId);
        recycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));


        final Button volver=findViewById(R.id.button2);
        String name = new String();
        String time = new String();
        String tries = new String();
        Uri imageUri=null;

        volver.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                openAcivity1();

            }
        });

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras != null) {

                tries=getIntent().getExtras().getString("tries");
                time=getIntent().getExtras().getString("time");
                name=getIntent().getExtras().getString("name");
                imageUri=latestPhoto();
                Partida p = new Partida(name,tries, time,imageUri);
                listPartidas.add(p);

            }
        }

        AdapterDatos adapter=new AdapterDatos(listPartidas);
        recycler.setAdapter(adapter);

    }

    private Uri latestPhoto(){

        Uri uri=null;
        File f=new File("/sdcard/Android/data/com.example.myapplication/files/Pictures");
        if(f != null){
            uri = FileProvider.getUriForFile(this,
                    "com.example.myapplication.provider",
                    f.listFiles()[f.listFiles().length-1]);

        }
        return uri;

    }

    public void openAcivity1(){
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }


}

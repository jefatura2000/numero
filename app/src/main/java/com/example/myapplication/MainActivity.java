package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int tries =0;
    int num=(int)(Math.random()*100);
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button b=findViewById(R.id.button);


        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                EditText text=findViewById(R.id.editTextNumber);
                int opcion=Integer.parseInt(text.getText().toString());
                if(opcion==num){
                    final Toast t1 = Toast.makeText(MainActivity.this, "Has acertado, te ha costado "+tries+" intentos", Toast.LENGTH_SHORT);
                    t1.show();
                    tries=0;
                    num=(int)(Math.random()*100);
                }
                if(opcion>num){
                    final Toast t2 = Toast.makeText(MainActivity.this, "Te has pasado", Toast.LENGTH_SHORT);
                    t2.show();
                    tries=tries+1;
                }
                if(opcion<num){
                    final Toast t3 = Toast.makeText(MainActivity.this, "Te has quedado corto", Toast.LENGTH_SHORT);
                    t3.show();
                    tries=tries+1;
                }

            }
        });
    }
}
package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;



public class MainActivity extends AppCompatActivity {
    int segundos=0;
    int tries =0;
    int num=(int)(Math.random()*9+1);
    Timer timer = new Timer();
    String value=new String();
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button b=findViewById(R.id.button);
        final Button b2=findViewById(R.id.ranking);



        Contar();

        b2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });

        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                EditText text=findViewById(R.id.editTextNumber);
                int opcion=Integer.parseInt(text.getText().toString());
                if(opcion==num){
                    Detener();
                    final Toast t1 = Toast.makeText(MainActivity.this, "Has acertado, te ha costado "+tries+" intentos y has tardado "+segundos+" segundos", Toast.LENGTH_SHORT);
                    t1.show();

                    Dialogo();

                    
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
    class Contador extends TimerTask {
        public void run() {
            segundos++;
            System.out.println("segundo: " + segundos);
        }
    }
    public void Contar()
    {
        segundos=0;
        timer.schedule(new Contador(), 0, 1000);
    }
    public void Detener() {
        timer.cancel();
    }

    public void openActivity2(){
        Intent intent=new Intent(this,MainActivity2.class);
        startActivity(intent);
    }
    public void Dialogo(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle("Victoria!");
        alert.setMessage("Introduce tu nombre para el ranking");


        // Set an EditText view to get user input
        final EditText input = new EditText(this);
        alert.setView(input);

        final Intent intent=new Intent(this,MainActivity2.class);
        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                value = input.getText().toString();

                intent.putExtra("tries",Integer.toString(tries));
                intent.putExtra("time",Integer.toString(segundos));
                Dialogo();
                intent.putExtra("name",value);
                startActivity(intent);
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Canceled.
            }
        });

        alert.show();
    }


}
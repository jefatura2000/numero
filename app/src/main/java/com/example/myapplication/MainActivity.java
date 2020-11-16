package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;


import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


public class MainActivity extends AppCompatActivity {
    int segundos=0;
    int tries =1;
    int num=(int)(Math.random()*9+1);
    Timer timer = new Timer();
    String value=new String();
    private static int REQUEST_TAKE_PHOTO=1;

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
                int opcion=0;
                if(text.getText().toString()!=null){
                    opcion=Integer.parseInt(text.getText().toString());
                }else{
                    final Toast t4 = Toast.makeText(MainActivity.this, "No has introducido un numero", Toast.LENGTH_SHORT);
                    t4.show();
                    opcion=-1;
                }

                if(opcion==num){
                    Detener();
                    final Toast t1 = Toast.makeText(MainActivity.this, "Has acertado, te ha costado "+tries+" intentos y has tardado "+segundos+" segundos", Toast.LENGTH_SHORT);
                    t1.show();

                    Dialogo();

                    
                }
                else if(opcion>num){
                    final Toast t2 = Toast.makeText(MainActivity.this, "Te has pasado", Toast.LENGTH_SHORT);
                    t2.show();
                    tries=tries+1;
                }
                else if(opcion<num){
                    final Toast t3 = Toast.makeText(MainActivity.this, "Te has quedado corto", Toast.LENGTH_SHORT);
                    t3.show();
                    tries=tries+1;
                }
                else{

                    final Toast t4 = Toast.makeText(MainActivity.this, "No has introducido un numero", Toast.LENGTH_SHORT);
                    t4.show();
                }

            }
        });
    }
    class Contador extends TimerTask {
        public void run() {
            segundos++;
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
    Intent intent;
    public void Dialogo(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle("Victoria!");
        alert.setMessage("Introduce tu nombre para el ranking");


        // Set an EditText view to get user input
        final EditText input = new EditText(this);
        alert.setView(input);

        intent=new Intent(this,MainActivity2.class);
        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int whichButton) {

            value = input.getText().toString();
            intent.putExtra("tries",Integer.toString(tries));
            intent.putExtra("time",Integer.toString(segundos));
            intent.putExtra("name",value);
            //escribirXML();
            //tomar foto
            Intent takePictureIntent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            File photoFile=null;
            try {
                photoFile= createImageFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Uri photoURI = FileProvider.getUriForFile(getApplicationContext(),
                    BuildConfig.APPLICATION_ID+".provider",
                    photoFile);
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
            startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);




        }
    });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int whichButton) {
            // Canceled.
        }});


        alert.show();


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        startActivity(intent);

        //Bundle extras = data.getExtras();
        //Bitmap imageBitmap = (Bitmap) extras.get("data");
        //imageView.setImageBitmap(imageBitmap);


    }

    String currentPhotoPath;
    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    public void escribirXML(){

        //tries segundos value
        File path=getFilesDir();
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.newDocument();
            // definimos el elemento raíz del documento
            Element eRaiz = doc.createElement("Partidas");


            // definimos el nodo que contendrá los elementos
            Element ePartida = doc.createElement("Partida");
            eRaiz.appendChild(ePartida);

            // definimos cada uno de los elementos y le asignamos un valor
            Element eNombre = doc.createElement("nombre");
            eNombre.appendChild(doc.createTextNode(value));
            ePartida.appendChild(eNombre);

            Element eIntentos = doc.createElement("intentos");
            eIntentos.appendChild(doc.createTextNode(Integer.toString(tries)));
            ePartida.appendChild(eIntentos);

            Element eTiempo = doc.createElement("tiempo");
            eTiempo.appendChild(doc.createTextNode(Integer.toString(segundos)));
            ePartida.appendChild(eTiempo);

            // clases necesarias finalizar la creación del archivo XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(path+"datos.xml"));

            transformer.transform(source, result);

        } catch(Exception e) {
            e.printStackTrace();
        }

    }

}
package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterDatos extends RecyclerView.Adapter<AdapterDatos.ViewHolderDatos> {

    ArrayList<Partida> listPartidas;

    public AdapterDatos(ArrayList<Partida> listPartidas) {
        this.listPartidas = listPartidas;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,null,false);
        return new ViewHolderDatos(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {

        holder.asignarDatos(listPartidas.get(position));

    }

    @Override
    public int getItemCount() {
        return listPartidas.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        TextView nombre;
        TextView intentos;
        TextView tiempo;
        ImageView image;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            nombre=itemView.findViewById(R.id.idNombre);
            intentos=itemView.findViewById(R.id.idIntentos);
            tiempo=itemView.findViewById(R.id.idTiempo);
            image=itemView.findViewById(R.id.imageView);
        }

        public void asignarDatos(Partida partida) {
            nombre.setText(partida.getNombre());
            intentos.setText(partida.getTries());
            tiempo.setText(partida.getTime());
            image.setImageURI(partida.getImageUri());
        }
    }
}

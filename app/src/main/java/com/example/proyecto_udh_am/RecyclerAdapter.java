package com.example.proyecto_udh_am;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    int i = 0;
    List<Habito> listaHabitos;

    public RecyclerAdapter(List<Habito> listaHabitos) {
        this.listaHabitos = listaHabitos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.i("RecyclerView", "Elemento:" + i++);

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.lista_items, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView1.setText(listaHabitos.get(position).getNombre());
        holder.textView2.setText("Cada " + listaHabitos.get(position).getFrecuencia());

        int posterId = listaHabitos.get(position).getPoster();

        Drawable poster = holder.imageView.getResources().getDrawable(posterId);

        holder.imageView.setImageDrawable(poster);
    }

    @Override
    public int getItemCount() {
        return listaHabitos.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imageView;
        TextView textView1;
        TextView textView2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView1 = itemView.findViewById(R.id.textView1);
            textView2 = itemView.findViewById(R.id.textView2);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(v.getContext(),
                    listaHabitos.get(getAdapterPosition()).getNombre(),
                    Toast.LENGTH_LONG).show();
            Intent intent = new Intent(v.getContext(), MostrarHabito.class);
            v.getContext().startActivity(intent);
        }
    }
}

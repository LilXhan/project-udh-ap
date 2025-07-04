package com.example.proyecto_udh_am;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Habito> listaHabitos;
    RecyclerAdapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        listaHabitos = new ArrayList<Habito>();
        listaHabitos.add(new Habito("Correr", "1 dia", "07-05-2025", "07-08-2025", "7 am", R.drawable.correr));
        listaHabitos.add(new Habito("Caminar", "2 dias", "07-05-2025", "07-08-2025", "8 am", R.drawable.caminar));
        listaHabitos.add(new Habito("Nadar", "7 dias", "07-05-2025", "07-08-2025", "10 am", R.drawable.nadar));
        listaHabitos.add(new Habito("Futbol", "5 dias", "07-05-2025", "07-08-2025", "8 pm", R.drawable.futbol));

        recyclerView = findViewById(R.id.miVista);
        recyclerAdapter = new RecyclerAdapter(listaHabitos);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerAdapter);

        try {
            Intent receiverIntent = getIntent();
            String frecuencia = receiverIntent.getStringExtra("frecuencia");
            String fechaInicio = receiverIntent.getStringExtra("fechaInicio");
            String fechaFin = receiverIntent.getStringExtra("fechaFin");
            String horario = receiverIntent.getStringExtra("horario");
            String nombre =  receiverIntent.getStringExtra("habito");

            int poster = getResources().getIdentifier(nombre, "drawable", getPackageName());
            listaHabitos.add(new Habito(nombre, frecuencia, fechaInicio, fechaFin, horario, poster));
        } catch (Exception e) {
            Toast.makeText(this, "Cargando...", Toast.LENGTH_SHORT).show();
        }

    }
    public void nuevoHabito(View v) {
        Intent intent = new Intent(v.getContext(), CrearHabito.class);
        v.getContext().startActivity(intent);
    }

}
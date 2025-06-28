package com.example.proyecto_udh_am;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MostrarHabito extends AppCompatActivity {

    TextView tvFrecuencia, tvFechaInicio, tvFechaFin, tvHorario, tvNombre;
    ImageView ivPoster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mostrar_habito);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent receiverIntent = getIntent();
        String frecuencia = receiverIntent.getStringExtra("frecuencia");
        String fechaInicio = receiverIntent.getStringExtra("fechaInicio");
        String fechaFin = receiverIntent.getStringExtra("fechaFin");
        String horario = receiverIntent.getStringExtra("horario");
        String nombre =  receiverIntent.getStringExtra("nombre");
        int posterId = receiverIntent.getIntExtra("poster", 0);


        tvFrecuencia = findViewById(R.id.retrieveFrecuencia);
        tvFechaInicio = findViewById(R.id.retrieveFechaInicio);
        tvFechaFin = findViewById(R.id.retrieveFechaFin);
        tvNombre = findViewById(R.id.retrieveNombre);
        ivPoster = findViewById(R.id.retrievePoster);
        tvHorario = findViewById(R.id.retrieveHorario);
        tvFrecuencia.setText(frecuencia);
        tvFechaInicio.setText(fechaInicio);
        tvFechaFin.setText(fechaFin);
        tvNombre.setText(nombre);
        tvHorario.setText(horario);
        Drawable poster = ivPoster.getResources().getDrawable(posterId);


        ivPoster.setImageDrawable(poster);

    }

    public void irInicio(View v) {
        Intent intent = new Intent(v.getContext(), MainActivity.class);
        v.getContext().startActivity(intent);
    }

}
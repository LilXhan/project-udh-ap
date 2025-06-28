package com.example.proyecto_udh_am;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CrearHabito extends AppCompatActivity {

    RadioButton rbHabito;
    int rgHabito;
    RadioGroup rgHabitos;
    EditText etFrecuencia, etFechaInicio, etFechaFin, etHorario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_crear_habito);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        rgHabitos = findViewById(R.id.rgHabitos);
        etFrecuencia = findViewById(R.id.etFrecuencia);
        etFechaInicio = findViewById(R.id.etFechaInicio);
        etFechaFin = findViewById(R.id.etFechaFin);
        etHorario = findViewById(R.id.etHorario);
    }

    public void crear(View v) {
        String frecuencia = etFrecuencia.getText().toString();
        String fechaInicio = etFechaInicio.getText().toString();
        String fechaFin = etFechaFin.getText().toString();
        String horario = etHorario.getText().toString();
        rgHabito = rgHabitos.getCheckedRadioButtonId();
        rbHabito = findViewById(rgHabito);
        String habito = rbHabito.getText().toString().toLowerCase();

        Intent intent = new Intent(v.getContext(), MainActivity.class);
        intent.putExtra("frecuencia", frecuencia);
        intent.putExtra("fechaInicio", fechaInicio);
        intent.putExtra("fechaFin", fechaFin);
        intent.putExtra("horario", horario);
        intent.putExtra("habito", habito);
        v.getContext().startActivity(intent);
    }

    public void irInicio(View v) {
        Intent intent = new Intent(v.getContext(), MainActivity.class);
        v.getContext().startActivity(intent);
    }

}
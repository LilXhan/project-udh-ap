package com.example.proyecto_udh_am;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ImageView ivPoster;

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

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        listaHabitos = new ArrayList<Habito>();

        db.collection("habitos")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String id = document.getId();
                                String nombre = document.getString("nombre");
                                String frecuencia = document.getString("frecuencia");
                                String fechaInicio = document.getString("fechaInicio");
                                String fechaFin = document.getString("fechaFin");
                                String horario = document.getString("horario");
                                int posterId = document.getLong("poster").intValue();

                                Habito habit = new Habito(nombre, frecuencia, fechaInicio, fechaFin, horario, posterId, id);
                                listaHabitos.add(habit);
                                Log.d(TAG, document.getId() + " => " + document.getData());

                            }
                            recyclerAdapter.notifyDataSetChanged();
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });



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

            Map<String, Object> habito = new HashMap<>();
            habito.put("frecuencia", frecuencia);
            habito.put("fechaInicio", fechaInicio);
            habito.put("fechaFin", fechaFin);
            habito.put("horario", horario);
            habito.put("nombre", nombre);
            habito.put("poster", poster);

            db.collection("habitos")
                    .add(habito)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "Error adding document", e);
                        }
                    });
        } catch (Exception e) {
            Toast.makeText(this, "Cargando...", Toast.LENGTH_SHORT).show();
        }

    }
    public void nuevoHabito(View v) {
        Intent intent = new Intent(v.getContext(), CrearHabito.class);
        v.getContext().startActivity(intent);
    }

}
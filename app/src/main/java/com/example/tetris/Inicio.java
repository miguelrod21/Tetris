package com.example.tetris;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Inicio extends AppCompatActivity {

    Button modoClasico;
    Button muerteSubita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        modoClasico = (Button)findViewById(R.id.clasico);
        modoClasico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Inicio.this,MainActivity.class);
                intent.putExtra("MODO",0);
                startActivity(intent);
            }
        });
        muerteSubita = (Button) findViewById(R.id.subita);
        muerteSubita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(Inicio.this,MainActivity.class);
                intent2.putExtra("MODO",1);
                startActivity(intent2);
            }
        });
    }

}

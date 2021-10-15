package com.example.guessnumber.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.guessnumber.databinding.ActivityEndPlayBinding;
import com.example.guessnumber.model.data.Jugador;

public class EndPlayActivity extends AppCompatActivity {

    ActivityEndPlayBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEndPlayBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //Recogemos el jugador creado en GuessNumberApplication para usarlo aquí.
        Jugador jugador = ((GuessNumberApplication)getApplication()).getJugador();
        //Preparamos la interfaz con los datos del jugador
        binding.tvResultado.setText("El jugador "+jugador.getNombre()+" ha "+jugador.getEstado()+" en " +
                jugador.getIntentosActuales() + " intentos.");
        binding.tvSecretNumber.setText("El número secreto era " + jugador.getNumeroSecreto());
    }

    /**
     * Método empleado para volver a jugar. Nos redireccionará a la primera Activity.
     * @param view
     */
    public void VolverAJugar(View view) {
        Intent intent = new Intent(this, ConfigActivity.class);
        startActivity(intent);
        finish();
    }

}
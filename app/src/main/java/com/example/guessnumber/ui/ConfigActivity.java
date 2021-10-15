package com.example.guessnumber.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.guessnumber.databinding.ActivityConfigBinding;

public class ConfigActivity extends AppCompatActivity {

    private ActivityConfigBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityConfigBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    /**
     * Método con el que gestionamos lo siguiente:
     * 1. Comprobamos que los campos no estén vacíos.
     * 2. Comprobamos que el número de intentos introducido este en el rengo 1-100
     * 3. Editamos los campos del Jugador creado en GuessNumberApplication con los que introdujo el usuario.
     * 4. Iniciamos la siguiente Activity y cerramos esta.
     * @param view
     */
    public void play(View view) {
        if (comprobarCamposVacios()) {
            Toast.makeText(this, "Debes rellenar los campos", Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            if (Integer.parseInt(binding.etIntentos.getText().toString()) <= 0 || Integer.parseInt(binding.etIntentos.getText().toString()) > 100) {
                Toast.makeText(this, "Intentos entre 1 y 100", Toast.LENGTH_SHORT).show();
                return;
            }
            ((GuessNumberApplication) getApplication()).setJugador(binding.etPlayer.getText().toString(),
                    Integer.parseInt(binding.etIntentos.getText().toString()));
            Intent intent = new Intent(this, PlayActivity.class);
            startActivity(intent);
            finish();
        }
    }

    /**
     * Método con el cual podemos comprobar si los campos que deben estar rellenos para proceder
     * con el curso del programa lo están.
     * @return Devuelve true si están vacíos y false si no lo están.
     */
    public boolean comprobarCamposVacios() {
        String etPlayer = binding.etPlayer.getText().toString();
        String etIntentos = String.valueOf(binding.etIntentos.getText());

        if (etPlayer.equals("") || etIntentos.equals(""))
            return true;

        return false;
    }
}
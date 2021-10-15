package com.example.guessnumber.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.guessnumber.databinding.ActivityPlayBinding;
import com.example.guessnumber.model.data.Jugador;

public class PlayActivity extends AppCompatActivity {

    ActivityPlayBinding binding;
    Jugador jugador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPlayBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //Recogemos al Jugador que tenemos en GuessNumberApplication para usarlo.
        this.jugador = ((GuessNumberApplication)getApplication()).getJugador();
        //Preparamos la interfaz del programa
        binding.tvJugador.setText("Hola "+jugador.getNombre()+", a jugar!");
        binding.tvIntentosRestantes.setText("Dispones de "+String.valueOf(jugador.getIntentosMaximos()) +
        " intentos.");
    }

    /**
     * Método con el que comprobamos si el número introducido por el jugador es igual, menor o mayor
     * que el número secreto.
     * @param view
     */
    public void Comprobar(View view) {
        if (comprobarCamposVacios()) {
            Toast.makeText(this, "Debes introducir un número (1-100)", Toast.LENGTH_SHORT).show();
            return;
        }
            int numeroIntroducido = Integer.parseInt(binding.etNumero.getText().toString());
            Intent intent = new Intent(this, EndPlayActivity.class);
            AlertDialog.Builder dialogo = new AlertDialog.Builder(this)
                    .setCancelable(false)
                    .setTitle("Has fallado :(")
                    .setPositiveButton("Reintentar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            binding.etNumero.setText("");
                            jugador.setIntentosActuales(jugador.getIntentosActuales() + 1);
                            jugador.setIntentosMaximos(jugador.getIntentosMaximos() - 1);
                            binding.tvIntentosRestantes.setText("Dispones de " +
                                    String.valueOf(jugador.getIntentosMaximos()) + " intentos.");
                        }
                    });

            if (numeroIntroducido == jugador.getNumeroSecreto()) {
                Ganar(intent);
                return;
            }
            if (numeroIntroducido < jugador.getNumeroSecreto()) {
                NumeroMenor(intent, dialogo);
                return;
            }
            if (numeroIntroducido > jugador.getNumeroSecreto()) {
                NumeroMayor(intent, dialogo);
                return;
            }
    }

    /**
     * Método que gestiona el curso del programa en caso de que el jugador acierte el número secreto
     * @param intent Inicia la siguiente Activity.
     */
    public void Ganar(Intent intent) {
        jugador.setEstado("ganado");
        jugador.setIntentosActuales(jugador.getIntentosActuales()+1);
        startActivity(intent);
        finish();
    }

    /**
     * Método que gestiona el curso del programa en caso de que el jugador introduzca un número
     * menor al número secreto.
     * @param intent Inicia la siguiente Activity en caso de que el jugador se quede sin intentos.
     * @param dialogo Si el jugador aún dispone de intentos, lanzará un cuadro de diálogo con la
     *                opción de reintentar.
     */
    public void NumeroMenor(Intent intent, AlertDialog.Builder dialogo) {
            if (jugador.getIntentosMaximos() == 1) {
                jugador.setEstado("perdido");
                jugador.setIntentosActuales(jugador.getIntentosActuales()+1);
                startActivity(intent);
                finish();
                return;
            }
            dialogo.setMessage("El número introducido es menor al número secreto.");
            dialogo.show();
        }

    /**
     * Método que gestiona el curso del programa en caso de que el jugador introduzca un número
     * mayor al número secreto.
     * @param intent Inicia la siguiente Activity en caso de que el jugador se quede sin intentos.
     * @param dialogo Si el jugador aún dispone de intentos, lanzará un cuadro de diálogo con la
     *                opción de reintentar.
     */
    public void NumeroMayor(Intent intent, AlertDialog.Builder dialogo) {
            if (jugador.getIntentosMaximos() == 1) {
                jugador.setEstado("perdido");
                jugador.setIntentosActuales(jugador.getIntentosActuales()+1);
                startActivity(intent);
                finish();
                return;
            }
            dialogo.setMessage("El número introducido es mayor al número secreto.");
            dialogo.show();
        }

    /**
     * Método con el cual podemos comprobar si los campos que deben estar rellenos para proceder
     * con el curso del programa lo están.
     * @return Devuelve true si están vacíos y false si no lo están.
     */
    public boolean comprobarCamposVacios() {
        String etPlayer = binding.etNumero.getText().toString();

        if (etPlayer.equals("") || Integer.parseInt(etPlayer) <= 0 || Integer.parseInt(etPlayer) > 100 )
            return true;

        return false;
    }
}
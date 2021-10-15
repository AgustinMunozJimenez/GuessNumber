package com.example.guessnumber.ui;

import android.app.Application;

import com.example.guessnumber.model.data.Jugador;

/**
 * Con esta clase conseguimos tener una instancia de la clase Jugador visible para todas las
 * Activities de nuestro proyecto.
 */
public class GuessNumberApplication extends Application {
    Jugador jugador;
    @Override
    public void onCreate() {
        super.onCreate();
    }

    /**
     * Método para editar el Jugador
     * @param jugador Nombre del jugador
     * @param intentos Número de intentos
     */
    public void setJugador(String jugador, int intentos) {
        this.jugador = new Jugador(jugador, intentos);
    }

    /**
     * Método para devolver nuestra instancia de Jugador
     * @return
     */
    public Jugador getJugador() {
        return jugador;
    }
}

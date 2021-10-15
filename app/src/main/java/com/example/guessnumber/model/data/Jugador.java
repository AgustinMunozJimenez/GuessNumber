package com.example.guessnumber.model.data;

import java.io.Serializable;
import java.util.Random;

/**
 * Clase creada para gestionar todos los datos de un Jugador. Gracias a ella podemos gestionar su
 * nombre, sus intentos y su estado.
 */

public class Jugador implements Serializable {

    private String Nombre;
    private int IntentosMaximos;
    private int IntentosActuales;
    private int NumeroSecreto;
    private String Estado;

    public Jugador(String nombre, int intentos) {
        Nombre = nombre;
        IntentosMaximos = intentos;
        IntentosActuales = 0;
        NumeroSecreto = 1+ new Random().nextInt(100);
        Estado = "Jugando";
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public int getIntentosMaximos() {
        return IntentosMaximos;
    }

    public void setIntentosMaximos(int intentos) {
        IntentosMaximos = intentos;
    }

    public int getIntentosActuales() {
        return IntentosActuales;
    }

    public void setIntentosActuales(int intentos) {
        IntentosActuales = intentos;
    }

    public int getNumeroSecreto() { return NumeroSecreto; }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }
}
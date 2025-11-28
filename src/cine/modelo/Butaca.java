package cine.modelo;

import java.io.Serializable;

/**
 * Butaca simple con fila, número y estado ocupado.
 * Los índices publicos empiezan en 1 (más comodo para el usuario)).
 */
public class Butaca implements Serializable {
    private static final long serialVersionUID = 1L;

    private int fila;
    private int numero;
    private boolean ocupada;

    public Butaca(int fila, int numero) {
        this.fila = fila;
        this.numero = numero;
        this.ocupada = false;
    }

    public int getFila() { return fila; }
    public int getNumero() { return numero; }
    public boolean isOcupada() { return ocupada; }

    public void ocupar() { this.ocupada = true; }
    public void liberar() { this.ocupada = false; }

    @Override
    public String toString() {
        return "F" + fila + "N" + numero + (ocupada ? " (X)" : " ( )");
    }
}
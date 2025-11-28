package cine.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Sala - representa una sala con butacas en matriz.
 * Para el parcial se inicializa con filas x columnas fijas.
 */
public class Sala implements Serializable {
    private static final long serialVersionUID = 1L;

    private int numero;
    private String pelicula;
    private Butaca[][] butacas;
    private int filas;
    private int columnas;

    public Sala(int numero, String pelicula, int filas, int columnas) {
        this.numero = numero;
        this.pelicula = pelicula;
        this.filas = filas;
        this.columnas = columnas;
        this.butacas = new Butaca[filas][columnas];
        inicializarButacas();
    }

    private void inicializarButacas() {
        for (int f = 0; f < filas; f++) {
            for (int c = 0; c < columnas; c++) {
                butacas[f][c] = new Butaca(f + 1, c + 1);
            }
        }
    }

    public int getNumero() { return numero; }
    public String getPelicula() { return pelicula; }
    public int getFilas() { return filas; }
    public int getColumnas() { return columnas; }

    public Butaca getButaca(int fila, int numero) {
        if (fila < 1 || fila > filas || numero < 1 || numero > columnas) return null;
        return butacas[fila - 1][numero - 1];
    }

    public List<Butaca> getButacasComoLista() {
        List<Butaca> lista = new ArrayList<>();
        for (int f = 0; f < filas; f++) {
            for (int c = 0; c < columnas; c++) {
                lista.add(butacas[f][c]);
            }
        }
        return lista;
    }

    @Override
    public String toString() {
        return "Sala " + numero + " - " + pelicula;
    }
}
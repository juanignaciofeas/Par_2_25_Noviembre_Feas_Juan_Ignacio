package cine.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Cine - aglutina salas, clientes y entradas.
 * Permite inicializar datos de ejemplo y operaciones básicas.
 */
public class Cine implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Sala> salas = new ArrayList<>();
    private List<Entrada> entradas = new ArrayList<>();
    private List<Cliente> clientes = new ArrayList<>();

    public Cine() {}

    public List<Sala> getSalas() { return salas; }
    public List<Entrada> getEntradas() { return entradas; }
    public List<Cliente> getClientes() { return clientes; }

    // buscar cliente por email (case-insensitive)
    public Cliente buscarClientePorEmail(String email) {
        for (Cliente c : clientes) {
            if (c.getEmail().equalsIgnoreCase(email)) return c;
        }
        return null;
    }

    public void agregarCliente(Cliente c) { clientes.add(c); }
    public void agregarEntrada(Entrada e) { entradas.add(e); }

    // si no hay datos, creo algunos para poder probar
    public void inicializarConDatosEjemplo() {
        if (!salas.isEmpty()) return;
        Sala s1 = new Sala(1, "Los Vengadores", 5, 8);
        Sala s2 = new Sala(2, "Toy Story", 6, 7);
        Sala s3 = new Sala(3, "Noche de Terror", 4, 6);
        salas.add(s1);
        salas.add(s2);
        salas.add(s3);
    }
}
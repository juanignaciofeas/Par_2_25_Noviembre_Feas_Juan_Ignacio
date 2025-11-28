package cine.controlador;

import cine.modelo.*;
import cine.persistencia.PersistenciaDatos;

import java.util.List;

/**
 * CineController - lógica principal: listar salas, comprar entrada, exponer entradas.
 * Método getEntradas() para que la vista no acceda directamente al modelo.
 */
public class CineController {
    private Cine cine;

    public CineController(Cine cine) {
        this.cine = cine;
    }

    public List<Sala> listarSalas() {
        return cine.getSalas();
    }

    public Sala obtenerSalaPorNumero(int numero) {
        for (Sala s : cine.getSalas()) {
            if (s.getNumero() == numero) return s;
        }
        return null;
    }

    // comprar una entrada: marca la butaca, crea la entrada y persiste
    public boolean comprarEntrada(Cliente cliente, Sala sala, int fila, int numero) {
        Butaca b = sala.getButaca(fila, numero);
        if (b == null || b.isOcupada()) return false;
        b.ocupar();
        Entrada e = new Entrada(cliente, sala, b);
        cine.agregarEntrada(e);
        PersistenciaDatos.guardar(cine);
        return true;
    }

    // expone las entradas (para "ver mis entradas")
    public List<Entrada> getEntradas() {
        return cine.getEntradas();
    }

    // guardar estado manualmente si se necesita
    public void guardarEstado() {
        PersistenciaDatos.guardar(cine);
    }
}
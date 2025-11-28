package cine.controlador;

import cine.modelo.Cliente;
import cine.modelo.Cine;

/**
 * LoginController - maneja login y registro simple.
 * En el parcial basta con validar email/password en memoria.
 */
public class LoginController {
    private Cine cine;

    public LoginController(Cine cine) {
        this.cine = cine;
    }

    // intenta loguear, devuelve Cliente o null
    public Cliente login(String email, String password) {
        Cliente c = cine.buscarClientePorEmail(email);
        if (c != null && c.getPassword().equals(password)) {
            return c;
        }
        return null;
    }

    // registra un cliente nuevo si no existe el email
    public boolean registrar(String nombre, String email, String password) {
        if (cine.buscarClientePorEmail(email) != null) {
            return false; // ya existe
        }
        Cliente nuevo = new Cliente(nombre, email, password);
        cine.agregarCliente(nuevo);
        return true;
    }
}
package cine.modelo;

import java.io.Serializable;

/**
 * Cliente - representa un usuario del sistema.
 * Nota de estudiante: en un sistema real NUNCA guardaríamos la password en texto plano.
 */
public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nombre;
    private String email;
    private String password;

    public Cliente(String nombre, String email, String password) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
    }

    public String getNombre() { return nombre; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }

    @Override
    public String toString() {
        return nombre + " <" + email + ">";
    }
}
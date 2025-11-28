package cine.persistencia;

import cine.modelo.Cine;

import java.io.*;

/**
 * PersistenciaDatos - serializa/deserializa el objeto Cine a cine.ser
 * Estilo estudiante: simple, funcional y suficiente para el parcial.
 */
public class PersistenciaDatos {

    private static final String ARCHIVO = "cine.ser";

    public static Cine cargar() {
        File f = new File(ARCHIVO);
        if (!f.exists()) return null;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(f))) {
            Object o = in.readObject();
            if (o instanceof Cine) {
                return (Cine) o;
            } else {
                System.err.println("Archivo no contiene un Cine válido.");
                return null;
            }
        } catch (Exception e) {
            System.err.println("Error al cargar persistencia: " + e.getMessage());
            return null;
        }
    }

    public static boolean guardar(Cine cine) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ARCHIVO))) {
            out.writeObject(cine);
            return true;
        } catch (IOException e) {
            System.err.println("Error al guardar persistencia: " + e.getMessage());
            return false;
        }
    }
}
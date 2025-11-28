package cine.vista;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

/**
 * ConfirmDialog - utilitario simple para preguntar confirmaciones
 */
public class ConfirmDialog {

    public static boolean mostrarConfirmacion(String titulo, String mensaje) {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setTitle(titulo);
        a.setHeaderText(null);
        a.setContentText(mensaje);

        Optional<ButtonType> res = a.showAndWait();
        return res.isPresent() && res.get() == ButtonType.OK;
    }
}
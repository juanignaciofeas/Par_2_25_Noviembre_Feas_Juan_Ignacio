package cine;

import cine.controlador.CineController;
import cine.controlador.LoginController;
import cine.modelo.Cine;
import cine.modelo.Cliente;
import cine.persistencia.PersistenciaDatos;
import cine.vista.LoginView;
import cine.vista.SalaView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * MainApp - punto de entrada de la aplicación.
 * Mantiene UNA sola instancia de Cine y orquesta vistas.
 * Comentarios estilo estudiante, directo y claro.
 */
public class MainApp extends Application {

    private Cine cine; // única instancia compartida por toda la app

    @Override
    public void start(Stage stage) {
        // intento cargar estado persistido, si no hay creo datos de ejemplo
        cine = PersistenciaDatos.cargar();
        if (cine == null) {
            cine = new Cine();
            cine.inicializarConDatosEjemplo();
        }

        // controlador de login (usa la misma instancia de cine)
        LoginController loginCtrl = new LoginController(cine);
        LoginView loginView = new LoginView(loginCtrl);

        // escena de login: cuando loguea, se crea el controller para salas y se cambia la escena
        Scene loginScene = loginView.createScene(clienteLogueado -> {
            CineController cineCtrl = new CineController(cine);
            SalaView salaView = new SalaView(cineCtrl, clienteLogueado);
            Scene salaScene = salaView.createScene();
            stage.setScene(salaScene);
        });

        stage.setTitle("Cine - Parcial (UTN)");
        stage.setScene(loginScene);
        stage.show();

        // guardar al cerrar la app (si el entorno lo invoca)
        stage.setOnCloseRequest(ev -> {
            cineCtrlSave();
        });
    }

    // método auxiliar para guardar estado al cerrar
    private void cineCtrlSave() {
        if (cine != null) {
            PersistenciaDatos.guardar(cine);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
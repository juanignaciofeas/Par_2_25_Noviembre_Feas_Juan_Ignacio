package cine.vista;

import cine.controlador.LoginController;
import cine.modelo.Cliente;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 * LoginView - formulario básico de login y registro.
 * Callback al logueo para que MainApp muestre la siguiente pantalla.
 */
public class LoginView {

    private LoginController controller;

    public interface LoginCallback {
        void onLogin(Cliente cliente);
    }

    public LoginView(LoginController controller) {
        this.controller = controller;
    }

    public Scene createScene(LoginCallback callback) {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(18));
        grid.setHgap(8);
        grid.setVgap(8);

        Text t = new Text("Ingreso / Registro");
        Label lEmail = new Label("Email:");
        Label lPass = new Label("Password:");
        TextField tfEmail = new TextField();
        PasswordField pfPass = new PasswordField();
        Button btnLogin = new Button("Ingresar");
        Button btnReg = new Button("Registrar");
        Label lblMsg = new Label();

        grid.add(t, 0, 0, 2, 1);
        grid.add(lEmail, 0, 1);
        grid.add(tfEmail, 1, 1);
        grid.add(lPass, 0, 2);
        grid.add(pfPass, 1, 2);

        HBox hb = new HBox(10, btnLogin, btnReg);
        grid.add(hb, 1, 3);
        grid.add(lblMsg, 0, 4, 2, 1);

        btnLogin.setOnAction(ae -> {
            String email = tfEmail.getText().trim();
            String pass = pfPass.getText().trim();
            if (email.isEmpty() || pass.isEmpty()) {
                lblMsg.setText("Complete email y password.");
                return;
            }
            Cliente c = controller.login(email, pass);
            if (c == null) {
                lblMsg.setText("Credenciales incorrectas.");
            } else {
                lblMsg.setText("Ingreso OK. Bienvenido " + c.getNombre());
                callback.onLogin(c);
            }
        });

        btnReg.setOnAction(ae -> {
            TextInputDialog td = new TextInputDialog();
            td.setTitle("Registro");
            td.setHeaderText("Ingrese su nombre:");
            td.setContentText("Nombre:");
            td.showAndWait().ifPresent(nombre -> {
                String email = tfEmail.getText().trim();
                String pass = pfPass.getText().trim();
                if (nombre.isEmpty() || email.isEmpty() || pass.isEmpty()) {
                    lblMsg.setText("Complete todos los campos para registrarse.");
                    return;
                }
                boolean ok = controller.registrar(nombre, email, pass);
                if (ok) {
                    lblMsg.setText("Registrado. Ahora ingrese.");
                } else {
                    lblMsg.setText("Ya existe un usuario con ese email.");
                }
            });
        });

        return new Scene(grid, 420, 260);
    }
}
package cine.vista;

import cine.modelo.*;
import cine.controlador.CineController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import java.util.List;

/**
 * Vista de salas y butacas.
 * Diseño simple estilo estudiante: botones básicos, sin colores fancy.
 */
public class SalaView {

    private CineController controller;
    private Cliente cliente; // cliente logueado

    public SalaView(CineController controller, Cliente cliente) {
        this.controller = controller;
        this.cliente = cliente;
    }

    public Scene createScene() {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));

        Text title = new Text("Seleccione una sala y luego una butaca");
        root.setTop(title);
        BorderPane.setAlignment(title, Pos.CENTER);

        // Lista de salas
        ListView<String> lv = new ListView<>();
        List<Sala> salas = controller.listarSalas();
        for (Sala s : salas) lv.getItems().add("Sala " + s.getNumero() + " - " + s.getPelicula());

        // Panel central donde irán las butacas
        VBox centerBox = new VBox(10);
        centerBox.setPadding(new Insets(10));
        centerBox.getChildren().add(new Label("Seleccione una sala."));

        // Cuando selecciona sala
        lv.getSelectionModel().selectedIndexProperty().addListener((obs, oldV, newV) -> {
            if (newV == null || newV.intValue() < 0) return;

            Sala salaSel = salas.get(newV.intValue());
            centerBox.getChildren().clear();
            centerBox.getChildren().add(new Label("Sala " + salaSel.getNumero() +
                    " - " + salaSel.getPelicula()));

            GridPane grid = generarGrillaButacas(salaSel);
            centerBox.getChildren().add(grid);
        });

        root.setLeft(lv);
        root.setCenter(centerBox);

        // Botón para ver entradas del usuario
        Button btnResumen = new Button("Ver mis entradas");
        btnResumen.setOnAction(ae -> mostrarEntradas());

        HBox bottom = new HBox(10, btnResumen);
        bottom.setPadding(new Insets(10));
        root.setBottom(bottom);

        return new Scene(root, 700, 450);
    }

    // Grilla sin estilos pro, solo cuadraditos básicos
    private GridPane generarGrillaButacas(Sala sala) {
        GridPane grid = new GridPane();
        grid.setHgap(5);
        grid.setVgap(5);

        for (int f = 1; f <= sala.getFilas(); f++) {
            for (int c = 1; c <= sala.getColumnas(); c++) {

                Butaca b = sala.getButaca(f, c);
                Button btn = new Button(f + "-" + c);
                btn.setPrefWidth(45);

                if (b.isOcupada()) {
                    btn.setDisable(true);
                }

                final int fila = f;
                final int numero = c;
                btn.setOnAction(ae -> {
                    boolean confirmar = ConfirmDialog.mostrarConfirmacion(
                            "Confirmar compra",
                            "¿Comprar butaca " + fila + "-" + numero + "?"
                    );

                    if (confirmar) {
                        boolean ok = controller.comprarEntrada(cliente, sala, fila, numero);
                        if (ok) {
                            btn.setDisable(true);
                            new Alert(Alert.AlertType.INFORMATION, "Compra realizada.").showAndWait();
                        } else {
                            new Alert(Alert.AlertType.ERROR, "No se pudo comprar.").showAndWait();
                        }
                    }
                });

                grid.add(btn, c - 1, f - 1);
            }
        }
        return grid;
    }

    // **Corregido: usa getEntradas() del controlador**
    private void mostrarEntradas() {
        StringBuilder sb = new StringBuilder();

        for (Entrada e : controller.getEntradas()) {
            if (e.getCliente().getEmail().equalsIgnoreCase(cliente.getEmail())) {
                sb.append(e.toString()).append("\n");
            }
        }

        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("Entradas");
        a.setHeaderText("Tus compras");
        a.setContentText(sb.length() == 0 ? "No tenés entradas." : sb.toString());
        a.showAndWait();
    }
}
//Controlador
package Controlador;

import Modelo.SchoolGradingSystem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SistemaDeCalificacionController {

    @FXML
    private TextArea txtDatosEntrada;

    @FXML
    private TextArea txtDatosSalida;

    @FXML
    private TextField txtNumeroRegistro;    

    @FXML
    private Button btnCalcular;

    @FXML
    void calcular(ActionEvent event) {
        SchoolGradingSystem registroCompleto = new SchoolGradingSystem();
        registroCompleto.loadData(txtNumeroRegistro.getText(), txtDatosEntrada.getText());
        txtDatosSalida.setText(Double.toString((registroCompleto.stat1())) + "\n" + Integer.toString(registroCompleto.stat2()) + "\n" + 
        registroCompleto.stat3() + "\n" + registroCompleto.stat4());
    }

}

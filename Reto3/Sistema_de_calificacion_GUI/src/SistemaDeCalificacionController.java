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
        SchoolGradingSystem estudiante = new SchoolGradingSystem();
        estudiante.loadData(txtNumeroRegistro.getText(), txtDatosEntrada.getText());
        txtDatosSalida.setText(Double.toString((estudiante.stat1())) + "\n" + Integer.toString(estudiante.stat2()) + "\n" + 
        estudiante.stat3() + "\n" + estudiante.stat4());
    }

}

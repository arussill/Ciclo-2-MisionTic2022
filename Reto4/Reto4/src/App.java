import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage ventana) throws Exception {
        Parent vista = FXMLLoader.load(getClass().getResource("frmSistemaEstadisticoDeCalificaciones.fxml"));//lo que esta entre comillas es el nombre del archivo generado de scenbuldier;//carga el formulario
        Scene scene = new Scene(vista); //crea una escena
        ventana.setScene(scene);        //Asociar la ventana a una escena  
        ventana.show();                 //mostrar la ventana
    }

} 
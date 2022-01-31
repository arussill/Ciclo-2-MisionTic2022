import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application{
    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent vista = FXMLLoader.load(getClass().getResource("SistemaDeCalificacion.fxml"));
        Scene scene = new Scene(vista);
        stage.setScene(scene);
        stage.show();
        
    }
}

//Controllador de la GUI
//librerias usadas
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class SistemaEstadisticoDeCalificacionesController {
    //instancio objeto estudiante de la calse hija SchoolGrdienSystem
    private SchoolGradingSystem estudiante = new SchoolGradingSystem();
    private int contadorRegistros = 0; //inicializo contador para usarlo en el metodo procesar
     
    //atributos de la GUI
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Tab tabIngresarDatos;//tab1

    @FXML
    private Button btnGuardar;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtNota;

    @FXML
    private TextField txtMateria;

    @FXML
    private TextField txtGenero;

    @FXML
    private Tab tabProcesar;//tab2

    @FXML
    private Button btnCargar;

    @FXML
    private Button btnProcesar;

    @FXML
    private TextArea txtAreaIzq;

    @FXML
    private TextArea txtAreaDer;

    @FXML
    private Tab tabConsultarEliminar;//tab3

    @FXML
    private TextField txtNombreConsultaEliminar;

    @FXML
    private TextField txtMateriaConsultaEliminar;

    @FXML
    private Button btnConsultar;

    @FXML
    private Button btnEliminar;

    @FXML
    private TextArea txtResultado;

    //Metodos de la GUI
    @FXML
    void cargar(ActionEvent event) {
        limpiar(); //antes de cargar cualquier dato se hace una limpieza de las cajas de textos de esta pestaña (tab2)//metodo limpiar que se encuentra al final
        String salidaCargar = "" ;  //inicializacion de la variable que se mostrara en la caja de texto izq
        String nombre, genero, materia, nota;  
        Connection con = Conexion.conectar(); //coneccion a la base de datos
        String sql = "SELECT * FROM SitemaEstadisticoDeCalificacion";//preparacion de la sentencia antes de pasarlo a la db
        try {//maneja las exepciones que se pueden presentar por PreparedStament, y poder manejarlas
            PreparedStatement ps = con.prepareStatement(sql); //con esto se formatea la sentencia preparada
            ResultSet rs = ps.executeQuery();//se almacena el resultado obtenido por la db gracias al metodo executeQuery(), en la variable rs tipo ResultSet
            try (con;ps;rs){    //Esto es para poder cerrar las variables que esta en los parentecis al finalizar este metodo y liberar recursos
                while (rs.next()){//este while recorre cada registro rs.next() de la db
                    for (int e=0; e<estudiante.vector_nombre.length; e++){//reccorre el vector_nombre de la super clase 
                        if (rs.getString("nombre").equalsIgnoreCase(estudiante.vector_nombre[e])){//comprobo si elnombre obtenido de la db esta en el vector_nombre 
                            nombre = Double.toString(e+1.0);  //si el nombre se encuentra le asigno su codigo correspondiente
                            salidaCargar = salidaCargar + nombre + " ";//voy concatenado lo que se va mostrar en la caja de txtoIzq de la tab2
                        }
                    }
                    if (rs.getString("genero").equalsIgnoreCase("m")){//comprobo si el genero obtenido de la db es m o f
                        genero = "0.0";//se le asigna su codigo correspondiendo al genero
                        salidaCargar = salidaCargar + genero + " ";//concatenando lo que se mostrara en la salida de txtIzq de la tab2
                    }else{
                        genero = "1.0";
                        salidaCargar = salidaCargar + genero + " ";
                    }
                    for (int m=0; m<estudiante.vector_materia.length; m++){//recorro el vector_materia de la super clase
                        if (rs.getString("materia").equalsIgnoreCase(estudiante.vector_materia[m])){//comprobo si la materia obtenida de db esta en el vector_materia de la super clase
                            materia = Double.toString(m+1.0);//se le asigna el codigo correspondiente
                            salidaCargar = salidaCargar + materia + " ";//se concatena para mostrar en la salida de txrIzq de la tab2
                        }
                    }
                    nota = Double.toString(rs.getDouble("nota"));//se obtiene la nota del db
                    salidaCargar = salidaCargar + nota + "\n"; //se concatena todo lo anterior y cada linea se separa por salto de linea y tener el textocompleto
                }
                txtAreaIzq.setText(salidaCargar);//se muesra la variable anterior en la en el cuadro de txtIzq
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }             
    }

    @FXML
    void consultar(ActionEvent event) {
        Connection con = Conexion.conectar();
        String salida = "" ;
        String sql = "SELECT * FROM SitemaEstadisticoDeCalificacion WHERE nombre = ? OR materia = ?";//se prepara la sentencia en lenguaje sql que se enviara a db
        try{
            PreparedStatement ps = con.prepareStatement(sql);//se formatea la sentencia anterior
            ps.setString(1, txtNombreConsultaEliminar.getText().toLowerCase());//esto son los valores correspondientes (1) que van en los signos de interogacion de la sentencia
            ps.setString(2, txtMateriaConsultaEliminar.getText().toLowerCase());//signo de interogacion 2
            ResultSet rs = ps.executeQuery();//con esto se obtiene los valores solicitados por la sentencia anterior a la db
            if(rs.next()){//se posiciona en el primer registro de la db
                salida = salida + rs.getString("nombre") + " " + rs.getString("genero") + " " + rs.getString("materia") + " " + rs.getString("nota") + "\n";//obtiene los datos desde la db del primer registro
                while(rs.next()){//recorre los registros o filas de la db
                    salida = salida + rs.getString("nombre") + " " + rs.getString("genero") + " " + rs.getString("materia") + " " + rs.getString("nota") + "\n";//obtiene los datos de los registros siguientes despues del primero desde la db,concatenado realizando un salto de linea cuando cambia de registro o fila en la db
                } 
            }else{//cuando no encuntra los datos que concidan con los valores que se pasaron en los interogarntes arroja esta alerta
                Alert alert = new Alert(AlertType.WARNING);
                alert.setContentText("Registro no encontrado");
                alert.show();
            }
            txtResultado.setText(salida);//muestra en txtResultado o caja de texto del tab3 la salida concatenada antes
            //se cierra lo siguiente para liberar recursos
            con.close();
            ps.close();
            rs.close();      
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }        
    }

    @FXML
    void eliminar(ActionEvent event) {
        Connection con = Conexion.conectar();
        String sql = "DELETE FROM SitemaEstadisticoDeCalificacion WHERE nombre = ? OR materia = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, txtNombreConsultaEliminar.getText().toLowerCase());//se formatea la sentencia sql que se pasara a la db se obtiene desde la caja de texto y se pasa a minuscula
            ps.setString(2, txtMateriaConsultaEliminar.getText().toLowerCase());
            ps.execute();
            limpiar();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText("Registro Eliminado");
            alert.show();
            con.close();
            ps.close();
            limpiar();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void guardar(ActionEvent event) {
        String mensaje = "", mensaje1 = "", mensaje2 = "", mensaje3 = "", mensaje4 = "";
        Connection con = Conexion.conectar();
        String sql = "INSERT INTO SitemaEstadisticoDeCalificacion (nombre,genero,materia,nota) VALUES (?,?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, txtNombre.getText().toLowerCase());    
            ps.setString(2, txtGenero.getText().toLowerCase());
            ps.setString(3, txtMateria.getText().toLowerCase());
            ps.setString(4, txtNota.getText().toLowerCase());
            for (int e=0; e<estudiante.vector_nombre.length;e++){//recorre vector_nombre clase padre 
                if (txtNombre.getText().equalsIgnoreCase(estudiante.vector_nombre[e])){//comprobar que el dato ingresado por la txtNombre de la vista se encuentre en la clase padre
                   mensaje1= "";
                   break;//una vex que encuentre el valor rompe el for para que el valor en mensaje1 no cambie
                }else{
                    mensaje1 = txtNombre.getText() + " No existe en la lista de estudiantes";
                }
            }
            for (int m=0; m<estudiante.vector_materia.length;m++){//recore el ector_materia
                if (txtMateria.getText().equalsIgnoreCase(estudiante.vector_materia[m])){//comprueba si la materia existe en el vector de la super calse
                    mensaje2 = "";
                    break;
                }else{
                    mensaje2 = "Esta materia no existe";
                }
            }
            if (txtGenero.getText().equalsIgnoreCase("m")||txtGenero.getText().equalsIgnoreCase("f")){//comprueba si lo ingresado en la caja de texto de genero de tab1 sea f o m, sin importar si esta en mayuscula o minuscula
               if (txtGenero.getText().equalsIgnoreCase("m")){//si es m comprueba si coincide con el nombre del estudiante es hombre o mujer
                    if (txtNombre.getText().equalsIgnoreCase(estudiante.vector_nombre[0]) || txtNombre.getText().equalsIgnoreCase(estudiante.vector_nombre[1]) || txtNombre.getText().equalsIgnoreCase(estudiante.vector_nombre[2])){
                        mensaje3 = "";          
                    }else{
                        mensaje3 = txtNombre.getText() + " No es del genero " + txtGenero.getText();
                    }
                }else{
                    if (txtNombre.getText().equalsIgnoreCase(estudiante.vector_nombre[3]) || txtNombre.getText().equalsIgnoreCase(estudiante.vector_nombre[4]) || txtNombre.getText().equalsIgnoreCase(estudiante.vector_nombre[5])){
                        mensaje3 = "";          
                    }else{
                        mensaje3 = txtNombre.getText() + " No es del genero " + txtGenero.getText();
                    }
                }
            }else{
                mensaje3 = "Debe ingresar M para masculino \n F para femenino";//si se escribe otra cosa diferente a f o m arroja este mensaje 
            } 
            if (txtNota.getText().contains(".")){//comprueba si el valor en nota tiene punto
                mensaje4 = "";
            }else{
                mensaje4 = "Debe ingresar la nota con formato decimal y con punto (.)";   
            }
            if (mensaje1.equals("") && mensaje2.equals("") && mensaje3.equals("") && mensaje4.equals("")){//comprueba que todo se cumpla 
                ps.executeUpdate();//ejecuta para guardar en la db
                mensaje = "Regsistro Guardado"; 
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setContentText(mensaje);
                alert.show();//muestra el mensaje
            }else if (mensaje1.equals("") == false) {//los sigientes else if solo son para mostrar los mensajes de error cuando no se cumple ningunas de las condiciones anteriores
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setContentText(mensaje1);
                alert.show();
            }else if (mensaje2.equals("") == false) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setContentText(mensaje2);
                alert.show();
            }else if (mensaje3.equals("") == false) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setContentText(mensaje3);
                alert.show();
            }else if (mensaje4.equals("") == false) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setContentText(mensaje4);
                alert.show();
            }
            limpiar();//limpia todas las cajas de texto del tab
            //liberacion de recursos
            con.close();
            ps.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
     
    @FXML
    void procesar(ActionEvent event) {
        Connection con = Conexion.conectar();
        String sql = "SELECT counT() FROM SitemaEstadisticoDeCalificacion";//sentencia q realiza conteo de la cantidad de registros que se encuentran en la db 
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();  //obtengo resultados de db
            contadorRegistros = rs.getInt("counT()");  //obtengo la cantidad del conteo de los registros de la db y lo almaceno en la variable contadorRegisto
            estudiante.loadData(contadorRegistros, txtAreaIzq.getText());//paso los argumentos necesarios a el metodo loadData de la clase hija
            txtAreaDer.setText(Double.toString((estudiante.stat1())) + "\n" + Integer.toString(estudiante.stat2()) + "\n" + 
            estudiante.stat3() + "\n" + estudiante.stat4());//se muestra en la caja de text txtDer las respuestas que se obtienen desde la clase hija calculados en la clase padre
            //los valores anteriores se concatenan porque en las cajas de texto se muestra un solo string 
            //liberacion de recursos
            con.close();
            ps.close();
            rs.close();  
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }       
    }

    //metodo que limpia diferentes cajas de textos de las diferentes tabs
    public void limpiar (){
        txtNombre.setText("");
        txtMateria.setText("");
        txtNota.setText("");
        txtGenero.setText("");
        txtNombreConsultaEliminar.setText("");
        txtMateriaConsultaEliminar.setText("");
        txtAreaDer.setText("");
        txtAreaIzq.setText("");
        txtResultado.setText("");
    }

}

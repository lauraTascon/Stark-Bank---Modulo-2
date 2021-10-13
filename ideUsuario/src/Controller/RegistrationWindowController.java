/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;



import Modelo.Company;
import Modelo.JsonCompanyDAO;
import Modelo.NaturalPerson;
import Modelo.JsonNaturalPersonDAO;
import Vista.Main;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class RegistrationWindowController extends FatherController implements Initializable {

    private ToggleGroup tipoClienteToggleGroup;
    private ArrayList<String> errores;
    private boolean formularioLleno;
    private NaturalPerson personaNatural;//un objeto tipo perosona natutal
    private Company empresa;
    int campNumber;
    TextField direccionNumUnoTF;
    TextField direccionNumDosTF;
    TextField direccionNumTresTF;
    TextField direccionLetraUnoTF;
    TextField direccionLetraDosTF;
    ComboBox direccionBisCB;
    ComboBox direccionViaCB;
    ComboBox direccionSectorCB;
    Button crearDireccionBT;

    @FXML
    private RadioButton personaNaturalRB;
    @FXML
    private RadioButton empresaRB;
    @FXML
    private Label documentoIdentidadLB;
    @FXML
    private Label nitLB;
    @FXML
    private Label nombreLB;
    @FXML
    private Label direccionLB;
    @FXML
    private Label numeroContactoLB;
    @FXML
    private Label ocupacionLB;
    @FXML
    private Label nombreEmpresaLB;
    @FXML
    private Label sectorComercialLB;
    @FXML
    private Label direccionEmpresaLB;
    @FXML
    private TextField documentoIdentidadTF;
    @FXML
    private TextField nitTF;
    @FXML
    private TextField nombreTF;
    @FXML
    private TextField direccionTF;
    @FXML
    private TextField numeroContactoTF;
    @FXML
    private TextField ocupacionTF;

    @FXML
    private TextField nombreEmpresaTF;
    @FXML
    private TextField direccionEmpresaTF;
    @FXML
    private Button ingresarBT;
    @FXML
    private Button limpiarBT;
    @FXML
    private Button abrirPanelBT;
    @FXML
    private Button abrirPanelBT2;

    @FXML
    private ComboBox sectorComercialCB;

    public RegistrationWindowController() {//constructor

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.errores = new ArrayList<String>();
        tipoClienteToggleGroup = new ToggleGroup();
        this.personaNaturalRB.setToggleGroup(tipoClienteToggleGroup);
        this.empresaRB.setToggleGroup(tipoClienteToggleGroup);
        personaNaturalRB.setSelected(true);
        ActionEvent evet = new ActionEvent();
        toggleCamposTexto(evet);

        agregarFormatoCampos();

        ObservableList<String> items = FXCollections.observableArrayList();//coleccion de items para sectores
        items.addAll("Sector agropecuarios", "Sector de servicios", "Sector industrial", "Sector de transporte",
                "Sector de comercio", "Sector financiero", "Sector de la construcción", "Sector minero y energético",
                "Sector solidario", "Sector de comunicaciones");
        this.sectorComercialCB.setItems(items);

        formularioLleno = true;

    }

    public void cambiaFormulario(boolean formulario) {
        this.formularioLleno = formulario;
    }

    public void toggleTextPerson() {//desactiva los campos de empresa para que solo queden los de persona 
        nitTF.setDisable(true);
        nombreEmpresaTF.setDisable(true);
        sectorComercialCB.setDisable(true);
        direccionEmpresaTF.setDisable(true);
        abrirPanelBT2.setDisable(true);
    }

    public void toggleTextEmpresa() {//activa los campos de persona
        nitTF.setDisable(false);
        nombreEmpresaTF.setDisable(false);
        sectorComercialCB.setDisable(false);
        direccionEmpresaTF.setDisable(false);
        abrirPanelBT2.setDisable(false);

    }

    @FXML
    private void toggleCamposTexto(ActionEvent event) {//dependiendo del radio button presioando se activan los campos el formulario
        if (this.tipoClienteToggleGroup.getSelectedToggle().equals(this.personaNaturalRB)) {
            toggleTextPerson();
            resetEmpresaForm();

        }//deshabilitar campos de empresa

        if (this.tipoClienteToggleGroup.getSelectedToggle().equals(this.empresaRB)) {
            toggleTextEmpresa();
        }//habilitar campos de empresa

    }

    @FXML
    public void abrirPanelDireccion(ActionEvent event) {//estructurar direccion persona
        Stage primaryStage = new Stage();
        GridPane grid = new GridPane();

        grid.setVgap(8);
        grid.setHgap(2);
        grid.setAlignment(Pos.CENTER);

        //insercion en la nueva ventana
        Label tipoViaLB = new Label("Tipo Via");
        GridPane.setConstraints(tipoViaLB, 0, 0, 1, 1, HPos.CENTER, VPos.CENTER);
        direccionViaCB = new ComboBox();
        GridPane.setConstraints(direccionViaCB, 0, 1, 1, 1, HPos.CENTER, VPos.CENTER);

        Label numUnoLB = new Label("numero");
        GridPane.setConstraints(numUnoLB, 2, 0, 1, 1, HPos.CENTER, VPos.CENTER);
        direccionNumUnoTF = new TextField();
        GridPane.setConstraints(direccionNumUnoTF, 2, 1, 1, 1, HPos.CENTER, VPos.CENTER);

        Label bisLB = new Label("Bis");
        GridPane.setConstraints(bisLB, 4, 0, 1, 1, HPos.CENTER, VPos.CENTER);
        direccionBisCB = new ComboBox();
        GridPane.setConstraints(direccionBisCB, 4, 1, 1, 1, HPos.CENTER, VPos.CENTER);

        Label letraUnoLB = new Label("Letra");
        GridPane.setConstraints(letraUnoLB, 6, 0, 1, 1, HPos.CENTER, VPos.CENTER);
        direccionLetraUnoTF = new TextField();
        GridPane.setConstraints(direccionLetraUnoTF, 6, 1, 1, 1, HPos.CENTER, VPos.CENTER);

        Label sectorLB = new Label("Sector");
        GridPane.setConstraints(sectorLB, 8, 0, 1, 1, HPos.CENTER, VPos.CENTER);
        direccionSectorCB = new ComboBox();
        GridPane.setConstraints(direccionSectorCB, 8, 1, 1, 1, HPos.CENTER, VPos.CENTER);

        Label numeralLB = new Label("#");
        GridPane.setConstraints(numeralLB, 0, 3, 1, 1, HPos.CENTER, VPos.CENTER);

        Label numDosLB = new Label("numero");
        GridPane.setConstraints(numDosLB, 2, 2, 1, 1, HPos.CENTER, VPos.CENTER);
        direccionNumDosTF = new TextField();
        GridPane.setConstraints(direccionNumDosTF, 2, 3, 1, 1, HPos.CENTER, VPos.CENTER);

        Label letraDosLB = new Label("letra");
        GridPane.setConstraints(letraDosLB, 4, 2, 1, 1, HPos.CENTER, VPos.CENTER);
        direccionLetraDosTF = new TextField();
        GridPane.setConstraints(direccionLetraDosTF, 4, 3, 1, 1, HPos.CENTER, VPos.CENTER);

        Label guionLB = new Label("-");
        GridPane.setConstraints(guionLB, 6, 3, 1, 1, HPos.CENTER, VPos.CENTER);

        Label numTresLB = new Label("numero");
        GridPane.setConstraints(numTresLB, 8, 2, 1, 1, HPos.CENTER, VPos.CENTER);
        direccionNumTresTF = new TextField();
        GridPane.setConstraints(direccionNumTresTF, 8, 3, 1, 1, HPos.CENTER, VPos.CENTER);

        //llenado de los combo box
        ObservableList<String> items2 = FXCollections.observableArrayList();//coleccion de items para direccionVia
        items2.addAll("Calle", "Carrera", "Transversal", "Diagonal", "Avenida");
        direccionViaCB.setItems(items2);

        ObservableList<String> items3 = FXCollections.observableArrayList();//coleccion de items para direccionSector
        items3.addAll("Este", "Norte", "Oeste", "Sur");
        direccionSectorCB.setItems(items3);

        ObservableList<String> items4 = FXCollections.observableArrayList();//coleccion de items para direccionSector
        items4.addAll("Bis");
        direccionBisCB.setItems(items4);

        crearDireccionBT = new Button("crear");
        GridPane.setConstraints(crearDireccionBT, 4, 6);
        //GridPane

        //formato de campos
        formatText(direccionLetraUnoTF);
        formatText(direccionLetraDosTF);
        formatTextNumber(direccionNumUnoTF);
        formatTextNumber(direccionNumDosTF);
        formatTextNumber(direccionNumTresTF);
        //validacion de campos

        if (event.getSource() == abrirPanelBT2) {    //saber a que textField debe ir la direccion   
            crearDireccionBT.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {//cuando se da clic al boton crear direccionPersona natural
                    validarCamposDireccion();
                    if (formularioLleno == true) {
                        direccionEmpresaTF.setText(concatenaDireccion());
                        primaryStage.close();
                    }
                    errores();
                }
            });
        }
        if (event.getSource() == abrirPanelBT) {
            crearDireccionBT.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {//cuando se da clic al boton crear direccionPersona natural
                    validarCamposDireccion();
                    if (formularioLleno == true) {
                        direccionTF.setText(concatenaDireccion());
                        primaryStage.close();
                    }
                    errores();
                }
            });

        }

        grid.getChildren().addAll(tipoViaLB, direccionViaCB, direccionNumUnoTF, direccionNumDosTF, direccionNumTresTF,
                direccionBisCB, direccionSectorCB, direccionLetraUnoTF, direccionLetraDosTF,
                numUnoLB, numDosLB, numTresLB, letraUnoLB, letraDosLB, sectorLB, numeralLB, guionLB, bisLB, crearDireccionBT);
        Scene scene = new Scene(grid, 800, 300);
        scene.getStylesheets().add(getClass().getResource("/recursos/css/style.css").toExternalForm());

        primaryStage.show();
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();

    }

    public String concatenaDireccion() {//concatena los valores individuales de la direccion
        String direccion = "";
        String via = (String) direccionViaCB.getValue();

        switch (via) {
            case "Calle":
                direccion = "CL";
                break;
            case "Carrera":
                direccion = "KR";
                break;
            case "Diagonal":
                direccion = "DG";
                break;
            case "Transversal":
                direccion = "TV";
                break;
            case "Avenida":
                direccion = "AV";
                break;
            default:
                break;

        }
        direccion += direccionNumUnoTF.getText();

        try {
            if ((String) direccionBisCB.getValue() != null) {
                direccion += (String) direccionBisCB.getValue();
            }
        } catch (NullPointerException ex) {
            System.out.println(ex + "bis null");
        }

        direccion += direccionLetraUnoTF.getText();

        try {
            if ((String) direccionSectorCB.getValue() != null) {
                direccion += (String) direccionSectorCB.getValue();
            }
        } catch (NullPointerException ex) {
            System.out.println(ex + " sector null");
        }
        direccion += "#" + direccionNumDosTF.getText() + direccionLetraDosTF.getText() + "-" + direccionNumTresTF.getText();
        return direccion;

    }

    public void emptyText(TextField text, String error) {//campos vacios

        try {
            if (text == null || text.getText().isEmpty()) {
                throw new RuntimeException("Subclasses of Valueables cannot take in an empty String or null value for the \"name\" constructor");
            }
        } catch (RuntimeException exc) {//controla que no este vacio el campo
            errores.add("El campo " + error + " esta vacio");
            formularioLleno = false;
        }

    }

    public void emptyComboBox(ComboBox combo, String error) {//seleccion de un item del combo box
        try {
            if (combo == null || (String) combo.getValue() == null) {
                throw new RuntimeException("Subclasses of Valueables cannot take in an empty String or null value for the \"name\" constructor");
            }
        } catch (RuntimeException exc) {//controla que no este vacio el campo
            errores.add("seleccione " + error);
            formularioLleno = false;
        }
    }

    public void formatTextNumberNit(TextField text) {//solo numeros y el guion en el campo
        text.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d-*")) {
                text.setText(newValue.replaceAll("[^\\d-]", ""));
            }
        });
    }

    public void agregarFormatoCampos() {
        //se asegura que desde el inicio no puedan entrar valores numericos en los campos de texto

        formatText(nombreTF);
        formatText(ocupacionTF);
        formatText(nombreEmpresaTF);

        //se asegura que solo puedan ingresar numero, letras, #y el
        //se asegura que desde el inicio no puedan entrar valores numericos en los campos de texto
        formatTextNumber(documentoIdentidadTF);
        formatTextNumberNit(nitTF);
        formatTextNumber(numeroContactoTF);

    }

    private void validarCamposVacios() {
        //campos numericos
        emptyText(documentoIdentidadTF, "documento de identidad");//documento identidad
        emptyText(numeroContactoTF, "numero de contacto");//numero de contacto
        emptyText(nombreTF, "nombre completo");//nombre
        emptyText(ocupacionTF, "ocupacion");//numero de contacto
        //   emptyText(direccionTF, "la direccion");//direccion

        if (empresaRB.isSelected()) {//para los campos que se activan para empresa
            emptyText(nitTF, "nit");//nit
            emptyText(nombreEmpresaTF, "nombre de la empresa");//nombre empresa
            emptyText(direccionEmpresaTF, "la direccion de la empresa");//direccion empresa
            emptyComboBox(sectorComercialCB, " un sector comercial");
        }
    }

    public void validarCamposDireccion() {
        emptyText(direccionNumUnoTF, "llene el campo de numero");
        emptyText(direccionNumDosTF, "llene el campo de numero");
        emptyText(direccionNumTresTF, "llene el campo de numero");
        emptyComboBox(direccionViaCB, "seleccione una via");//solo la via es obligatoria
    }

    @FXML
    private void guardar(ActionEvent event) {
        JsonNaturalPersonDAO nuevaPersona = new JsonNaturalPersonDAO();
        JsonCompanyDAO nuevaEmpresa = new JsonCompanyDAO();

        validarCamposVacios();
        ArrayList<String> accounts = new ArrayList<String>();
        if (personaNaturalRB.isSelected() && formularioLleno == true) {
            personaNatural = new NaturalPerson(
                    documentoIdentidadTF.getText(),
                    numeroContactoTF.getText(),
                    nombreTF.getText(),
                    ocupacionTF.getText(),
                    direccionTF.getText(),
                    accounts//siempre que se registre un nuevo usuario se crea el campo cuentas pero vacio, el modulo 1 se encarga de asiganarle valores

            );
            nuevaPersona.insertar(personaNatural);//nuevo registro en la base de datos
            mensaje("Alerta", "Registro Exitoso", "Presione ok para\nvolver al menu principal").showAndWait().ifPresent(response -> {//siempre y cuando el boton del mensaje de registro exitoso sea presionado
                if (response == ButtonType.OK) {
                    abrirVentanaYCerrar(event, "/Vista/MenuWindow");
                }
            });

            System.out.println("registro exitoso");

        }
        if (empresaRB.isSelected() && formularioLleno == true) {
            empresa = new Company(//
                    nitTF.getText(),
                    nombreEmpresaTF.getText(),
                    (String) sectorComercialCB.getValue(),
                    direccionEmpresaTF.getText(),
                    documentoIdentidadTF.getText(),
                    numeroContactoTF.getText(),
                    nombreTF.getText(),
                    ocupacionTF.getText(),
                    direccionTF.getText(),
                    accounts//siempre que se registre un nuevo usuario se crea el campo cuentas pero vacio, el modulo 1 se encarga de asiganarle valores
            );
            nuevaEmpresa.insertar(empresa);//nuevo registro en la base de datos
            mensaje("Alerta", "Registro Exitoso", "Presione ok para\nvolver al menu principal").showAndWait().ifPresent(response -> {//siempre y cuando el boton del mensaje de registro exitoso sea presionado
                if (response == ButtonType.OK) {
                    abrirVentanaYCerrar(event, "/Vista/MenuWindow");
                }
            });
            System.out.println("registro exitoso");

        }

        errores();

    }

    public void errores() {
        if (errores.size() > 0) {
            String cadenaErrores = "";
            for (int i = 0; i < errores.size(); i++) {
                cadenaErrores += errores.get(i) + "\n";
            }
            mensaje("error ", "Para diligenciar exitosamente este formulario se requiere:",cadenaErrores).show();
            errores.clear();
            formularioLleno = false;
            return;
        }
    }

    @FXML
    public void abrirMenuPrincipal(ActionEvent event) {
         mensaje("Alerta", "Canelar registro","Esta seguro que quiere volver al menu principal").showAndWait().ifPresent(response -> {//siempre y cuando el boton del mensaje  sea presionado
                if (response == ButtonType.OK) {
                  abrirVentanaYCerrar(event,"/Vista/MenuWindow"); 
                }
            }); 

    }

    public void resetClientForm() {//reset el formulario general(el de perosna natural)
        documentoIdentidadTF.setText("");
        nombreTF.setText("");
        direccionTF.setText("");
        numeroContactoTF.setText("");
        ocupacionTF.setText("");

    }

    public void resetEmpresaForm() {//Reset el formulario en los campos de empresa
        nitTF.setText("");
        nombreEmpresaTF.setText("");
        sectorComercialCB.setValue(null);
        direccionEmpresaTF.setText("");
    }

    @FXML
    private void limpiar(ActionEvent event) {//limpia el formulario segun sea el caso
        System.out.println("borro");
        if (personaNaturalRB.isSelected()) {
            resetClientForm();
        }
        if (empresaRB.isSelected()) {
            resetClientForm();
            resetEmpresaForm();
        }
        formularioLleno = false;
    }

}

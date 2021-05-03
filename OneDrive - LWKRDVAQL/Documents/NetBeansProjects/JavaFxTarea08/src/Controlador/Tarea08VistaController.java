/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.EventoCovid;
import Modelo.Pasaporte;
import Modelo.PruebaContagio;
import Modelo.TipoPrueba;
import Modelo.Usuario;
import Modelo.Vacunacion;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.LinkedList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.converter.LocalDateStringConverter;

/**
 * FXML Controller class
 *
 * @author chenc
 */
public class Tarea08VistaController implements Initializable {

    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tfApellidos;
    @FXML
    private TextField tfDni;
    @FXML
    private DatePicker dpFechaNacimiento;
    @FXML
    private ComboBox<String> cbGrupo;
    @FXML
    private RadioButton rbHombre;
    @FXML
    private ToggleGroup tgSexo;
    @FXML
    private RadioButton rbMujer;
    @FXML
    private Button btnInsertarUsuario;
    @FXML
    private Button btnLimpiarDatosUsuario;
    String fecha;
    DateTimeFormatter formatter;
    @FXML
    private Button btnModificarUsuario;
    @FXML
    private Button btnEliminarUsuario;

    //TABLEVIEW
    @FXML
    private TableView<Usuario> tvUsuarios;
    //Creamos una observablelist que almacene los datos del objeto Usuario
    private ObservableList<Usuario> datosUsuario;
    @FXML
    private Pane paneUsuarios;
    @FXML
    private TableColumn<Usuario, String> colNombre;
    @FXML
    private TableColumn<Usuario, String> colApellidos;
    @FXML
    private TableColumn<Usuario, String> colDNI;
    @FXML
    private TableColumn<Usuario, String> colFechaNacimiento;
    @FXML
    private TableColumn<Usuario, String> colGrupo;
    @FXML
    private TableColumn<Usuario, String> colSexo;
    @FXML
    private Pane paneDatosEventos;
    @FXML
    private RadioButton rbPrueba;
    @FXML
    private RadioButton rbVacunacion;
    @FXML
    private DatePicker dpFechaEvento;
    @FXML
    private Pane panePrueba;
    @FXML
    private ComboBox<TipoPrueba> cbTipoPrueba;
    @FXML
    private RadioButton rbPositivo;
    @FXML
    private ToggleGroup tgResultado;
    @FXML
    private RadioButton rbNegativo;
    @FXML
    private Pane paneVacuna;
    @FXML
    private ComboBox<String> cbNombreVacuna;
    @FXML
    private TextField tfNumeroDosis;
    @FXML
    private Button btnLimpiarEvento;
    @FXML
    private Button btnInsertarEvento;
    @FXML
    private Pane paneEventos;
    //LISTVIEW
    @FXML
    private ListView<EventoCovid> lvEventosCovid;
    private ObservableList<EventoCovid> itemEventosCovid;
    @FXML
    private Button btnEliminarEvento;
    @FXML
    private Button btnBorrarTodo;
    @FXML
    private ToggleGroup tgTipoEvento;
    @FXML
    private Pane paneReporte;
    //TREEVIEW
    @FXML
    private TreeView<String> treevReporte;
    //Declaramos el nodo raiz
    private TreeItem<String> root;

    @FXML
    private Button btnReporte;

    Pasaporte pasaporte;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Cargo ComboBox
        cbGrupo.getItems().addAll("Sanidad", "Seguridad", "Educación", "Jubilados", "Riesgo");
        cbGrupo.setPromptText("Elige una opción");

        cbNombreVacuna.getItems().addAll("Astrazeneca", "Janssen", "Pfizer", "Moderna");
        cbNombreVacuna.setPromptText("Seleccione");

        //COMBOBOX ENUM
        cbTipoPrueba.getItems().setAll(TipoPrueba.values());
        cbTipoPrueba.setPromptText("Seleccione");

        //ToggleGroup
        //dejamos marcada una opcion por defecto para evitar errores de formularios vacios
        rbHombre.setSelected(true);
        rbNegativo.setSelected(true);
        //rbPrueba.setSelected(true);

        //DATEPICKER
        // dpFechaNacimiento.setEditable(false);
        //dpFechaNacimiento.setConverter(new LocalDateStringConverter(FormatStyle.SHORT));
        dpFechaEvento.setValue(LocalDate.now());
        dpFechaEvento.setEditable(false);
        dpFechaEvento.setConverter(new LocalDateStringConverter(FormatStyle.SHORT));

        //Modifica el formato en el que se muestra la fecha
        formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        dpFechaEvento.setConverter(new LocalDateStringConverter(formatter, null));
        dpFechaNacimiento.setConverter(new LocalDateStringConverter(formatter, null));

        //************* TABLEVIEW******************
        //Asociamos el tableview con su observablelist
        datosUsuario = FXCollections.observableArrayList();

        //Asociamos las columnas de la tableview con los atributos del objeto Usuario
        this.colNombre.setCellValueFactory(new PropertyValueFactory<Usuario, String>("nombre"));
        this.colApellidos.setCellValueFactory(new PropertyValueFactory<Usuario, String>("apellidos"));
        this.colDNI.setCellValueFactory(new PropertyValueFactory<Usuario, String>("dni"));
        this.colFechaNacimiento.setCellValueFactory(new PropertyValueFactory<Usuario, String>("fechaNacimiento"));
        this.colGrupo.setCellValueFactory(new PropertyValueFactory<Usuario, String>("grupo"));
        this.colSexo.setCellValueFactory(new PropertyValueFactory<Usuario, String>("sexo"));

        tvUsuarios.setItems(datosUsuario);

        //*************LISTVIEW******************************
        //Asociamos la ListView con su Observablelist
        itemEventosCovid = FXCollections.observableArrayList();
        lvEventosCovid.setItems(itemEventosCovid);

        //*************TREEVIEW****************************
        //instanciamos el nodo raiz
        root = new TreeItem<>("Pasaporte Covid");
        //Asociamos la raiz al Treeview
        treevReporte.setRoot(root);

        //PANE
        paneUsuarios.setDisable(true);
        paneDatosEventos.setDisable(true);
        paneEventos.setDisable(true);
        paneVacuna.setDisable(true);
        panePrueba.setDisable(true);
        paneReporte.setDisable(true);
    }

    @FXML
    private void insertarUsuario(ActionEvent event) {
        Usuario usu = new Usuario();

        Pattern patron = Pattern.compile("[0-9]{7,8}[A-Z a-z]");

        Matcher matcher = patron.matcher(tfDni.getText());

        //Datapicker
        fecha = dpFechaNacimiento.getValue().format(formatter);
        if (tfNombre.getText().isEmpty()) {//Validamos campo nombre

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Campo vacío o con errores");
            alert.setContentText("El campo NOMBRE no puede estar vacío");
            alert.showAndWait();
        } else if (tfApellidos.getText().isEmpty()) {// Validamos campo Apellidos

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Campo vacío o con errores");
            alert.setContentText("El campo APELLIDOS está vacio");
            alert.showAndWait();

        } else if (tfDni.getText().isEmpty()) {//Validamos que el campo DNI no esté vacío
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Campo vacío o con errores");
            alert.setContentText("El campo DNI está vacio");
            alert.showAndWait();

        } else if (!matcher.matches()) {//Validamos el formato del campo DNI
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Campo vacío o con errores");
            alert.setContentText("El campo DNI no contiene un formato válido \nDebe contener 8 números y una letra");
            alert.showAndWait();

        } else if (!validarDNI(tfDni.getText())) {//Validamos si la letra se corresponde con la secuencia de digitos

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Campo vacío o con errores");
            alert.setContentText("La letra no se corresponde con la secuencia de digitos");
            alert.showAndWait();

        } else if (dpFechaNacimiento.getValue() == null) {//Validamos campo FECHA NACIMIENTO

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Campo vacío o con errores");
            alert.setContentText("El campo FECHA NACIMIENTO está vacio");
            alert.showAndWait();
        } else if (cbGrupo.getSelectionModel().getSelectedIndex() == -1) {//Validamos campo GRUPO

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Campo vacío o con errores");
            alert.setContentText("No has elegido un GRUPO");
            alert.showAndWait();

        } else { //Si llegamos hasta aquí es que todo ha ido bien

            usu.setNombre(tfNombre.getText());
            usu.setApellidos(tfApellidos.getText());
            usu.setDni(tfDni.getText());
            usu.setFechaNacimiento(dpFechaNacimiento.getValue());
            usu.setGrupo(cbGrupo.getSelectionModel().getSelectedItem());
            //Sexo
            RadioButton rbAux = (RadioButton) tgSexo.getSelectedToggle();
            usu.setSexo(rbAux.getText());

            /*  Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Información");
            //alert.setHeaderText("Pasaporte creado con éxito");
            //alert.setContentText("Se ha creado al usuario con la información: " + "\n" + usu.toString());
            alert.setContentText("Usuario añadido correctamente");
            alert.showAndWait();*/
            //Nos aseguramos que el usuario no existe ya
            if (!this.datosUsuario.contains(usu)) {
                this.datosUsuario.add(usu);
                this.tvUsuarios.setItems(datosUsuario);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Información");
                alert.setContentText("Usuario añadido correctamente");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                //alert.setHeaderText("Pasaporte creado con éxito");
                //alert.setContentText("Se ha creado al usuario con la información: " + "\n" + usu.toString());
                alert.setContentText("El Usuario ya existe");
                alert.showAndWait();

            }
            limpiarDatosUsuario();
            paneUsuarios.setDisable(false);
        }
    }

    @FXML
    private void limpiarDatosUsuario() {
        tfNombre.setText(null);
        tfApellidos.setText(null);
        tfDni.setText(null);
        dpFechaNacimiento.setValue(null);
        cbGrupo.getSelectionModel().select(null);
        //cbGrupo.getSelectionModel().clearSelection();
        //tgSexo.selectToggle(null);

    }

    @FXML
    private void seleccionar(MouseEvent event) {
        // Obtengo el usuario seleccionado
        Usuario usu = this.tvUsuarios.getSelectionModel().getSelectedItem();

        // Si no es nulo seteo los campos
        if (usu != null) {
            this.tfNombre.setText(usu.getNombre());
            this.tfApellidos.setText(usu.getApellidos());
            this.tfDni.setText(usu.getDni());
            this.dpFechaNacimiento.setValue(usu.getFechaNacimiento());
            this.cbGrupo.getSelectionModel().select(usu.getGrupo());

            if (usu.getSexo().equals("Hombre")) {
                rbHombre.setSelected(true);
            } else {
                rbMujer.setSelected(true);
            }
            for (Usuario usuario : datosUsuario) {

            }
            paneDatosEventos.setDisable(false);
            actualizaLista(usu);
        }
    }

    public void actualizaLista(Usuario usu) {

        itemEventosCovid.clear();

        if (tvUsuarios.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("Debe seleccionar un usuario");
            alert.showAndWait();
        } else {
            LinkedList<EventoCovid> listaEventos = usu.getListaEventos();
            if (listaEventos.isEmpty()) {
                paneEventos.setDisable(true);
            } else {
                paneEventos.setDisable(false);
            }
            for (EventoCovid e : listaEventos) {
                itemEventosCovid.add(e);
            }
        }

    }

    @FXML
    private void modificarUsuario(ActionEvent event) {
        // Obtengo el usuario seleccionado
        Usuario usu = this.tvUsuarios.getSelectionModel().getSelectedItem();

        // Si el usuario es nulo, lanzo error
        if (usu == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Debe seleccionar un usuario");
            alert.showAndWait();
        } else {

            try {
                // Obtengo los datos del formulario
                String nombre = this.tfNombre.getText();
                String apellidos = this.tfApellidos.getText();
                String dni = this.tfDni.getText();
                LocalDate fechaNacimiento = this.dpFechaNacimiento.getValue();
                String grupo = this.cbGrupo.getSelectionModel().getSelectedItem();
                RadioButton rbAux = (RadioButton) this.tgSexo.getSelectedToggle();
                String sexo = rbAux.getText();

                // Creo un usuario
                Usuario aux = new Usuario(nombre, apellidos, dni, fechaNacimiento, grupo, sexo);

                // Compruebo si el usuario está en la lista
                if (!this.datosUsuario.contains(aux)) {

                    // Modifico el objeto
                    usu.setNombre(aux.getNombre());
                    usu.setApellidos(aux.getApellidos());
                    usu.setDni(aux.getDni());
                    usu.setFechaNacimiento(aux.getFechaNacimiento());
                    usu.setGrupo(aux.getGrupo());
                    usu.setSexo(aux.getSexo());

                    // Refresco la tabla
                    this.tvUsuarios.refresh();

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setTitle("Info");
                    alert.setContentText("Usuario modificado");
                    alert.showAndWait();

                } else {

                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("Error");
                    alert.setContentText("El usuario ya exite");
                    alert.showAndWait();
                }
            } catch (NumberFormatException e) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("Formato incorrecto");
                alert.showAndWait();
            }

        }
    }

    @FXML
    private void eliminarUsuario(ActionEvent event) {
        // Obtengo la persona seleccionada
        Usuario usu = this.tvUsuarios.getSelectionModel().getSelectedItem();

        // Si la persona es nula, lanzo error
        if (usu == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Debes seleccionar un Usuario a borrar");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmacion eliminar");
            //alert.setHeaderText("Look, a Confirmation Dialog");
            alert.setContentText("¿Está seguro que desea eliminar este Usuario?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                // Lo elimino de la lista
                this.datosUsuario.remove(usu);
                // Refresco la lista
                this.tvUsuarios.refresh();
            } else {
                ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
            }
            if (datosUsuario.isEmpty()) {
                paneUsuarios.setDisable(true);
                paneDatosEventos.setDisable(true);
                itemEventosCovid.clear();
                paneEventos.setDisable(true);
                limpiarEvento();
                limpiarDatosUsuario();
            }
        }
    }

    private boolean validarDNI(String dni) {
        char letra;
        switch (Integer.parseInt(dni.substring(0, 8)) % 23) {
            case 0 ->
                letra = 'T';
            case 1 ->
                letra = 'R';
            case 2 ->
                letra = 'W';
            case 3 ->
                letra = 'A';
            case 4 ->
                letra = 'G';
            case 5 ->
                letra = 'M';
            case 6 ->
                letra = 'Y';
            case 7 ->
                letra = 'F';
            case 8 ->
                letra = 'P';
            case 9 ->
                letra = 'D';
            case 10 ->
                letra = 'X';
            case 11 ->
                letra = 'B';
            case 12 ->
                letra = 'N';
            case 13 ->
                letra = 'J';
            case 14 ->
                letra = 'Z';
            case 15 ->
                letra = 'S';
            case 16 ->
                letra = 'Q';
            case 17 ->
                letra = 'V';
            case 18 ->
                letra = 'H';
            case 19 ->
                letra = 'L';
            case 20 ->
                letra = 'C';
            case 21 ->
                letra = 'K';
            default ->
                letra = 'E';
        }
        return (dni.toUpperCase().charAt(8) == letra);
    }

    @FXML
    private void limpiarEvento() {
        //tgtipoEvento.selectToggle(null);
        dpFechaEvento.setValue(null);
        //tgResultado.selectToggle(null);
        cbTipoPrueba.getSelectionModel().select(null);
        tfNumeroDosis.setText(null);
        cbNombreVacuna.getSelectionModel().select(null);
    }

    @FXML
    private void insertarEvento(ActionEvent event) {
        if (rbVacunacion.isSelected()) {

            EventoCovid evento;
            //*******************CREAMOS UN OBJETO VACUNACION*********************
            Vacunacion vacu = new Vacunacion();
            //Datapicker
            fecha = dpFechaEvento.getValue().format(formatter);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            if (!rbPrueba.isSelected() && !rbVacunacion.isSelected()) {//Validamos radioButtons

                alert.setTitle("ERROR");
                alert.setHeaderText("Campo vacío o con errores");
                alert.setContentText("Debe elegir un TIPO DE EVENTO");
                alert.showAndWait();

            } else if (tfNumeroDosis.getText().isEmpty()) {//Validamos campo Numero de dosis

                alert.setTitle("ERROR");
                alert.setHeaderText("Campo vacío o con errores");
                alert.setContentText("El campo NUMERO DOSIS está vacio");
                alert.showAndWait();

            } else if (dpFechaEvento.getValue() == null) {//Validamos campo FECHA EVENTO
                alert.setTitle("ERROR");
                alert.setHeaderText("Campo vacío o con errores");
                alert.setContentText("El campo FECHA EVENTO está vacio");
                alert.showAndWait();

            } else if (cbNombreVacuna.getSelectionModel().getSelectedIndex() == -1) {//Validamos campo Nombre Vacuna

                alert.setTitle("ERROR");
                alert.setHeaderText("Campo vacío o con errores");
                alert.setContentText("El campo NOMBRE VACUNA está vacio");
                alert.showAndWait();
            } else // Si llegamos hasta aqui, todo ha ido bien
            {
                vacu.setNombreVacuna(cbNombreVacuna.getSelectionModel().getSelectedItem());
                vacu.setFecha(dpFechaEvento.getValue());
                vacu.setNumDosis(Integer.parseInt(tfNumeroDosis.getText()));
                alert.setTitle("INFORMACION");
                //alert.setHeaderText("Campo vacío o con errores");
                alert.setContentText("El Evento ha sido creado con éxito");
                alert.showAndWait();

                //************* LISTVIEW******************
                Usuario usu = this.tvUsuarios.getSelectionModel().getSelectedItem();
                itemEventosCovid.add(vacu);
                //usu.getListaEventos().add(vacu);
                usu.añadirEventoCovid(vacu);
                paneEventos.setDisable(false);
                paneReporte.setDisable(false);

            }

        } else {
            //**********CREAMOS EL OBJETO PRUEBA CONTAGIO************************          
            PruebaContagio pruebaCon = new PruebaContagio();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            if (!rbPrueba.isSelected() && !rbVacunacion.isSelected()) {//Validamos radioButtons

                alert.setTitle("ERROR");
                alert.setHeaderText("Campo vacío o con errores");
                alert.setContentText("Debe elegir un TIPO DE EVENTO");
                alert.showAndWait();

            } else if (cbTipoPrueba.getSelectionModel().getSelectedIndex() == -1) {//Validamos campo Tipo de Prueba

                alert.setTitle("ERROR");
                alert.setHeaderText("Campo vacío o con errores");
                alert.setContentText("El campo TIPO PRUEBA no puede estar vacío");
                alert.showAndWait();

            } else if (dpFechaEvento.getValue() == null) {//Validamos campo FECHA EVENTO

                alert.setTitle("ERROR");
                alert.setHeaderText("Campo vacío o con errores");
                alert.setContentText("El campo FECHA EVENTO está vacio");
                alert.showAndWait();

            } else {//Si llegamos hasta aqui es que todo ha ido bien

                pruebaCon.setPrueba(cbTipoPrueba.getSelectionModel().getSelectedItem());
                pruebaCon.setFecha(dpFechaEvento.getValue());

                //Si RBPosistivo está seleccionado será true y vice
                boolean positivo = rbPositivo.isSelected();
                //RadioButton selectedRadioButton = (RadioButton) tgResultado.getSelectedToggle ();
                pruebaCon.setPositivo(positivo);

                //**********TABLEVIEW**********************
                Usuario usu = this.tvUsuarios.getSelectionModel().getSelectedItem();
                itemEventosCovid.add(pruebaCon);
                //usu.getListaEventos().add(pruebaCon);
                usu.añadirEventoCovid(pruebaCon);
                paneEventos.setDisable(false);
                paneReporte.setDisable(false);

            }

        }
    }

    @FXML
    private void eliminarEvento(ActionEvent event) {
        int i = lvEventosCovid.getSelectionModel().getSelectedIndex();

        // Obtengo la persona seleccionada
        // Si la persona es nula, lanzo error
        if (i == -1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Debes seleccionar un Evento");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmacion eliminar");
            //alert.setHeaderText("Look, a Confirmation Dialog");
            alert.setContentText("¿Está seguro que desea eliminar este Evento?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                // Guardo el evento temporalmente antes de eliminarlo
                EventoCovid evento = itemEventosCovid.get(i);
                //Eliminamos el evento de la ObservableList
                this.itemEventosCovid.remove(i);
                //CArgamos el Usuario seleccionado
                Usuario usu = this.tvUsuarios.getSelectionModel().getSelectedItem();
                //Eliminamos el usuario de la lista del usuario
                usu.eliminarEventoCovid(evento);
                // Refresco la lista
                this.lvEventosCovid.refresh();
            } else {
                ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
            }

        }
    }

    @FXML
    private void borrarTodo(ActionEvent event) {
        itemEventosCovid.clear();
    }

    @FXML
    private void hidePane(MouseEvent event) {
        //Habilito/deshabilito opciones Vacuna/Prueba 
        if (rbVacunacion.isSelected()) {
            panePrueba.setDisable(true);
            paneVacuna.setDisable(false);
        } else {
            panePrueba.setDisable(false);
            paneVacuna.setDisable(true);

        }
    }

    @FXML
    private void reporte(ActionEvent event) {
        Pasaporte pasaporte = new Pasaporte();
        
        Usuario usu = this.tvUsuarios.getSelectionModel().getSelectedItem();
        TreeItem<String> nodo = new TreeItem<>(usu.toString());
       // TreeItem<String> nodo = new TreeItem<>(pasaporte.getUsuario().toString());
        
        root.getChildren().add(nodo);
        
        for (EventoCovid e : pasaporte.getListaEventosCovid()) {
            nodo.getChildren().add(new TreeItem<>(e.toString()));
        }

    }
}

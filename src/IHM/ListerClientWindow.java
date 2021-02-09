/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import Entities.Client;
import Handler.ListerClientHandler;
import java.time.LocalDate;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 *
 * @author COZMET
 */
public class ListerClientWindow {
    Stage window = new Stage();
    VBox root = new VBox();
    Scene scene = new Scene(root);
    ListerClientHandler handler = new ListerClientHandler(this);
    Label titreLabel = new Label("liste des Client");
    VBox titreVBox = new VBox();
    TableView<Client> clientTableView = new TableView<>();
    TableColumn<Client, String> nomTableColumn = new TableColumn<>("nom");
    TableColumn<Client, String> prenomTableColumn = new TableColumn<>("prenom");
    TableColumn<Client, String> teleTableColumn = new TableColumn<>("telephone");
    TableColumn<Client, String> emailTableColumn = new TableColumn<>("email");
    TableColumn<Client, String> adrTableColumn = new TableColumn<>("adresse");
    TableColumn<Client, LocalDate> dateTableColumn = new TableColumn<>("date");

    public Stage getWindow() {
        return window;
    }

    public TableView<Client> getClientTableView() {
        return clientTableView;
    }
    
    public ListerClientWindow(){
        setupWindow();
        setStyleSheet();
        addWidgetToWindow();
        eventHandler();
        handler.updateClientTableView();
        window.show();
    }

    //creation d'un instructeur privé pour par la suite l'appeler dans une methode statique
    //ce constructeur se difére du constructeur normal pas la fonction setFunctionToEventTableRow
    //set methode prend une fontion et la definir comme methode d'evenement du rowFactory
    //<p, r>: p les paramettre, r le return
    public ListerClientWindow(Callback<Client, Client> Function){
        setupWindow();
        setStyleSheet();
        addWidgetToWindow();
        eventHandler();
        handler.updateClientTableView();
        handler.setFunctionToEventTableRow(Function);
        window.show();
    }
    
    private void setupWindow() {
        window.setTitle("liste des produis");
        window.setWidth(800);
        window.setHeight(600);
        window.setScene(scene);
        window.initModality(Modality.APPLICATION_MODAL);
    }

    private void setStyleSheet() {
        scene.getStylesheets().add("css/main.css");
        root.getStyleClass().add("body");
        titreVBox.getStyleClass().add("titreBox");
        titreLabel.getStyleClass().add("titreLabel");
        clientTableView.getStyleClass().add("tableView");
    }

    private void addWidgetToWindow() {
        root.getChildren().addAll(titreVBox,clientTableView);
        titreVBox.getChildren().add(titreLabel);
        setupClientTableView();
    }

    private void eventHandler(){
    }
    
    private void setupClientTableView() {
    
        nomTableColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("nom"));
        prenomTableColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("prenom"));
        teleTableColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("tele"));
        emailTableColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("email"));
        adrTableColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("addr"));
        dateTableColumn.setCellValueFactory(new PropertyValueFactory<Client, LocalDate>("date"));
        
        clientTableView.getColumns().addAll(nomTableColumn, prenomTableColumn, 
                teleTableColumn, emailTableColumn, adrTableColumn, dateTableColumn);
    }
    
    //la methode static pour appeler le constructeur privé
    //prend en argument la fonction qui sera appeler dans eventement rowFactory dans row
    //<p, r>: p les paramettre, r le return
    public static ListerClientWindow selectionListeClient(Callback<Client, Client> Function){
        ListerClientWindow listerClient = new ListerClientWindow(Function);
        return listerClient;
    }
}

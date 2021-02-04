/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import Entities.Client;
import Handler.RechercherClientHandler;
import java.time.LocalDate;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author COZMET
 */
public class RechercherClientFormWindow {
    Stage window = new Stage();
    VBox root = new VBox();
    Scene scene = new Scene(root);
    RechercherClientHandler handler = new RechercherClientHandler(this);
    HBox hContainerRechercherClientHBox = new HBox();
    Label rechercherClientLabel = new Label("Nom :");
    TextField rechercherClientTextField = new TextField();
    Button rechercherClientButton = new Button("rechercher");
    TableView<Client> rechercheClientTableView = new TableView<>();
    TableColumn<Client, String> nomTableColumn = new TableColumn<>("nom");
    TableColumn<Client, String> prenomTableColumn = new TableColumn<>("prenom");
    TableColumn<Client, String> teleTableColumn = new TableColumn<>("telephone");
    TableColumn<Client, String> emailTableColumn = new TableColumn<>("email");
    TableColumn<Client, String> adresseTableColumn = new TableColumn<>("adresse");
    TableColumn<Client, LocalDate> dateTableColumn = new TableColumn<>("date");

    public TextField getRechercherClientTextField() {
        return rechercherClientTextField;
    }

    public TableView<Client> getRechercheClientTableView() {
        return rechercheClientTableView;
    }
    
    public RechercherClientFormWindow(){
        setupWindow();
        addWidgetToWindow();
        setStyleSheet();
        eventHandler();
        window.show();
    }
    
    private void setupWindow(){
        window.setTitle("Rechercher client");
        window.setWidth(800);
        window.setHeight(600);
        window.setScene(scene);
        window.initModality(Modality.APPLICATION_MODAL);
    }
    
    private void addWidgetToWindow(){
        root.getChildren().addAll(hContainerRechercherClientHBox, rechercheClientTableView);
        hContainerRechercherClientHBox.getChildren().addAll(rechercherClientLabel, rechercherClientTextField, rechercherClientButton);
        setupRechercheClientTableView();
    }
    
    private void setupRechercheClientTableView(){
        nomTableColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("nom")) ;
        prenomTableColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("prenom")) ;
        teleTableColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("tele")) ;
        emailTableColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("email")) ;
        adresseTableColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("addr")) ;
        dateTableColumn.setCellValueFactory(new PropertyValueFactory<Client, LocalDate>("date")) ;
        
        rechercheClientTableView.getColumns().addAll(nomTableColumn,prenomTableColumn,teleTableColumn,emailTableColumn,adresseTableColumn,dateTableColumn);
        
    }
    
    private void setStyleSheet(){
        scene.getStylesheets().add("css/main.css");
        root.getStyleClass().add("body");
        rechercherClientLabel.getStyleClass().add("inputLabel");
        rechercherClientTextField.getStyleClass().add("input");
        rechercherClientButton.getStyleClass().add("btn");
        hContainerRechercherClientHBox.setPadding(new Insets(10));
        hContainerRechercherClientHBox.setSpacing(10);
    }
    
    private void eventHandler(){
        rechercherClientButton.setOnAction(event->{
            handler.findClient();
        });
        rechercheClientTableView.setRowFactory(event->{
            TableRow<Client> row = new TableRow<>();
            row.setOnMouseClicked(e->{
                if(row.getItem()!=null)
                handler.askUpdateOrDelete(row.getItem());
            });
            return row;
        });
    }
}

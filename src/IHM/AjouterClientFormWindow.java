/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import Handler.AjouterClientHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author COZMET
 */
public class AjouterClientFormWindow {
    Stage window = new Stage();
    VBox root = new VBox();
    Scene scene = new Scene(root);
    AjouterClientHandler handler = new AjouterClientHandler(this);
    VBox titreVBox = new VBox();
    Label titreLabel = new Label("Ajouter client");
    Label nomLabel = new Label("Nom :");
    TextField nomTextField = new TextField();
    Label prenomLabel = new Label("Prenom :");
    TextField prenomTextField = new TextField();
    Label teleLabel = new Label("Téléphone :");
    TextField teleTextField = new TextField();
    Label emailLabel = new Label("Email :");
    TextField emailTextField = new TextField();
    Label adrLabel = new Label("Adresse :");
    TextField adrTextField = new TextField();
    Label dateLabel = new Label("Date :");
    DatePicker dateDatePicker = new DatePicker();
    HBox hContainerHBox = new HBox();
    Button ajouterClientButton = new Button("Ajouter");
    Button annulerClientButton = new Button("Annuler");
    
    public AjouterClientFormWindow(){
        setupWindow();
        setStyleSheet();
        addWidgetToWindow();
        eventHandler();
        window.show();
    }

    public TextField getNomTextField() {
        return nomTextField;
    }

    public TextField getPrenomTextField() {
        return prenomTextField;
    }

    public TextField getTeleTextField() {
        return teleTextField;
    }

    public TextField getEmailTextField() {
        return emailTextField;
    }

    public TextField getAdrTextField() {
        return adrTextField;
    }

    public DatePicker getDateDatePicker() {
        return dateDatePicker;
    }
    
    private void setupWindow(){
        window.setTitle("Ajouter client");
        window.setWidth(800);
        window.setHeight(600);
        window.setScene(scene);
        window.initModality(Modality.APPLICATION_MODAL);
    }
    private void setStyleSheet(){
        scene.getStylesheets().add("css/main.css");
        root.getStyleClass().add("body");
        
        titreVBox.getStyleClass().add("titreBox");
        titreLabel.getStyleClass().add("titreLabel");
        
        nomLabel.getStyleClass().add("inputLabel");
        prenomLabel.getStyleClass().add("inputLabel");
        teleLabel.getStyleClass().add("inputLabel");
        emailLabel.getStyleClass().add("inputLabel");
        adrLabel.getStyleClass().add("inputLabel");
        dateLabel.getStyleClass().add("inputLabel");
        ajouterClientButton.getStyleClass().add("btn");
        annulerClientButton.getStyleClass().add("btn");
        
        root.setSpacing(10);
        root.setPadding(new Insets(10));
        hContainerHBox.setSpacing(20);
    }
    private void eventHandler(){
        window.setOnCloseRequest(event->{
            event.consume();
        });
        ajouterClientButton.setOnAction(event->{
            handler.addClient();
        });
        annulerClientButton.setOnAction(event->{
            window.close();
        });
    }
    private void addWidgetToWindow(){
        root.getChildren().add(titreVBox);
        root.getChildren().addAll(nomLabel,nomTextField);
        root.getChildren().addAll(prenomLabel,prenomTextField);
        root.getChildren().addAll(teleLabel,teleTextField);
        root.getChildren().addAll(emailLabel,emailTextField);
        root.getChildren().addAll(adrLabel,adrTextField);
        root.getChildren().addAll(dateLabel,dateDatePicker);
        root.getChildren().add(hContainerHBox);
        titreVBox.getChildren().add(titreLabel);
        hContainerHBox.getChildren().addAll(ajouterClientButton, annulerClientButton);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import Entities.Produit;
import Handler.AjouterProduitHandler;
import Handler.ModifierProduitHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
public class ModifierProduitFormWindow {
    Stage window = new Stage();
    VBox root = new VBox();
    Scene scene = new Scene(root);
    ModifierProduitHandler handler = new ModifierProduitHandler(this);
    Label titreLabel = new Label("Modifier produit");
    VBox titreVBox = new VBox();
    Label designationLabel = new Label("designation:");
    TextField designationTextField = new TextField();
    Label prixAchatLabel = new Label("prix achat:");
    TextField prixAchatTextField = new TextField();
    Label prixVenteLabel = new Label("prix vente:");
    TextField prixVenteTextField = new TextField();
    Label qteLabel = new Label("quantite:");
    TextField qteTextField = new TextField();
    Label categorieLabel = new Label("categorie");
    ComboBox categorieComboBox = new ComboBox();
    Label dateLabel = new Label("date:");
    DatePicker dateDatePicker = new DatePicker();
    HBox hContainer = new HBox();
    Button modifierProduitButton = new Button("Modifier");
    Button annulerProduitButton = new Button("Annuler");

    public TextField getDesignationTextField() {
        return designationTextField;
    }

    public TextField getPrixAchatTextField() {
        return prixAchatTextField;
    }

    public TextField getPrixVenteTextField() {
        return prixVenteTextField;
    }

    public TextField getQteTextField() {
        return qteTextField;
    }

    public DatePicker getDateDatePicker() {
        return dateDatePicker;
    }

    public ComboBox getCategorieComboBox() {
        return categorieComboBox;
    }

    public Stage getWindow() {
        return window;
    }

    public Button getModifierProduitButton() {
        return modifierProduitButton;
    }

    public ModifierProduitHandler getHandler() {
        return handler;
    }
    
    public ModifierProduitFormWindow(Produit p){
        setupWindow();
        addWidgetToWindow();
        eventHandler();
        setStyleSheet();
        handler.setValueToFields(p);
        window.show();
    }
    
    private void addWidgetToWindow(){
        root.getChildren().addAll(titreVBox);
        root.getChildren().addAll(designationLabel, designationTextField);
        root.getChildren().addAll(prixAchatLabel, prixAchatTextField);
        root.getChildren().addAll(prixVenteLabel, prixVenteTextField);
        root.getChildren().addAll(qteLabel, qteTextField);
        root.getChildren().addAll(categorieLabel, categorieComboBox);
        root.getChildren().addAll(dateLabel, dateDatePicker);
        root.getChildren().addAll(hContainer);
        titreVBox.getChildren().add(titreLabel);
        hContainer.getChildren().addAll(modifierProduitButton, annulerProduitButton);
    }
    
    private void eventHandler(){
        window.setOnCloseRequest(event->{
            event.consume();
        });
        modifierProduitButton.setOnAction(event->{
            handler.updateProduit();
        });
        annulerProduitButton.setOnAction(event->{
           window.close();
        });
        categorieComboBox.setOnMouseClicked(event->{
            handler.initCategorieComboBox();
        });
    }
    
    private void setStyleSheet(){
        scene.getStylesheets().add("css/main.css");
        root.getStyleClass().add("body");
        titreVBox.getStyleClass().add("titreBox");
        titreLabel.getStyleClass().add("titreLabel");
        designationLabel.getStyleClass().add("inputLabel");
        prixAchatLabel.getStyleClass().add("inputLabel");
        prixVenteLabel.getStyleClass().add("inputLabel");
        qteLabel.getStyleClass().add("inputLabel");
        categorieLabel.getStyleClass().add("inputLabel");
        dateLabel.getStyleClass().add("inputLabel");
        categorieComboBox.getStyleClass().add("comboBox");
        modifierProduitButton.getStyleClass().add("btn");
        annulerProduitButton.getStyleClass().add("btn");
        root.setSpacing(10);
        root.setPadding(new Insets(10));
        hContainer.setSpacing(20);
    }
    
    private void setupWindow(){
        window.setTitle("Modifier Produit");
        window.setWidth(800);
        window.setHeight(600);
        window.setScene(scene);
        window.initModality(Modality.APPLICATION_MODAL);
        
    }
    
}

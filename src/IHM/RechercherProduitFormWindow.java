/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import Entities.Produit;
import Handler.RechercherProduitHandler;
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
public class RechercherProduitFormWindow {
    Stage window = new Stage();
    VBox root = new VBox();
    Scene scene = new Scene(root);
    RechercherProduitHandler handler = new RechercherProduitHandler(this);
    HBox hContainerRechercherProduitHBox = new HBox();
    Label rechercherProduitLabel = new Label("Rechercher par designation :");
    Button rechercherProduitButton = new Button("Envoyer");
    TextField rechercherProduitTextField = new TextField();
    TableView<Produit> rechercheProduitTableView = new TableView<>();
    TableColumn<Produit, String> designationTableColumn = new TableColumn<>("designation");
    TableColumn<Produit, Float> prixAchatTableColumn = new TableColumn<>("prix achat");
    TableColumn<Produit, Float> prixVenteTableColumn = new TableColumn<>("prix vente");
    TableColumn<Produit, String> categorieTableColumn = new TableColumn<>("categorie");
    TableColumn<Produit, LocalDate> dateTableColumn = new TableColumn<>("date");
    TableColumn<Produit, Long> qteTableColumn = new TableColumn<>("quantité");

    public TextField getRechercherProduitTextField() {
        return rechercherProduitTextField;
    }

    public TableView<Produit> getRechercheProduitTableView() {
        return rechercheProduitTableView;
    }
    
    public RechercherProduitFormWindow(){
        setupWindow();
        setStyleSheet();
        addWidgetToWindow();
        setupRechercheProduitTableView();
        eventHandler();
        window.show();
    }
    
    private void setStyleSheet(){
        scene.getStylesheets().add("css/main.css");
        root.getStyleClass().add("body");
        rechercherProduitLabel.getStyleClass().add("inputLabel");
        rechercherProduitButton.getStyleClass().add("btn");
        rechercherProduitTextField.getStyleClass().add("input");
        hContainerRechercherProduitHBox.setPadding(new Insets(10));
        hContainerRechercherProduitHBox.setSpacing(10);
        
    }
    
    private void setupWindow(){
        window.setTitle("Rechercher Produit");
        window.setWidth(800);
        window.setHeight(600);
        window.setScene(scene);
        window.initModality(Modality.APPLICATION_MODAL);
        
    }
    private void setupRechercheProduitTableView(){
        designationTableColumn.setCellValueFactory(new PropertyValueFactory<Produit, String>("designation"));
        prixAchatTableColumn.setCellValueFactory(new PropertyValueFactory<Produit, Float>("prixAchat"));
        prixVenteTableColumn.setCellValueFactory(new PropertyValueFactory<Produit, Float>("prixVente"));
        categorieTableColumn.setCellValueFactory(new PropertyValueFactory<Produit, String>("categorie"));
        dateTableColumn.setCellValueFactory(new PropertyValueFactory<Produit, LocalDate>("date"));
        qteTableColumn.setCellValueFactory(new PropertyValueFactory<Produit, Long>("qte"));
        
        rechercheProduitTableView.getColumns().addAll(designationTableColumn, prixAchatTableColumn, 
                prixVenteTableColumn, categorieTableColumn, qteTableColumn, dateTableColumn);
    }
    
    private void eventHandler(){
        rechercherProduitButton.setOnAction(event->{
            handler.findProduit();
        });
        rechercheProduitTableView.setRowFactory(value->{
            TableRow<Produit> row = new TableRow<>();
            row.setOnMouseClicked(event->{
                handler.askUpdateOrDelete(row.getItem());
            });
            return row;
        });
    }
    
    private void addWidgetToWindow(){
        root.getChildren().addAll(hContainerRechercherProduitHBox, rechercheProduitTableView);
        hContainerRechercherProduitHBox.getChildren().addAll(rechercherProduitLabel, rechercherProduitTextField, rechercherProduitButton);
    }
}

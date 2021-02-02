/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import Entities.Produit;
import Handler.ListerProduitHandler;
import java.time.LocalDate;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author COZMET
 */
public class ListerProduitWindow {
    Stage window = new Stage();
    VBox root = new VBox();
    Scene scene = new Scene(root);
    ListerProduitHandler handler = new ListerProduitHandler(this);
    Label titreLabel = new Label("liste des produits");
    VBox titreVBox = new VBox();
    TableView<Produit> produitTableView = new TableView<>();
    TableColumn<Produit, String> designationTableColumn = new TableColumn<>("designation");
    TableColumn<Produit, Float> prixAchatTableColumn = new TableColumn<>("prix achat");
    TableColumn<Produit, Float> prixVenteTableColumn = new TableColumn<>("prix vente");
    TableColumn<Produit, String> categorieTableColumn = new TableColumn<>("categorie");
    TableColumn<Produit, LocalDate> dateTableColumn = new TableColumn<>("date");
    TableColumn<Produit, Long> qteTableColumn = new TableColumn<>("quantité");
    HBox hContainerTotal = new HBox();
    Label totalLabel = new Label("total: ");
    Label totalCalculLabel = new Label("0.0");
    
    public TableView<Produit> getProduitTableView() {
        return produitTableView;
    }

    public Label getTotalCalculLabel() {
        return totalCalculLabel;
    }
    
    private void setupWindow(){
        window.setTitle("liste des produis");
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
        produitTableView.getStyleClass().add("tableView");
        hContainerTotal.getStyleClass().add("titreBox");
        totalLabel.getStyleClass().add("titreLabel");
        totalCalculLabel.getStyleClass().add("titreLabel");
    }
    
    private void setupProduitTableView(){
        designationTableColumn.setCellValueFactory(new PropertyValueFactory<Produit, String>("designation"));
        prixAchatTableColumn.setCellValueFactory(new PropertyValueFactory<Produit, Float>("prixAchat"));
        prixVenteTableColumn.setCellValueFactory(new PropertyValueFactory<Produit, Float>("prixVente"));
        categorieTableColumn.setCellValueFactory(new PropertyValueFactory<Produit, String>("categorie"));
        dateTableColumn.setCellValueFactory(new PropertyValueFactory<Produit, LocalDate>("date"));
        qteTableColumn.setCellValueFactory(new PropertyValueFactory<Produit, Long>("qte"));
        
        produitTableView.getColumns().addAll(designationTableColumn, prixAchatTableColumn, 
                prixVenteTableColumn, categorieTableColumn, qteTableColumn, dateTableColumn);
    }
    
    private void addWidgetToWindow(){
        root.getChildren().addAll(titreVBox,produitTableView, hContainerTotal);
        titreVBox.getChildren().add(titreLabel);
        hContainerTotal.getChildren().addAll(totalLabel, totalCalculLabel);
        setupProduitTableView();
    }
    
    public ListerProduitWindow(){
        setupWindow();
        setStyleSheet();
        addWidgetToWindow();
        handler.updateProduitTableView();
        window.show();
    }
}

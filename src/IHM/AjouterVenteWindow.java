/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import Entities.LineCommande;
import Entities.Produit;
import java.time.LocalDate;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author COZMET
 */
public class AjouterVenteWindow {
    Stage window = new Stage();
    HBox root = new HBox();
    Scene scene = new Scene(root);
    VBox leftVBox = new VBox();
    VBox rightVBox = new VBox();
    VBox clientVBox = new VBox();
    VBox listeProduitsVBox = new VBox();
    VBox produitVBox = new VBox();
    VBox linesCommandeVBox = new VBox();
    //la partie de client
    Label clientLabel = new Label("client : ");
    ComboBox clientComboBox = new ComboBox();
    Button AjouterClientButton = new Button("Ajouter");
    Label nomLabel = new Label("Nom : ");
    Label nomClientLabel = new Label("NAIDR");
    Label prenomLabel = new Label("Prenom : ");
    Label prenomClientLabel = new Label("Yasser");
    Label teleLabel = new Label("Téléphone : ");
    Label teleClientLabel = new Label("0610065615");
    Label emailLabel = new Label("Email : ");
    Label emailClientLabel = new Label("yassernaidr761@gmail.com");
    Label adrLabel = new Label("Adresse : ");
    Label adrClientLabel = new Label("sidi momen casa");
    Label dateLabel = new Label("Date : ");
    DatePicker dateDatePicker = new DatePicker();
    //la partie de liste des produits
    HBox rechercherProduitHbox = new HBox(); 
    Label rechercherProuditLabel = new Label("Designation :");
    TextField rechercherProduitTextField = new TextField();
    Button rechercherProduitButton = new Button("Rechercher");
    TableView<Produit> listeProduitsTableView = new TableView<>();
    TableColumn<Produit, String> designationTableColumn = new TableColumn<>("designation");
    TableColumn<Produit, String> categorieTableColumn = new TableColumn<>("categorie");
    TableColumn<Produit, Double> prixAchatTableColumn = new TableColumn<>("prix achat");
    TableColumn<Produit, Long> qteTableColumn = new TableColumn<>("quatité");
    TableColumn totalTableColumn = new TableColumn("total");
    //la partie de produit
    Label designationLabel = new Label("Designation :");
    Label designationProduitLabel = new Label("Iphone X");
    Label prixAchatLabel = new Label("Prix achat:");
    Label prixAchatProduitLabel = new Label("12000");
    Label categorieLabel = new Label("Categorie : ");
    Label categorieProduitLabel = new Label("smart phone");
    Label qteLabel = new Label("Quantité en stock : ");
    Label qteProduitLabel = new Label("10");
    Label qteDemandeLabel = new Label("Quantité demandé : ");
    TextField qteDemandeTextField = new TextField();
    Label prixVenteLabel = new Label("Prix vente : ");
    TextField prixVenteTextField = new TextField();
    Button ajouterProduitALineCommandeButton = new Button("ajouter");
    //line de commande
    //les statistiques
    VBox infoGlobalVBox = new VBox();
    HBox totalPrixHorsTaxeHBox = new HBox();
    Label totalPrixHorsTaxeLabel = new Label("Total(HT): "); 
    Label totalPrixHorsCalculerTaxeLabel = new Label("0.0");
    HBox taxeHBox = new HBox();
    Label taxeHBoxLabel = new Label("Taxe(20%): ");
    Label taxeHBoxCalculerTaxeLabel = new Label("0.0");
    HBox TTCHBox = new HBox();
    Label TTCLabel = new Label("TTC: ");
    Label TTCCalculerTaxeLabel = new Label("0.0");
    //le tableau des lines de commande
    TableView<LineCommande> listeLinesCommandeTableView = new TableView<>();
    TableColumn<LineCommande, String> designationProduitTableColumn = new TableColumn<>("Produit");
    TableColumn<LineCommande, String> prixVenteTableColumn= new TableColumn<>("Prix vente");
    TableColumn<LineCommande, Long> qteDemandeTableColumn = new TableColumn<>("Quantité");
    TableColumn<LineCommande, LocalDate> dateCommandeTableColumn = new TableColumn<>("date");
    TableColumn totalCommandeTableColumn = new TableColumn("Total");

    
    
    public AjouterVenteWindow() {
        setupWindow();
        addWidgetToWindow();
        setStyleSheet();
        eventeHanlder();
        window.show();
    }

    private void setupWindow() {
        window.setTitle("ajouter un vente");
        window.setMinWidth(900);
        window.setHeight(600);
        window.setMinHeight(400);
        window.setScene(scene);
        window.initModality(Modality.APPLICATION_MODAL);
    }

    private void setStyleSheet() {
        scene.getStylesheets().add("css/new.css");
        root.getStyleClass().add("body");
        clientVBox.getStyleClass().add("main-carte");
        listeProduitsVBox.getStyleClass().add("main-carte");
        produitVBox.getStyleClass().add("main-carte");
        linesCommandeVBox.getStyleClass().add("main-carte");
        
        clientLabel.getStyleClass().add("lbl");
        nomLabel.getStyleClass().add("lbl");
        nomClientLabel.getStyleClass().add("lbl");
        prenomLabel.getStyleClass().add("lbl");
        prenomClientLabel.getStyleClass().add("lbl");
        teleLabel.getStyleClass().add("lbl");
        teleClientLabel.getStyleClass().add("lbl");
        emailLabel.getStyleClass().add("lbl");
        emailClientLabel.getStyleClass().add("lbl");
        adrLabel.getStyleClass().add("lbl");
        adrClientLabel.getStyleClass().add("lbl");
        dateLabel.getStyleClass().add("lbl");
        AjouterClientButton.getStyleClass().add("btn");
        
        designationLabel.getStyleClass().add("lbl");
        designationProduitLabel.getStyleClass().add("lbl");
        prixAchatLabel.getStyleClass().add("lbl");
        prixAchatProduitLabel.getStyleClass().add("lbl");
        categorieLabel.getStyleClass().add("lbl");
        categorieProduitLabel.getStyleClass().add("lbl");
        qteLabel.getStyleClass().add("lbl");
        qteProduitLabel.getStyleClass().add("lbl");
        qteDemandeLabel.getStyleClass().add("lbl");
        prixVenteLabel.getStyleClass().add("lbl");
        ajouterProduitALineCommandeButton.getStyleClass().addAll("btn", "lbl");
        
        totalPrixHorsTaxeLabel.getStyleClass().add("lbl");
        totalPrixHorsCalculerTaxeLabel.getStyleClass().add("lbl");
        taxeHBoxLabel.getStyleClass().add("lbl");
        taxeHBoxCalculerTaxeLabel.getStyleClass().add("lbl");
        TTCLabel.getStyleClass().add("lbl");
        TTCCalculerTaxeLabel.getStyleClass().add("lbl");
        
        rechercherProuditLabel.getStyleClass().add("lbl");
        rechercherProduitButton.getStyleClass().add("btn");
        
        VBox.setMargin(clientVBox, new Insets(10));
        VBox.setMargin(listeProduitsVBox, new Insets(10));
        VBox.setMargin(produitVBox, new Insets(10));
        VBox.setMargin(linesCommandeVBox, new Insets(10));
        HBox.setMargin(qteDemandeTextField, new Insets(0, 0, 5, 0));
        HBox.setMargin(prixVenteTextField, new Insets(0, 0, 5, 0));
        VBox.setMargin(listeProduitsTableView, new Insets(10, 0, 0, 0));
        VBox.setMargin(listeLinesCommandeTableView, new Insets(10, 0, 0, 0));
        HBox.setMargin(rechercherProduitTextField, new Insets(0, 5, 0, 5));
        
        clientVBox.setMaxHeight(100);
        produitVBox.setMaxHeight(100);
        
        HBox.setHgrow(leftVBox, Priority.ALWAYS);
        HBox.setHgrow(rightVBox, Priority.ALWAYS);
        VBox.setVgrow(clientVBox, Priority.ALWAYS);
        VBox.setVgrow(listeProduitsVBox, Priority.ALWAYS);
        VBox.setVgrow(produitVBox, Priority.ALWAYS);
        VBox.setVgrow(linesCommandeVBox, Priority.ALWAYS);
        VBox.setVgrow(listeProduitsTableView, Priority.ALWAYS);
        VBox.setVgrow(listeLinesCommandeTableView, Priority.ALWAYS);
        
        
        clientVBox.setEffect(new DropShadow(10, 0, 0, Color.BLACK));
        listeProduitsVBox.setEffect(new DropShadow(10, 0, 0, Color.BLACK));
        produitVBox.setEffect(new DropShadow(10, 0, 0, Color.BLACK));
        linesCommandeVBox.setEffect(new DropShadow(10, 0, 0, Color.BLACK));
                
                
    }

    private void addWidgetToWindow() {
        root.getChildren().addAll(leftVBox, rightVBox);
        leftVBox.getChildren().addAll(clientVBox, listeProduitsVBox);
        rightVBox.getChildren().addAll(produitVBox, linesCommandeVBox);
        //leftVBox
        //clientVBox
        clientVBox.getChildren().addAll( 
            new HBox(20, clientLabel,clientComboBox, AjouterClientButton),
            new HBox(nomLabel,nomClientLabel),
            new HBox(prenomLabel,prenomClientLabel),
            new HBox(teleLabel,teleClientLabel),
            new HBox(emailLabel,emailClientLabel),
            new HBox(adrLabel,adrClientLabel),
            new HBox(dateLabel,dateDatePicker));
        //listeProduits
        listeProduitsVBox.getChildren().addAll(rechercherProduitHbox, listeProduitsTableView);
        rechercherProduitHbox.getChildren().addAll(rechercherProuditLabel, rechercherProduitTextField, rechercherProduitButton);
        addColumnToTable();
        //rightVBox
        //produitVBox
        produitVBox.getChildren().addAll( 
            new HBox(designationLabel,designationProduitLabel),
            new HBox(prixAchatLabel,prixAchatProduitLabel),
            new HBox(categorieLabel,categorieProduitLabel),
            new HBox(qteLabel,qteProduitLabel),
            new HBox(qteDemandeLabel,qteDemandeTextField),
            new HBox(prixVenteLabel,prixVenteTextField),
            ajouterProduitALineCommandeButton
                    );
        //line de commande
        linesCommandeVBox.getChildren().addAll(infoGlobalVBox, listeLinesCommandeTableView);
        infoGlobalVBox.getChildren().addAll(totalPrixHorsTaxeHBox, taxeHBox, TTCHBox);
        totalPrixHorsTaxeHBox.getChildren().addAll( totalPrixHorsTaxeLabel, totalPrixHorsCalculerTaxeLabel);
        taxeHBox.getChildren().addAll( taxeHBoxLabel, taxeHBoxCalculerTaxeLabel);
        TTCHBox.getChildren().addAll( TTCLabel, TTCCalculerTaxeLabel);
    }

    private void eventeHanlder() {
    }
    
    private void addColumnToTable(){
        listeProduitsTableView.getColumns().addAll(
        designationTableColumn,categorieTableColumn,
        prixAchatTableColumn,qteTableColumn,
        totalTableColumn
        );
        listeLinesCommandeTableView.getColumns().addAll(
            designationProduitTableColumn, prixVenteTableColumn, 
            qteDemandeTableColumn, dateCommandeTableColumn,
            totalCommandeTableColumn);
    }
    
}

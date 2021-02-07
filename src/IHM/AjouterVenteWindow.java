/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import Entities.Client;
import Entities.LineCommande;
import Entities.Produit;
import Handler.AjouterVenteHandler;
import java.time.LocalDate;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
    BorderPane root = new BorderPane();
    HBox body = new HBox();
    Scene scene = new Scene(root);
    AjouterVenteHandler handler = new AjouterVenteHandler(this);
    VBox leftVBox = new VBox();
    VBox rightVBox = new VBox();
    VBox clientVBox = new VBox();
    VBox listeProduitsVBox = new VBox();
    VBox produitVBox = new VBox();
    VBox linesCommandeVBox = new VBox();
    //la partie de client
    Label clientLabel = new Label("client : ");
    //ajouter button pour selectionné un client
    Button selectionnerClientButton = new Button("Selectionner un client");
    Button AjouterClientButton = new Button("Ajouter un client");
    Label nomLabel = new Label("Nom : ");
    Label nomClientLabel = new Label("");
    Label prenomLabel = new Label("Prenom : ");
    Label prenomClientLabel = new Label("");
    Label teleLabel = new Label("Téléphone : ");
    Label teleClientLabel = new Label("");
    Label emailLabel = new Label("Email : ");
    Label emailClientLabel = new Label("");
    Label adrLabel = new Label("Adresse : ");
    Label adrClientLabel = new Label("");
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
    Label designationProduitLabel = new Label("");
    Label prixAchatLabel = new Label("Prix achat:");
    Label prixAchatProduitLabel = new Label("");
    Label categorieLabel = new Label("Categorie : ");
    Label categorieProduitLabel = new Label("");
    Label qteLabel = new Label("Quantité en stock : ");
    Label qteProduitLabel = new Label("");
    Label qteDemandeLabel = new Label("Quantité demandé : ");
    TextField qteDemandeTextField = new TextField();
    Label prixVenteLabel = new Label("Prix vente : ");
    TextField prixVenteTextField = new TextField();
    Button ajouterProduitALineCommandeButton = new Button("ajouter au line");
    Button ajouterProduitButton = new Button("ajouter Produit");
    //line de commande
    //les statistiques
    VBox infoGlobalVBox = new VBox();
    HBox totalPrixHorsTaxeHBox = new HBox();
    Label totalPrixHorsTaxeLabel = new Label("Total(HT): "); 
    Label totalPrixHorsTaxeCalculerLabel = new Label("0.0");
    HBox taxeHBox = new HBox();
    Label taxeLabel = new Label("Taxe(20%): ");
    Label taxeCalculerLabel = new Label("0.0");
    HBox TTCHBox = new HBox();
    Label TTCLabel = new Label("TTC: ");
    Label TTCCalculerLabel = new Label("0.0");
    //le tableau des lines de commande
    TableView<LineCommande> listeLinesCommandeTableView = new TableView<>();
    TableColumn<LineCommande, String> designationProduitTableColumn = new TableColumn<>("Produit");
    TableColumn<LineCommande, Double> prixVenteTableColumn= new TableColumn<>("Prix vente");
    TableColumn<LineCommande, Long> qteDemandeTableColumn = new TableColumn<>("Quantité");
    TableColumn<LineCommande, LocalDate> dateCommandeTableColumn = new TableColumn<>("date");
    TableColumn<LineCommande, Double> totalCommandeTableColumn = new TableColumn("Total");

    public Label getTotalPrixHorsTaxeCalculerLabel() {
        return totalPrixHorsTaxeCalculerLabel;
    }

    public Label getTaxeCalculerLabel() {
        return taxeCalculerLabel;
    }

    public Label getTTCCalculerLabel() {
        return TTCCalculerLabel;
    }

    public TableView<LineCommande> getListeLinesCommandeTableView() {
        return listeLinesCommandeTableView;
    }

    public TextField getQteDemandeTextField() {
        return qteDemandeTextField;
    }

    public TextField getPrixVenteTextField() {
        return prixVenteTextField;
    }

    public Label getDesignationProduitLabel() {
        return designationProduitLabel;
    }

    public Label getPrixAchatProduitLabel() {
        return prixAchatProduitLabel;
    }

    public Label getCategorieProduitLabel() {
        return categorieProduitLabel;
    }

    public Label getQteProduitLabel() {
        return qteProduitLabel;
    }

    public TextField getRechercherProduitTextField() {
        return rechercherProduitTextField;
    }

    public TableView<Produit> getListeProduitsTableView() {
        return listeProduitsTableView;
    }

    public Label getNomClientLabel() {
        return nomClientLabel;
    }

    public Label getPrenomClientLabel() {
        return prenomClientLabel;
    }

    public Label getTeleClientLabel() {
        return teleClientLabel;
    }

    public Label getEmailClientLabel() {
        return emailClientLabel;
    }

    public Label getAdrClientLabel() {
        return adrClientLabel;
    }

    
    public AjouterVenteWindow() {
        setupWindow();
        addWidgetToWindow();
        setStyleSheet();
        eventeHanlder();
        handler.setClientsToComboBox();
        handler.updateProduitTableView();
        window.show();
    }

    private void setupWindow() {
        window.setTitle("ajouter un vente");
        window.setMinWidth(900);
        window.setHeight(600);
        window.setMinHeight(400);
        window.setScene(scene);
        window.initModality(Modality.APPLICATION_MODAL);
        window.setMaximized(true);
    }

    private void setStyleSheet() {
        scene.getStylesheets().add("css/new.css");
        body.getStyleClass().add("body");
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
        selectionnerClientButton.getStyleClass().add("btn");
        
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
        ajouterProduitButton.getStyleClass().addAll("btn", "lbl");
        
        totalPrixHorsTaxeLabel.getStyleClass().add("lbl");
        totalPrixHorsTaxeCalculerLabel.getStyleClass().add("lbl");
        taxeLabel.getStyleClass().add("lbl");
        taxeCalculerLabel.getStyleClass().add("lbl");
        TTCLabel.getStyleClass().add("lbl");
        TTCCalculerLabel.getStyleClass().add("lbl");
        
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
        
        clientVBox.setMaxHeight(210);
        produitVBox.setMaxHeight(210);
        clientVBox.setMinHeight(210);
        produitVBox.setMinHeight(210);
        
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
        root.setCenter(body);
        body.getChildren().addAll(leftVBox, rightVBox);
        leftVBox.getChildren().addAll(clientVBox, listeProduitsVBox);
        rightVBox.getChildren().addAll(produitVBox, linesCommandeVBox);
        //leftVBox
        //clientVBox
        clientVBox.getChildren().addAll( 
            new HBox(20, clientLabel,selectionnerClientButton, AjouterClientButton),
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
            new HBox(10, ajouterProduitALineCommandeButton, ajouterProduitButton)
                    );
        //line de commande
        linesCommandeVBox.getChildren().addAll(infoGlobalVBox, listeLinesCommandeTableView);
        infoGlobalVBox.getChildren().addAll(totalPrixHorsTaxeHBox, taxeHBox, TTCHBox);
        totalPrixHorsTaxeHBox.getChildren().addAll( totalPrixHorsTaxeLabel, totalPrixHorsTaxeCalculerLabel);
        taxeHBox.getChildren().addAll( taxeLabel, taxeCalculerLabel);
        TTCHBox.getChildren().addAll( TTCLabel, TTCCalculerLabel);
    }

    private void eventeHanlder() {
        selectionnerClientButton.setOnAction(event->{
            
        });
        AjouterClientButton.setOnAction(event->{
           AjouterClientFormWindow ajouterClient = new AjouterClientFormWindow();
           ajouterClient.annulerClientButton.setOnAction(e->{
               ajouterClient.window.close();
           });
        });
        rechercherProduitButton.setOnAction(event->{
            handler.findProduit();
        });
        listeProduitsTableView.setRowFactory(event->{
            TableRow<Produit> row = new TableRow();
            row.setOnMouseClicked(e->{
                handler.setSelectedProduitToLabels(row.getItem());
                
            });
            return row;
        });
        ajouterProduitALineCommandeButton.setOnAction(event->{
            handler.addProduitToLineCommande();
        });
        ajouterProduitButton.setOnAction(event->{
            AjouterProduitFormWindow ajouterProduit = new AjouterProduitFormWindow();
            ajouterProduit.annulerProduitButton.setOnAction(e->{
                ajouterProduit.window.close();
                handler.updateProduitTableView();
            });
        });
    }
    
    private void addColumnToTable(){
        designationTableColumn.setCellValueFactory(new PropertyValueFactory<Produit, String>("designation"));
        categorieTableColumn.setCellValueFactory(new PropertyValueFactory<Produit, String>("categorie"));
        prixAchatTableColumn.setCellValueFactory(new PropertyValueFactory<Produit, Double>("PrixAchat"));
        qteTableColumn.setCellValueFactory(new PropertyValueFactory<Produit, Long>("qte"));
        totalTableColumn.setCellValueFactory(new PropertyValueFactory<Produit, String>("total"));
        
        designationProduitTableColumn.setCellValueFactory(new PropertyValueFactory<LineCommande, String>("produit"));
        prixVenteTableColumn.setCellValueFactory(new PropertyValueFactory<LineCommande, Double>("prixVente"));
        qteDemandeTableColumn.setCellValueFactory(new PropertyValueFactory<LineCommande, Long>("qte"));
        dateCommandeTableColumn.setCellValueFactory(new PropertyValueFactory<LineCommande, LocalDate>("date"));
        totalCommandeTableColumn.setCellValueFactory(new PropertyValueFactory<LineCommande, Double>("total"));
        
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

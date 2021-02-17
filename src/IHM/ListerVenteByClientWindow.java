/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import Entities.Client;
import Entities.Vente;
import Handler.ListerVenteByClientHandler;
import java.time.LocalDate;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
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

public class ListerVenteByClientWindow {
    Stage window = new Stage();
    VBox root = new VBox();
    Scene scene = new Scene(root);
    ListerVenteByClientHandler handler = new ListerVenteByClientHandler(this);
    //titre du window
    HBox titreHBox = new HBox(10);
    Label titreLabel = new Label("liste des vente: ");
    Label nomClientLabel = new Label();
    TableView<Vente> listeVentesTableView = new TableView<>();
    TableColumn<Vente, Long> idVenteTableColumn = new TableColumn<>("id");
    TableColumn<Vente, LocalDate> dateTableColumn = new TableColumn<>("date");
    TableColumn<Vente, Double> totalTableColumn = new TableColumn<>("Total");
    HBox totalHBox = new HBox(10);
    Label totalLabel = new Label("total :");
    Label totalCalculeLabel = new Label("0.0");

    public Label getTotalCalculeLabel() {
        return totalCalculeLabel;
    }

    public Label getNomClientLabel() {
        return nomClientLabel;
    }

    public TableView<Vente> getListeVentesTableView() {
        return listeVentesTableView;
    }
    
    public ListerVenteByClientWindow(Client client){
        setupWindow();
        setStyleSheet();
        addWidgetToWindow();
        eventHandler();
        handler.setClient(client);
        handler.setNameToLabel();
        handler.updateListeVentesTableView();
        handler.calculeTotalVente();
        window.show();
    }

    private void setupWindow() {
        window.setTitle("liste des ventes");
        window.setWidth(800);
        window.setHeight(600);
        window.setScene(scene);
        window.initModality(Modality.APPLICATION_MODAL);
    }

    private void setStyleSheet() {
        scene.getStylesheets().add("css/new.css");
        root.getStyleClass().add("body");
        titreHBox.getStyleClass().add("titre");
        titreLabel.getStyleClass().add("lblTitre");
        nomClientLabel.getStyleClass().add("lblTitre");
        
        totalHBox.getStyleClass().add("titre"); 
        totalLabel.getStyleClass().add("lblTitre");
        totalCalculeLabel.getStyleClass().add("lblTitre");
        
        idVenteTableColumn.setMinWidth(100);
        dateTableColumn.setMinWidth(100);
        totalTableColumn.setMinWidth(100);
    }

    private void addWidgetToWindow() {
        root.getChildren().addAll(titreHBox, listeVentesTableView, totalHBox);
        titreHBox.getChildren().addAll(titreLabel, nomClientLabel);
        totalHBox.getChildren().addAll(totalLabel, totalCalculeLabel);
        
        setupListeVentesTableView();
    }

    private void eventHandler() {
        listeVentesTableView.setRowFactory(event->{
            TableRow<Vente> row = new TableRow<>();
            row.setOnMouseClicked(e->{
                handler.askUpdateDeleteVente(row.getItem());
            });
            return row;
        });
    }
    
    private void setupListeVentesTableView(){
        idVenteTableColumn.setCellValueFactory(new PropertyValueFactory<Vente, Long>("id"));
        dateTableColumn.setCellValueFactory(new PropertyValueFactory<Vente, LocalDate>("date"));
        totalTableColumn.setCellValueFactory(new PropertyValueFactory<Vente, Double>("total"));
        
        listeVentesTableView.getColumns().addAll(idVenteTableColumn,dateTableColumn,totalTableColumn);
    }
    
}

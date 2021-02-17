/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author COZMET
 */
public class Program extends Application{

    BorderPane root = new BorderPane();
    Scene scene = new Scene(root);
    //menu bare
    MenuBar menuBar = new MenuBar();
    //menus
    Menu produitMenu = new Menu("produit");
    Menu clientMenu = new Menu("client");
    Menu venteMenu = new Menu("vente");
    //items
    MenuItem ajouterProduitMenuItem = new MenuItem("ajouter");
    MenuItem rechercherProduitMenuItem = new MenuItem("rechercher");
    MenuItem listerProduitMenuItem = new MenuItem("lister");
    MenuItem ajouterClientMenuItem = new MenuItem("ajouter");
    MenuItem rechercherClientMenuItem = new MenuItem("rechercher");
    MenuItem listerClientMenuItem = new MenuItem("lister");
    MenuItem ajouterVenteMenuItem = new MenuItem("ajouter");
    MenuItem rechercherVenteMenuItem = new MenuItem("rechercher");
    
    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        launch();
    }

    private void addStyleSheet(){
        scene.getStylesheets().add("css/main.css");
        root.getStyleClass().add("body");
    }
    
    private void addMenuToWindow(){
        root.setTop(menuBar);
        menuBar.getMenus().addAll(produitMenu, clientMenu, venteMenu);
        produitMenu.getItems().addAll(ajouterProduitMenuItem, rechercherProduitMenuItem, listerProduitMenuItem);
        clientMenu.getItems().addAll(ajouterClientMenuItem, rechercherClientMenuItem, listerClientMenuItem);
        venteMenu.getItems().addAll(ajouterVenteMenuItem, rechercherVenteMenuItem);
    }
    
    private void eventHandler(){
        ajouterProduitMenuItem.setOnAction(event->{
            new AjouterProduitFormWindow();
        });
        listerProduitMenuItem.setOnAction(event->{
            new ListerProduitWindow();
        });
        rechercherProduitMenuItem.setOnAction(event->{
            new RechercherProduitFormWindow();
        });
        ajouterClientMenuItem.setOnAction(event->{
           new AjouterClientFormWindow();
        });
        rechercherClientMenuItem.setOnAction(event->{
            new RechercherClientFormWindow();
        });
        listerClientMenuItem.setOnAction(event->{
            new ListerClientWindow();
        });
        ajouterVenteMenuItem.setOnAction(event->{
           new AjouterVenteWindow(); 
        });
        rechercherVenteMenuItem.setOnAction(event->{
           ListerClientWindow listerClient = ListerClientWindow.selectionListeClient((client)->{
               new ListerVenteByClientWindow(client);
                return null;
            });
        });
    }
    
    @Override
    public void start(Stage window) throws Exception {
        window.setTitle("gestion de magazin");
        window.setWidth(800);
        window.setHeight(600);
        window.setScene(scene);
        
        addStyleSheet();
        addMenuToWindow();
        eventHandler();
        
        window.show();
    }
    
}

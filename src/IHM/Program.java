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
    //items
    MenuItem ajouterProduitMenuItem = new MenuItem("ajouter");
    MenuItem rechercherProduitMenuItem = new MenuItem("rechercher");
    MenuItem listerProduitMenuItem = new MenuItem("lister");
    
    
    
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
        menuBar.getMenus().addAll(produitMenu);
        produitMenu.getItems().addAll(ajouterProduitMenuItem, rechercherProduitMenuItem, listerProduitMenuItem);
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwareeng.biblioteca.application;

import javafx.application.Application;

/**
 *
 * @author cashrules
 */
public class MainApp extends Application {
    private Stage primaryStage;
    private Catalogo catalogo;
    private ListaUtenti utenti; 
    private ListaPrestiti prestiti;
    private GestoreFile gestoreFile;

    @Override
    public void start(Stage primaryStage);
    
    public void mostraHome();
    
    public void mostraLibri();

    public void mostraUtenti();
    
    public void mostraPrestiti();
    
    public void caricaDati();
    
    public void salvaDati()
}

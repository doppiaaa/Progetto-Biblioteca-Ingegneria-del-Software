/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwareeng.biblioteca.controller;
import softwareeng.biblioteca.model.Gestione;
import softwareeng.biblioteca.application.MainApp;
import java.util.Map;

/**
 *
 * @author cashrules
 */
public abstract class TController<T> {
    public abstract void setMainApp(MainApp app);
    
    public abstract void setDati(? extends Gestione<T> interfaccia);

    public abstract void goHome();

    public abstract void goToPrestiti();

    public abstract void clickNuovo();

    public abstract void seleziona(T elemento);

    public abstract void rimuovi();

    public abstract void modifica();

    public abstract boolean showEditDialog(T elemento);
    
    public abstract boolean showEditDialog();

    public abstract void popolaForm(T elemento);

    public abstract void sendAttrbitui(Map<String, Object> attributi);
}

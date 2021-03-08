package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.Window;
import sample.Main;
import sample.sql.InventoryManagement;
import sample.window.Notification;

import java.io.IOException;

public class ControllerAddTable {
    public HBox root;
    public TextField cod;
    public TextField name;
    public TextField sena;
    public TextField col;
    public TextField fact;

    public void initialize() throws IOException {


    }

    public void add(ActionEvent actionEvent) {
        try {
            Main.bd.addInventory(
                    ControllerMain.id, cod.getText(), name.getText(), Integer.parseInt(col.getText()), Integer.parseInt(
                            fact.getText()),
                    Integer.parseInt(sena.getText())
                                );
            ObservableList<InventoryManagement> inventories = FXCollections.observableArrayList();
            inventories.addAll(Main.bd.getInventory(ControllerMain.id));
            ControllerTable.table.setItems(inventories);
            Stage window = (Stage) col.getScene().getWindow();
            window.close();
        }catch (Exception e){
            new Notification("Error", "Ведены не верные данные", Alert.AlertType.ERROR);
        }


    }
}

package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import sample.Main;
import sample.controller.gui.CustomTableView;
import sample.sql.Inventory;
import sample.window.StageDialog;

import java.io.IOException;

public class ControllerMain {
    public static CustomTableView<Inventory> table = new CustomTableView<>();
    public HBox root;
    public static String id;
    public void initialize() throws IOException {
        table = new CustomTableView<>();
        root.getChildren().add(table);
        VBox.setVgrow(table, Priority.ALWAYS);
        HBox.setHgrow(table, Priority.ALWAYS);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        TableColumn<Inventory, String> colIP = new TableColumn<Inventory, String>("ID");
        TableColumn<Inventory, String> colName = new TableColumn<Inventory, String>("Номер");
        TableColumn<Inventory, String> colStat = new TableColumn<Inventory, String>("Дата создания");

        colIP.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("number"));
        colStat.setCellValueFactory(new PropertyValueFactory<>("dateCreate"));




        colName.setStyle(" -fx-alignment: CENTER");
        colStat.setStyle(" -fx-alignment: CENTER");



        table.getColumns().addAll(colIP, colName, colStat);
        ObservableList<Inventory> inventories = FXCollections.observableArrayList();
        inventories.addAll(Main.bd.getInventoryManagement());
        table.setItems(inventories);


    }

    public void add(ActionEvent actionEvent) {
        StageDialog stageDialog = new StageDialog("add", "добавить инвентаризацию", false);
        stageDialog.show();
    }
}

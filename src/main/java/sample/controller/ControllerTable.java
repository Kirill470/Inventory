package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.Main;
import sample.controller.gui.CustomTableView;
import sample.sql.Inventory;
import sample.sql.InventoryManagement;
import sample.window.StageDialog;

import java.io.IOException;

public class ControllerTable {
    public static CustomTableView<InventoryManagement> table = new CustomTableView<>();
    public HBox root;


    public void initialize() throws IOException {
        table = new CustomTableView<>();
        VBox vBox = new VBox();
        Button button = new Button("Создать товар");
        HBox.setHgrow(button, Priority.ALWAYS);
        button.setMaxWidth(Double.MAX_VALUE);
        button.setOnMouseClicked(event -> {
            StageDialog stageDialog = new StageDialog("addTable","добавить",true);

        });
        Button button2 = new Button("Вернуться");
        HBox.setHgrow(button2, Priority.ALWAYS);
        button2.setMaxWidth(Double.MAX_VALUE);
        button2.setOnMouseClicked(event -> {
            try {
                Main.scene("main","главная");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        vBox.getChildren().add(button2);
        vBox.getChildren().add(button);
        root.getChildren().add(vBox);

        root.getChildren().add(table);
        VBox.setVgrow(table, Priority.ALWAYS);
        HBox.setHgrow(table, Priority.ALWAYS);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        TableColumn<InventoryManagement, String> colName = new TableColumn<InventoryManagement, String>("Код товара");
        TableColumn<InventoryManagement, String> colStat = new TableColumn<InventoryManagement, String>("Наименование");
        TableColumn<InventoryManagement, String> colSсдфв = new TableColumn<InventoryManagement, String>(
                "Кол. на складе");
        TableColumn<InventoryManagement, String> raznica = new TableColumn<InventoryManagement, String>("Разница");
        TableColumn<InventoryManagement, String> factRaz = new TableColumn<InventoryManagement, String>("Фактическая разница");
        TableColumn<InventoryManagement, String> cena = new TableColumn<InventoryManagement, String>("Цена");
        TableColumn<InventoryManagement, String> summa = new TableColumn<InventoryManagement, String>("Сумма");


        colName.setCellValueFactory(new PropertyValueFactory<>("cod"));
        colStat.setCellValueFactory(new PropertyValueFactory<>("name"));
        colSсдфв.setCellValueFactory(new PropertyValueFactory<>("kol"));
        factRaz.setCellValueFactory(new PropertyValueFactory<>("factRaz"));
        raznica.setCellValueFactory(new PropertyValueFactory<>("raznica"));
        cena.setCellValueFactory(new PropertyValueFactory<>("price"));
        summa.setCellValueFactory(new PropertyValueFactory<>("summa"));


        colName.setStyle(" -fx-alignment: CENTER");
        colStat.setStyle(" -fx-alignment: CENTER");


        table.getColumns().addAll(
                colName,
                colStat,
                colSсдфв,
                factRaz,
                raznica,
                cena,summa
                                 );
        ObservableList<InventoryManagement> inventories = FXCollections.observableArrayList();

        inventories.addAll(Main.bd.getInventory(ControllerMain.id));
        table.setItems(inventories);

    }
}

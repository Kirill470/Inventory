package sample.controller;


import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import sample.Main;
import sample.sql.Inventory;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ControllerAdd {
    public HBox root;

    public void initialize() throws IOException {
        DatePicker datePicker = new DatePicker();
        datePicker.setShowWeekNumbers(true);
        StringConverter<LocalDate> converter = new StringConverter<LocalDate>() {
            DateTimeFormatter dateFormatter =
                    DateTimeFormatter.ofPattern("yyyy-MM-dd");

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        };
        datePicker.setConverter(converter);
        datePicker.setPromptText("yyyy-MM-dd");

        root.getChildren().add(datePicker);

        Button button = new Button("Добавить инвентаризацию");
        button.setOnMouseClicked(event -> {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    Main.bd.addInventoryManagement(
                            Date.from(datePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
                    ObservableList<Inventory> inventories = FXCollections.observableArrayList();
                    inventories.addAll(Main.bd.getInventoryManagement());
                    ControllerMain.table.setItems(inventories);
                    Stage stage = (Stage) button.getScene().getWindow();
                    stage.close();
                }
            });
        });
        root.getChildren().add(button);

    }
}

package sample.sql;

import javafx.scene.control.Button;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import sample.Main;
import sample.controller.ControllerMain;
import sample.controller.ControllerTable;
import sample.window.Notification;
import sample.window.StageDialog;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class Inventory {
    private String id;

    public int number;
    Date dateCreate;

    public Inventory(String id, int number, Date dateCreate) {
        this.id = id;
        this.number = number;
        this.dateCreate = dateCreate;
    }

    public Button getId() {
        Button button = new Button(id);
        button.setOnMouseClicked(event -> {
            ControllerMain.id = getIdS();
            try {
                Main.scene("Table", "Инвентаризация №" + number + " дата:" +
                        new SimpleDateFormat("YYYY-MM-dd").format(dateCreate));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        VBox.setVgrow(button, Priority.ALWAYS);
        button.setMaxWidth(Double.MAX_VALUE);
        return button;
    }

    public String getIdS() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getDateCreate() {
        return new SimpleDateFormat("YYYY-MM-dd").format(dateCreate);
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }


}

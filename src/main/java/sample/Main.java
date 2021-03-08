package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.sql.BD;

import java.io.IOException;

import java.util.Date;
import java.util.Objects;

public class Main extends Application {
    public static Stage stage;
    public static BD bd;

    @Override public void init() throws Exception {
        bd = new BD("db.sqlite");
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader
                .load(Objects.requireNonNull(getClass().getClassLoader().getResource("fxml/main.fxml")));
        primaryStage.setTitle("Hello World");


        Scene scene = new Scene(root);
        scene.getStylesheets()
             .add(Objects.requireNonNull(getClass().getClassLoader().getResource("css/main.css")).toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
        this.stage = primaryStage;
    }

    public static void scene(String fileName, String nameWindows) throws IOException {
        Parent root = FXMLLoader
                .load(Objects.requireNonNull(Main.class.getClassLoader().getResource("fxml/" + fileName+".fxml")));
        stage.setTitle(nameWindows);
        stage.setScene(new Scene(root));
    }


    public static void main(String[] args) {
        launch(args);

    }

}

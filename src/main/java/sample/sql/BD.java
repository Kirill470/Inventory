package sample.sql;

import javafx.scene.control.Alert;
import sample.window.Notification;

import java.sql.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;


//Этот класс подключает БД
public class BD {
    private Connection conn;


    public BD(String fileConn) throws ClassNotFoundException, SQLException {


        Class.forName("org.sqlite.JDBC");

        conn = DriverManager.getConnection("jdbc:sqlite:" + fileConn);
        start();

    }

    public Connection getConn() {
        return conn;
    }

    public void start() throws SQLException {
        try {
            Statement statmt = conn.createStatement();
            statmt.execute(
                    "create table inventory_management ( id text not null constraint inventory_management_pk primary " +
                            "key, number INTEGER, dateCreate date ); create unique index " +
                            "inventory_management_id_uindex on inventory_management (id);");
            statmt.execute(
                    "create table Inventory ( id text not null, cod text not null, name text, kol int, dactRaz int, " +
                            "price int ,idd text);");
            statmt.close();
        } catch (Exception e) {

        }
    }

    public Set<Inventory> getInventoryManagement() {
        try (Statement statement = this.conn.createStatement()) {
            Set<Inventory> inventories = new TreeSet<>(
                    Comparator.comparing(Inventory::getNumber));
            ResultSet resultSet = statement.executeQuery("SELECT * FROM inventory_management");
            while (resultSet.next()) {
                inventories.add(new Inventory(resultSet.getString("id"), resultSet.getInt("number"),
                                              resultSet.getDate("dateCreate")
                ));
            }
            return inventories;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return Collections.emptySet();
        }
    }

    public void addInventoryManagement(java.util.Date date) {
        try (Statement statmt = conn.createStatement()) {
            AtomicInteger max = new AtomicInteger();
            getInventoryManagement().forEach(inventory -> max
                    .set(Math.max(inventory.number, max.get())));
            statmt.execute("INSERT INTO inventory_management (id, number, dateCreate) VALUES ('" +
                                   UUID.randomUUID() + "', " + (max.get() + 1) + ",'" +
                                   date.getTime() + "' )");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Set<InventoryManagement> getInventory(String id) {
        try (Statement statement = this.conn.createStatement()) {
            Set<InventoryManagement> inventories = new TreeSet<>(
                    Comparator.comparing(InventoryManagement::getIdd));
            ResultSet resultSet = statement.executeQuery("SELECT * FROM inventory where id = '" + id + "'");
            while (resultSet.next()) {
                inventories.add(new InventoryManagement(resultSet.getString("id"), resultSet.getString("cod"),
                                                        resultSet.getString("name"), resultSet.getInt("kol"),
                                                        resultSet.getInt("dactRaz"), resultSet.getInt("price"),
                                                        resultSet.getString("idd")
                ));

            }
            return inventories;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return Collections.emptySet();
        }
    }

    public void addInventory(String id, String cod, String name, int kol, int dactRaz, double price) {
        try (Statement statmt = conn.createStatement()) {
            AtomicInteger max = new AtomicInteger();
            getInventoryManagement().forEach(inventory -> max
                    .set(Math.max(inventory.number, max.get())));
            statmt.execute("INSERT INTO Inventory (id, cod, name, kol, dactRaz, price,idd) VALUES ('" +
                                   id + "', '" + cod + "', '" + name + "', " + kol + ", " +
                                   dactRaz + ", " + price + " ,'" + UUID.randomUUID() + "')");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            new Notification("Error", "Ведены не верные данные", Alert.AlertType.ERROR);
        }
    }
}


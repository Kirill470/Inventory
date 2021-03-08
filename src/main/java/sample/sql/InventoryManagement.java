package sample.sql;

public class InventoryManagement {
    String id;
    String cod;
    String name;
    private String idd;
    int kol;
    int factRaz;
    int price;

    public InventoryManagement(String id, String cod, String name, int kol, int factRaz, int price, String idd) {
        this.id = id;
        this.cod = cod;
        this.name = name;
        this.kol = kol;
        this.factRaz = factRaz;
        this.price = price;
        this.idd = idd;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getKol() {
        return kol;
    }

    public void setKol(int kol) {
        this.kol = kol;
    }

    public int getFactRaz() {
        return factRaz;
    }

    public void setFactRaz(int factRaz) {
        this.factRaz = factRaz;
    }

    public int getPrice() {
        return price;
    }

    public int getRaznica() {
        return kol - factRaz;
    }

    public int getSumma() {
        return kol * price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getIdd() {
        return idd;
    }

    public void setIdd(String idd) {
        this.idd = idd;
    }
}

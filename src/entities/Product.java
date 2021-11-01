package entities;

public class Product {
    private String name;
    private String category;
    private int quantity;
    private double costPrice;
    private double sellPrice;

    // Constructor
    public Product(String name, String category, int quantity, double costPrice, double sellPrice) {
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.costPrice = costPrice;
        this.sellPrice = sellPrice;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public double getCostPrice() {
        return costPrice;
    }
    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }

    public double getSellPrice() {
        return sellPrice;
    }
    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    // toString method Override
    public String toString() {
        return "Nome: " +  name + " | " +
                "Cat.: " + category + " | " +
                "Qt.: " + quantity + " | " +
                "Preço custo: U$" + costPrice + " | " +
                "Preço venda: U$" + sellPrice;
    }

    // Showing products for the costumer
    public String productCatalog() {
        return "Produto: " + name + " | " +
                "Categoria: " + category + " | " +
                "Preço: U$" + sellPrice;
    }
}

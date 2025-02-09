package org.management;
public class Inventory {
    private int inventoryId;
    private int itemId;
    private int quantity;
    private String location;
    private String lastUpdated;
    private int minimumStock;
    private int maximumStock;

    // Constructors
    public Inventory(int inventoryId, int itemId, int quantity, String location, String lastUpdated, int minimumStock, int maximumStock) {
        this.inventoryId = inventoryId;
        this.itemId = itemId;
        this.quantity = quantity;
        this.location = location;
        this.lastUpdated = lastUpdated;
        this.minimumStock = minimumStock;
        this.maximumStock = maximumStock;
    }

    public Inventory() {}

    // Getters and Setters
    public int getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public int getMinimumStock() {
        return minimumStock;
    }

    public void setMinimumStock(int minimumStock) {
        this.minimumStock = minimumStock;
    }

    public int getMaximumStock() {
        return maximumStock;
    }

    public void setMaximumStock(int maximumStock) {
        this.maximumStock = maximumStock;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "inventoryId=" + inventoryId +
                ", itemId=" + itemId +
                ", quantity=" + quantity +
                ", location='" + location + '\'' +
                ", lastUpdated='" + lastUpdated + '\'' +
                ", minimumStock=" + minimumStock +
                ", maximumStock=" + maximumStock +
                '}';
    }
}
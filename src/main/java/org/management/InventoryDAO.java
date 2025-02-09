package org.management;

import java.sql.*;
import java.util.ArrayList;

public class InventoryDAO {
    // Create
    public void addInventory(Inventory inventory) throws SQLException {
        String sql = "INSERT INTO inventory (item_id, quantity, location, last_updated, minimum_stock, maximum_stock) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
                    
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setInt(1, inventory.getItemId());
            pstmt.setInt(2, inventory.getQuantity());
            pstmt.setString(3, inventory.getLocation());
            pstmt.setString(4, inventory.getLastUpdated());
            pstmt.setInt(5, inventory.getMinimumStock());
            pstmt.setInt(6, inventory.getMaximumStock());
            
            pstmt.executeUpdate();
            
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                inventory.setInventoryId(rs.getInt(1));
            }
        }
    }

    // Read all inventory
    public ArrayList<Inventory> getAllInventory() throws SQLException {
        ArrayList<Inventory> inventoryList = new ArrayList<>();
        String sql = "SELECT * FROM inventory";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Inventory inventory = new Inventory();
                inventory.setInventoryId(rs.getInt("inventory_id"));
                inventory.setItemId(rs.getInt("item_id"));
                inventory.setQuantity(rs.getInt("quantity"));
                inventory.setLocation(rs.getString("location"));
                inventory.setLastUpdated(rs.getString("last_updated"));
                inventory.setMinimumStock(rs.getInt("minimum_stock"));
                inventory.setMaximumStock(rs.getInt("maximum_stock"));
                inventoryList.add(inventory);
            }
        }
        return inventoryList;
    }

    // Get inventory by item ID
    public Inventory getInventoryByItemId(int itemId) throws SQLException {
        String sql = "SELECT * FROM inventory WHERE item_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, itemId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                Inventory inventory = new Inventory();
                inventory.setInventoryId(rs.getInt("inventory_id"));
                inventory.setItemId(rs.getInt("item_id"));
                inventory.setQuantity(rs.getInt("quantity"));
                inventory.setLocation(rs.getString("location"));
                inventory.setLastUpdated(rs.getString("last_updated"));
                inventory.setMinimumStock(rs.getInt("minimum_stock"));
                inventory.setMaximumStock(rs.getInt("maximum_stock"));
                return inventory;
            }
        }
        return null;
    }

    // Get low stock items
    public ArrayList<Inventory> getLowStockItems() throws SQLException {
        ArrayList<Inventory> lowStockItems = new ArrayList<>();
        String sql = "SELECT * FROM inventory WHERE quantity <= minimum_stock";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Inventory inventory = new Inventory();
                inventory.setInventoryId(rs.getInt("inventory_id"));
                inventory.setItemId(rs.getInt("item_id"));
                inventory.setQuantity(rs.getInt("quantity"));
                inventory.setLocation(rs.getString("location"));
                inventory.setLastUpdated(rs.getString("last_updated"));
                inventory.setMinimumStock(rs.getInt("minimum_stock"));
                inventory.setMaximumStock(rs.getInt("maximum_stock"));
                lowStockItems.add(inventory);
            }
        }
        return lowStockItems;
    }

    // Update
    public void updateInventory(Inventory inventory) throws SQLException {
        String sql = "UPDATE inventory SET quantity = ?, location = ?, last_updated = ?, " +
                    "minimum_stock = ?, maximum_stock = ? WHERE inventory_id = ?";
                    
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, inventory.getQuantity());
            pstmt.setString(2, inventory.getLocation());
            pstmt.setString(3, inventory.getLastUpdated());
            pstmt.setInt(4, inventory.getMinimumStock());
            pstmt.setInt(5, inventory.getMaximumStock());
            pstmt.setInt(6, inventory.getInventoryId());
            
            pstmt.executeUpdate();
        }
    }

    // Delete
    public void deleteInventory(int id) throws SQLException {
        String sql = "DELETE FROM inventory WHERE inventory_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
}
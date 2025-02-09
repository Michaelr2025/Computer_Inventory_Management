package org.management;

import java.sql.*;
import java.util.ArrayList;

public class ItemDAO {
    // Create
    public void addItem(Items item) throws SQLException {
        String sql = "INSERT INTO items (name, description, price, category, supplier, active) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
                    
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setString(1, item.getName());
            pstmt.setString(2, item.getDescription());
            pstmt.setDouble(3, item.getPrice());
            pstmt.setString(4, item.getCategory());
            pstmt.setString(5, item.getSupplier());
            pstmt.setBoolean(6, item.isActive());
            
            pstmt.executeUpdate();
            
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                item.setItemId(rs.getInt(1));
            }
        }
    }

    // Read all items
    public ArrayList<Items> getAllItems() throws SQLException {
        ArrayList<Items> items = new ArrayList<>();
        String sql = "SELECT * FROM items";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Items item = new Items();
                item.setItemId(rs.getInt("item_id"));
                item.setName(rs.getString("name"));
                item.setDescription(rs.getString("description"));
                item.setPrice(rs.getDouble("price"));
                item.setCategory(rs.getString("category"));
                item.setSupplier(rs.getString("supplier"));
                item.setActive(rs.getBoolean("active"));
                items.add(item);
            }
        }
        return items;
    }

    // Read item by ID
    public Items getItemById(int id) throws SQLException {
        String sql = "SELECT * FROM items WHERE item_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                Items item = new Items();
                item.setItemId(rs.getInt("item_id"));
                item.setName(rs.getString("name"));
                item.setDescription(rs.getString("description"));
                item.setPrice(rs.getDouble("price"));
                item.setCategory(rs.getString("category"));
                item.setSupplier(rs.getString("supplier"));
                item.setActive(rs.getBoolean("active"));
                return item;
            }
        }
        return null;
    }

    // Search items by name
    public ArrayList<Items> searchItemsByName(String name) throws SQLException {
        ArrayList<Items> items = new ArrayList<>();
        String sql = "SELECT * FROM items WHERE LOWER(name) LIKE ?";
        String searchPattern = "%" + name.toLowerCase() + "%";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, searchPattern);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Items item = new Items();
                item.setItemId(rs.getInt("item_id"));
                item.setName(rs.getString("name"));
                item.setDescription(rs.getString("description"));
                item.setPrice(rs.getDouble("price"));
                item.setCategory(rs.getString("category"));
                item.setSupplier(rs.getString("supplier"));
                item.setActive(rs.getBoolean("active"));
                items.add(item);
            }
        }
        return items;
    }

    // Update
    public void updateItem(Items item) throws SQLException {
        String sql = "UPDATE items SET name = ?, description = ?, price = ?, " +
                    "category = ?, supplier = ?, active = ? WHERE item_id = ?";
                    
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, item.getName());
            pstmt.setString(2, item.getDescription());
            pstmt.setDouble(3, item.getPrice());
            pstmt.setString(4, item.getCategory());
            pstmt.setString(5, item.getSupplier());
            pstmt.setBoolean(6, item.isActive());
            pstmt.setInt(7, item.getItemId());
            
            pstmt.executeUpdate();
        }
    }

    // Delete
    public void deleteItem(int id) throws SQLException {
        String sql = "DELETE FROM items WHERE item_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
}

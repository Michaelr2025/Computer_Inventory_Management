package org.management;

import java.sql.*;
import java.util.ArrayList;

public class OrderDAO {
    // Create
    public void addOrder(Order order) throws SQLException {
        String sql = "INSERT INTO orders (customer_id, order_date, status, total_amount, shipping_address, payment_method) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
                    
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setInt(1, order.getCustomerId());
            pstmt.setString(2, order.getOrderDate());
            pstmt.setString(3, order.getStatus());
            pstmt.setDouble(4, order.getTotalAmount());
            pstmt.setString(5, order.getShippingAddress());
            pstmt.setString(6, order.getPaymentMethod());
            
            pstmt.executeUpdate();
            
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                order.setOrderId(rs.getInt(1));
            }
        }
    }

    // Read all orders
    public ArrayList<Order> getAllOrders() throws SQLException {
        ArrayList<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Order order = new Order();
                order.setOrderId(rs.getInt("order_id"));
                order.setCustomerId(rs.getInt("customer_id"));
                order.setOrderDate(rs.getString("order_date"));
                order.setStatus(rs.getString("status"));
                order.setTotalAmount(rs.getDouble("total_amount"));
                order.setShippingAddress(rs.getString("shipping_address"));
                order.setPaymentMethod(rs.getString("payment_method"));
                orders.add(order);
            }
        }
        return orders;
    }

    // Read order by ID
    public Order getOrderById(int id) throws SQLException {
        String sql = "SELECT * FROM orders WHERE order_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                Order order = new Order();
                order.setOrderId(rs.getInt("order_id"));
                order.setCustomerId(rs.getInt("customer_id"));
                order.setOrderDate(rs.getString("order_date"));
                order.setStatus(rs.getString("status"));
                order.setTotalAmount(rs.getDouble("total_amount"));
                order.setShippingAddress(rs.getString("shipping_address"));
                order.setPaymentMethod(rs.getString("payment_method"));
                return order;
            }
        }
        return null;
    }

    // Get orders by customer ID
    public ArrayList<Order> getOrdersByCustomerId(int customerId) throws SQLException {
        ArrayList<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders WHERE customer_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, customerId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Order order = new Order();
                order.setOrderId(rs.getInt("order_id"));
                order.setCustomerId(rs.getInt("customer_id"));
                order.setOrderDate(rs.getString("order_date"));
                order.setStatus(rs.getString("status"));
                order.setTotalAmount(rs.getDouble("total_amount"));
                order.setShippingAddress(rs.getString("shipping_address"));
                order.setPaymentMethod(rs.getString("payment_method"));
                orders.add(order);
            }
        }
        return orders;
    }

    // Update
    public void updateOrder(Order order) throws SQLException {
        String sql = "UPDATE orders SET customer_id = ?, order_date = ?, status = ?, " +
                    "total_amount = ?, shipping_address = ?, payment_method = ? WHERE order_id = ?";
                    
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, order.getCustomerId());
            pstmt.setString(2, order.getOrderDate());
            pstmt.setString(3, order.getStatus());
            pstmt.setDouble(4, order.getTotalAmount());
            pstmt.setString(5, order.getShippingAddress());
            pstmt.setString(6, order.getPaymentMethod());
            pstmt.setInt(7, order.getOrderId());
            
            pstmt.executeUpdate();
        }
    }

    // Delete
    public void deleteOrder(int id) throws SQLException {
        String sql = "DELETE FROM orders WHERE order_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
}
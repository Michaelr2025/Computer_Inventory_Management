package org.management;

import java.sql.*;
import java.util.ArrayList;

public class TaskDAO {
    // Create
    public void addTask(Task task) throws SQLException {
        String sql = "INSERT INTO tasks (employee_id, title, description, due_date, priority, status) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
                    
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setInt(1, task.getEmployeeId());
            pstmt.setString(2, task.getTitle());
            pstmt.setString(3, task.getDescription());
            pstmt.setString(4, task.getDueDate());
            pstmt.setString(5, task.getPriority());
            pstmt.setString(6, task.getStatus());
            
            pstmt.executeUpdate();
            
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                task.setTaskId(rs.getInt(1));
            }
        }
    }

    // Read all tasks
    public ArrayList<Task> getAllTasks() throws SQLException {
        ArrayList<Task> tasks = new ArrayList<>();
        String sql = "SELECT * FROM tasks";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Task task = new Task();
                task.setTaskId(rs.getInt("task_id"));
                task.setEmployeeId(rs.getInt("employee_id"));
                task.setTitle(rs.getString("title"));
                task.setDescription(rs.getString("description"));
                task.setDueDate(rs.getString("due_date"));
                task.setPriority(rs.getString("priority"));
                task.setStatus(rs.getString("status"));
                tasks.add(task);
            }
        }
        return tasks;
    }

    // Read task by ID
    public Task getTaskById(int id) throws SQLException {
        String sql = "SELECT * FROM tasks WHERE task_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                Task task = new Task();
                task.setTaskId(rs.getInt("task_id"));
                task.setEmployeeId(rs.getInt("employee_id"));
                task.setTitle(rs.getString("title"));
                task.setDescription(rs.getString("description"));
                task.setDueDate(rs.getString("due_date"));
                task.setPriority(rs.getString("priority"));
                task.setStatus(rs.getString("status"));
                return task;
            }
        }
        return null;
    }

    // Get tasks by employee ID
    public ArrayList<Task> getTasksByEmployeeId(int employeeId) throws SQLException {
        ArrayList<Task> tasks = new ArrayList<>();
        String sql = "SELECT * FROM tasks WHERE employee_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, employeeId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Task task = new Task();
                task.setTaskId(rs.getInt("task_id"));
                task.setEmployeeId(rs.getInt("employee_id"));
                task.setTitle(rs.getString("title"));
                task.setDescription(rs.getString("description"));
                task.setDueDate(rs.getString("due_date"));
                task.setPriority(rs.getString("priority"));
                task.setStatus(rs.getString("status"));
                tasks.add(task);
            }
        }
        return tasks;
    }

    // Get tasks by status
    public ArrayList<Task> getTasksByStatus(String status) throws SQLException {
        ArrayList<Task> tasks = new ArrayList<>();
        String sql = "SELECT * FROM tasks WHERE status = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, status);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Task task = new Task();
                task.setTaskId(rs.getInt("task_id"));
                task.setEmployeeId(rs.getInt("employee_id"));
                task.setTitle(rs.getString("title"));
                task.setDescription(rs.getString("description"));
                task.setDueDate(rs.getString("due_date"));
                task.setPriority(rs.getString("priority"));
                task.setStatus(rs.getString("status"));
                tasks.add(task);
            }
        }
        return tasks;
    }

    // Update
    public void updateTask(Task task) throws SQLException {
        String sql = "UPDATE tasks SET employee_id = ?, title = ?, description = ?, " +
                    "due_date = ?, priority = ?, status = ? WHERE task_id = ?";
                    
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, task.getEmployeeId());
            pstmt.setString(2, task.getTitle());
            pstmt.setString(3, task.getDescription());
            pstmt.setString(4, task.getDueDate());
            pstmt.setString(5, task.getPriority());
            pstmt.setString(6, task.getStatus());
            pstmt.setInt(7, task.getTaskId());
            
            pstmt.executeUpdate();
        }
    }

    // Delete
    public void deleteTask(int id) throws SQLException {
        String sql = "DELETE FROM tasks WHERE task_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
}